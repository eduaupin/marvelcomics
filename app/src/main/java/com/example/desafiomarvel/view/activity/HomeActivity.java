package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.view.adapters.QuadrinhoAdapter;
import com.example.desafiomarvel.view.interfaces.ComicsOnClick;
import com.example.desafiomarvel.viewmodel.HomeViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCanceledListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.desafiomarvel.view.activity.LoginActivity.GOOGLE_ACCOUNT;

public class HomeActivity extends AppCompatActivity implements ComicsOnClick {
    private RecyclerView recyclerView;
    private QuadrinhoAdapter adapter;
    private HomeViewModel viewModel;
    private List<Result> listaDeQuadrinhos = new ArrayList<>();
    private ProgressBar progressBar;
    private Button btnLogout;
    private ImageView imgUser;
    private GoogleSignInClient googleSignInClient;


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

        //recuperando login
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        //pega os dados
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        pegaOsDados();

        btnLogout.setOnClickListener(view ->{
            googleSignInClient.signOut().addOnCompleteListener(task ->{
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                HomeActivity.this.startActivity(intent);
                finish();
            });
        });

    }

    private void pegaOsDados() {
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        if(googleSignInAccount != null) {
            Picasso.get().load(googleSignInAccount.getPhotoUrl())
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imgUser);
        }

    }

    public void initViews(){
        recyclerView = findViewById(R.id.recycler);
        progressBar = findViewById(R.id.progress_bar);
        adapter = new QuadrinhoAdapter(listaDeQuadrinhos, this);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        btnLogout = findViewById(R.id.btnLogout);
        imgUser = findViewById(R.id.userImage);
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
