package com.example.ingsw_cinemates20.entity;

public class Utente {

    String Email, Password, Nome, Cognome, Telefono, City;
    int NumLike;

    public Utente() {}

    public Utente(String email, String password,
                  String nome, String cognome,
                  String telefono, String città) {

        this.setEmail(email);
        this.setPassword(password);
        this.setNome(nome);
        this.setCognome(cognome);

        if(telefono.isEmpty()){
            this.setTelefono("None");
        }else{
            this.setTelefono(telefono);
        }

        if(telefono.isEmpty()){
            this.setCity("None");
        }else{
            this.setCity(città);
        }

        this.NumLike = 0;

    }







    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCognome() {
        return Cognome;
    }

    public void setCognome(String cognome) {
        Cognome = cognome;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getNumLike() {
        return NumLike;
    }

    public void setNumLike(int numLike) {
        NumLike = numLike;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
