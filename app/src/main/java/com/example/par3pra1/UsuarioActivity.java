package com.example.par3pra1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UsuarioActivity extends AppCompatActivity {
    Button reg,cons,mod,eli,mostrarusuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        reg=findViewById(R.id.btnRegistro);
        mod=findViewById(R.id.btnModificar);
        mostrarusuarios=findViewById(R.id.btnMostrarUsuarios);
        cons=findViewById(R.id.btnConsultar);
        eli=findViewById(R.id.btnEliminar);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioActivity.this,RegistroProdActivity.class);
                startActivity(i);
            }
        });

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioActivity.this,ModificarActivity.class);
                startActivity(i);
            }
        });

        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioActivity.this,MostrarProductosActivity.class);
                startActivity(i);
            }
        });

        mostrarusuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioActivity.this, MostrarUsuariosActivity.class);
                startActivity(i);
            }
        });

        eli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioActivity.this, EliminarActivity.class);
                startActivity(i);
            }
        });

    }

}