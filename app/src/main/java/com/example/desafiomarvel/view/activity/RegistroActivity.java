package com.example.desafiomarvel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.data.CadastroDao;
import com.example.desafiomarvel.data.Database;
import com.example.desafiomarvel.model.Cadastro;
import com.google.android.material.textfield.TextInputLayout;

public class RegistroActivity extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private TextInputLayout inputNome;
    private Button btnRegistro;
    private TextView btnLogin;
    private CadastroDao cadastroDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        initViews();



            btnRegistro.setOnClickListener(view->{

                new Thread(() ->{
                    if(!inputEmail.getEditText().getText().toString().isEmpty() &&
                            !inputSenha.getEditText().getText().toString().isEmpty() &&
                            !inputNome.getEditText().getText().toString().isEmpty()){

                        String nome = inputNome.getEditText().getText().toString();
                        String email = inputEmail.getEditText().getText().toString();
                        String senha = inputSenha.getEditText().getText().toString();

                        Cadastro cadastro = new Cadastro(nome,senha, email);

                        if(cadastro != null){
                            cadastroDao.insereCadastro(cadastro);
                        }

                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    }

                }).start();


            });

        btnLogin.setOnClickListener(view ->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void initViews(){
        inputEmail = findViewById(R.id.cadastroInputEmailRegistro);
        inputSenha = findViewById(R.id.cadastroInputSenhaRegistro);
        inputNome = findViewById(R.id.cadastroInputNomeRegistro);
        btnRegistro = findViewById(R.id.cadatroButtonCadastro);
        btnLogin = findViewById(R.id.CadastroButtonLogin);

        cadastroDao = Database.getDatabase(this).cadastroDao();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
