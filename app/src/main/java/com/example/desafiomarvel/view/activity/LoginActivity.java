package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafiomarvel.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout inputEmail;
    private TextInputLayout inputSenha;
    private Button btnLogin;
    private TextView btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();


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
    }

}
