package com.example.desafiomarvel.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cadastros")
public class Cadastro implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "nome")
    private String nomeUser;

    @ColumnInfo(name = "senha")
    private String senhaUser;

    @ColumnInfo(name = "email")
    private String emailUser;

    public Cadastro(String nomeUser, String senhaUser, String emailUser) {
        this.nomeUser = nomeUser;
        this.senhaUser = senhaUser;
        this.emailUser = emailUser;
    }

    protected Cadastro(Parcel in) {
        id = in.readLong();
        nomeUser = in.readString();
        senhaUser = in.readString();
        emailUser = in.readString();
    }

    public static final Creator<Cadastro> CREATOR = new Creator<Cadastro>() {
        @Override
        public Cadastro createFromParcel(Parcel in) {
            return new Cadastro(in);
        }

        @Override
        public Cadastro[] newArray(int size) {
            return new Cadastro[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getSenhaUser() {
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        this.senhaUser = senhaUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(nomeUser);
        dest.writeString(senhaUser);
        dest.writeString(emailUser);
    }
}
