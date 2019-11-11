package com.example.desafiomarvel.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.desafiomarvel.model.Cadastro;

@Dao
public interface CadastroDao {

    @Insert
    void insereCadastro(Cadastro cadastro);

    @Query("SELECT * FROM cadastros WHERE email = :emailUser")
    Cadastro getByEmail(String emailUser);

}
