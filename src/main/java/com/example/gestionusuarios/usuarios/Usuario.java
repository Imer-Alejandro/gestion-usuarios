package com.example.gestionusuarios.usuarios;

import java.util.ArrayList;

public abstract class  Usuario {
    public static int countUser=1;

    int id= 0;
    String nombre= "" ;
    String correo= "" ;
    String password= "";

    public Usuario(String nombre,String correo, String password){
        this.correo=correo;
        this.nombre=nombre;
        this.password=password;
        this.id = countUser++;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getCorreo(){
        return this.correo;
    }

    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }

    public int getCountUser(){
        return countUser;
    }


    public int getId(){
        return id;
    }

}
