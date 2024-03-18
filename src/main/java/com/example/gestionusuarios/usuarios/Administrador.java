package com.example.gestionusuarios.usuarios;

public class Administrador extends Usuario{
    Boolean admin=false;

    public Administrador(String nombre,String correo, String password){
        super(nombre,correo,password);
    }

    public Administrador(String nombre,String correo, String password,boolean admin){
        super(nombre,correo,password);
        setAdmin(admin);
    }
    public boolean getAdmin(){
        return admin;
    }
    public void setAdmin(boolean admin){
        this.admin=admin;
    }



}
