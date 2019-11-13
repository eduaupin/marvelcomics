package com.example.desafiomarvel.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.data.CadastroDao;
import com.example.desafiomarvel.data.Database;
import com.example.desafiomarvel.model.Cadastro;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private SignInButton googleSignInButton;
    private GoogleSignInClient googleSignInClient;
    public static final String GOOGLE_ACCOUNT = "google_account";

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

        loginGoogle();


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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case 101:
                    try{
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                        GoogleSignInAccount conta = task.getResult(ApiException.class);
                        concluirLogin(conta);

                    }catch (ApiException e){
                        Log.i("LOG", "Error: " + e.getMessage());

                        Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }



    public void concluirLogin(GoogleSignInAccount conta) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(GOOGLE_ACCOUNT, conta);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount alreadyLoggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(alreadyLoggedAccount != null){
            Toast.makeText(this, "Você já está logadp", Toast.LENGTH_SHORT).show();
            concluirLogin(alreadyLoggedAccount);
        }else{
            Toast.makeText(this, "Faça o login no app :)", Toast.LENGTH_SHORT).show();
        }

    }

    public void loginGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(view ->{
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 101);
        });
    }

    public void initViews(){
        inputEmail = findViewById(R.id.inputEmailRegistro);
        inputSenha = findViewById(R.id.inputSenhaRegistro);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRegistro = findViewById(R.id.loginButtonCadastrese);
        cadastroDao = Database.getDatabase(this).cadastroDao();
        googleSignInButton = findViewById(R.id.sign_in_button);
    }





}
