package com.example.par3pra1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class EliminarActivity extends AppCompatActivity {
    TextView codigo,n,p,d;
    Button leer, mod;
    Cursor reg;

    int id;

    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        leer = findViewById(R.id.btnBLeer2);
        codigo = findViewById(R.id.LBLBProducto2);
        n = findViewById(R.id.txtNProducto2);
        p = findViewById(R.id.txtPProducto2);
        d = findViewById(R.id.txtDProducto2);

        mod = findViewById(R.id.btnMProducto2);

        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eliminar(v);
            }
        });

        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!permisoCamaraConcedido) {
                    //Toast.makeText(RegistroProductosActivity.this, "Por favor permite que la app acceda a la cámara", Toast.LENGTH_SHORT).show();
                    permisoSolicitadoDesdeBoton = true;
                    verificarYPedirPermisosDeCamara();
                    return;
                }
                tomarlectura();
            }
        });

    }

    void eliminar(View v){

        BaseDatos db1=new BaseDatos(this,"Base Datos",null,1);
        SQLiteDatabase db=db1.getWritableDatabase();

        String instruccion="delete from productos where id='"+id+"'";
        db.execSQL(instruccion);
       // db.close();
       // finish();

        Intent i = new Intent(EliminarActivity.this, UsuarioActivity.class);
        startActivity(i);
    }


    void validar(){
        String cb;
        cb = codigo.getText().toString();

        BaseDatos db1 = new BaseDatos(this,"Base Datos",null,1);
        SQLiteDatabase conexion=db1.getWritableDatabase();
        reg = conexion.rawQuery("select * from productos where codigo='"+cb+"'",null);
        if (reg.moveToFirst()){
            id = reg.getInt(0);
            String nom = reg.getString(2);
            String pre = reg.getString(3);
            String des = reg.getString(4);
            n.setText(nom);
            p.setText(pre);
            d.setText(des);

        }else{
            Toast.makeText(this, "El producto no existe", Toast.LENGTH_SHORT).show();
        }
        conexion.close();

    }

    void tomarlectura(){
        Intent i = new Intent(EliminarActivity.this, CamaraActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    String codigoL = data.getStringExtra("codigo");
                    codigo.setText(codigoL);
                    validar();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Escanear directamten solo si fue pedido desde el botón
                    if (permisoSolicitadoDesdeBoton) {
                        tomarlectura();
                    }
                    permisoCamaraConcedido = true;
                } else {
                    permisoDeCamaraDenegado();
                }
                break;
        }
    }

    private void verificarYPedirPermisosDeCamara() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(EliminarActivity.this, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            permisoCamaraConcedido = true;
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(EliminarActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }


    private void permisoDeCamaraDenegado() {
        // Esto se llama cuando el usuario hace click en "Denegar" o
        // cuando lo denegó anteriormente
        Toast.makeText(EliminarActivity.this, "No puedes escanear si no das permiso", Toast.LENGTH_SHORT).show();
    }
}


