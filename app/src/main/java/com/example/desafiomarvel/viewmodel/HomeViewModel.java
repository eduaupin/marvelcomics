package com.example.desafiomarvel.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafiomarvel.model.ComicsResponse;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.repository.ComicsRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.desafiomarvel.util.AppUtils.md5;

public class HomeViewModel extends AndroidViewModel {
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Result>> listaComics = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private ComicsRepository repository = new ComicsRepository();

    public static final String PRIMARY_KEY = "6eb7e8896ec5850c52515a8a23ee97f0";
    public static final String PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f";

    String ts = Long.toString(System.currentTimeMillis()/1000);

    String hash = md5(ts + PRIVATE_KEY + PRIMARY_KEY);

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getListaComics(){
        return this.listaComics;
    }

    public LiveData<Boolean> getLoading() {
        return this.loading;
    }

    public void getAllComics(){
        disposable.add(
                repository.getComics("magazine", "comic",
                        true, "focDate", ts, hash, PRIMARY_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable1 -> {
                            loading.setValue(true);
                        })
                        .doOnTerminate(() -> loading.setValue(false))
                .subscribe(response -> listaComics.setValue(response.getData().getResults()),
                        throwable -> {
                    Log.i("LOG", "erro " + throwable.getMessage());
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
