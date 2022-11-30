package com.example.par3pra1.MostrarBD;

import java.io.Serializable;

public class Usuarios implements Serializable {
    int id;
    String nombre;

    public Usuarios() {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
