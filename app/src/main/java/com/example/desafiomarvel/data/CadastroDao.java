package com.example.desafiomarvel.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.desafiomarvel.model.Cadastro;

import io.reactivex.Observable;

@Dao
public interface CadastroDao {

    @Insert
    void insereCadastro(Cadastro cadastro);

    @Query("SELECT * FROM cadastros")
    Observable<Cadastro> pegaCadastro();

    @Query("SELECT * FROM cadastros WHERE email = :emailUser")
    Cadastro getByEmail(String emailUser);

    @Query("SELECT * FROM cadastros WHERE senha = :senhaUser")
    Cadastro getBySenha(String senhaUser);



}
