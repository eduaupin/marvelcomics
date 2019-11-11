package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.data.CadastroDao;
import com.example.desafiomarvel.data.Database;
import com.example.desafiomarvel.model.Cadastro;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private Button btnLogin;
    private TextView btnRegistro;
    private CadastroDao cadastroDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();


//            btnLogin.setOnClickListener(view->{
//                new Thread(()->{
//                    if(!inputEmail.getEditText().getText().toString().isEmpty() &&
//                            !inputSenha.getEditText().getText().toString().isEmpty()){
//
//                        String email = inputEmail.getEditText().getText().toString();
//                        cadastroDao.getByEmail(email);
//
//                        Cadastro cadastro = new Cadastro("u", email, "s");
//
//                        if(email == cadastro.getEmailUser()) {
//
//                            Intent intent = new Intent(this, HomeActivity.class);
//                            startActivity(intent);
//                        }
//
//                    }else{
//                        Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
//                    }
//                }).start();
//            });

        btnLogin.setOnClickListener(view->{
            if(!inputEmail.getEditText().getText().toString().isEmpty() &&
                    !inputSenha.getEditText().getText().toString().isEmpty()){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegistro.setOnClickListener(view ->{
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        });
    }

    public void initViews(){
        inputEmail = findViewById(R.id.inputEmailRegistro);
        inputSenha = findViewById(R.id.inputSenhaRegistro);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistro = findViewById(R.id.loginButtonCadastrese);

        cadastroDao = Database.getDatabase(this).cadastroDao();
    }

}
