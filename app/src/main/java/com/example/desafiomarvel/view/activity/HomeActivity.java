package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.view.adapters.QuadrinhoAdapter;
import com.example.desafiomarvel.view.interfaces.ComicsOnClick;
import com.example.desafiomarvel.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ComicsOnClick {
    private RecyclerView recyclerView;
    private QuadrinhoAdapter adapter;
    private HomeViewModel viewModel;
    private List<Result> listaDeQuadrinhos = new ArrayList<>();
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.homemenu);
        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        viewModel.getAllComics();

        viewModel.getListaComics().observe(this, results -> {
            adapter.atualizaLista(results);
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void initViews(){
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new QuadrinhoAdapter(listaDeQuadrinhos, this);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return true;
    }

    @Override
    public void OnClick(Result result) {
        Intent intent = new Intent(this, QuadrinhoDetalheActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("chave", result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
