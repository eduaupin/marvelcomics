package com.example.desafiomarvel.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.desafiomarvel.model.Cadastro;

//A anotação @Database determina quais são as classes anotadas como entindades, qual a versão do BD e
//se o schema do bd será exportado ou nao
@androidx.room.Database(entities = {Cadastro.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static volatile Database INSTANCE;

    public abstract CadastroDao cadastroDao();

    public static Database getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, Database.class, "cadastros_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
