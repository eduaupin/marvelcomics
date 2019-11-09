package com.example.desafiomarvel.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.repository.ComicsRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.desafiomarvel.util.AppUtils.md5;

public class HomeViewModel extends AndroidViewModel {
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Result>> listaComics = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private ComicsRepository repository = new ComicsRepository();

    public static final String PRIMARY_KEY = "fe81c0a4bd6c7f00e3df25d68d8d8a92";
    public static final String PRIVATE_KEY = "ceac13aef2089eaf3c704ba9da60cf2156b60912";

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
                .subscribeOn(Schedulers.newThread())
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
