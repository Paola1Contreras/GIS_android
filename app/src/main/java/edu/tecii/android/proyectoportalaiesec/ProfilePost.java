package edu.tecii.android.proyectoportalaiesec;

/**
 * Created by Leo on 08/12/2016.
 */

public class ProfilePost {
    private String id;
    private String email;
    private String full_name;
    private String imagen;

    public ProfilePost(){

    }

    public ProfilePost(String id, String email, String full_name, String imagen){
        this.id = id;
        this.email = email;
        this.full_name = full_name;
        this.imagen = imagen;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public  String getFull_name(){
        return full_name;
    }

    public  void setFull_name(String full_name){
        this.full_name = full_name;
    }

    public  String getImagen(){
        return imagen;
    }

    public  void setImagen(String imagen){
        this.imagen = imagen;
    }
}
