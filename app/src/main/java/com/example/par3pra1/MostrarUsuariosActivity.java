package com.example.par3pra1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.par3pra1.MostrarBD.AdaptadorUsuarios;
import com.example.par3pra1.MostrarBD.Usuarios;

import java.util.ArrayList;

public class MostrarUsuariosActivity extends AppCompatActivity {
    ArrayList<Usuarios> usuarios;
    RecyclerView recyclerView;

    BaseDatos conn;
    int ide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_usuarios);

        recyclerView = findViewById(R.id.recyclerUsuarios);

        conn=new BaseDatos(this,"Base Datos",null,1);
        usuarios = new ArrayList<Usuarios>();

        llenarusuarios();
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        AdaptadorUsuarios adapter=new AdaptadorUsuarios(usuarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    void llenarusuarios(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from usuarios",null);
        Usuarios usuario = null;
        while(cursor.moveToNext()){
            usuario = new Usuarios();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuarios.add(usuario);
        }

    }
}