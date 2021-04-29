package com.example.ingsw_cinemates20;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Utente {

    String Email, Password, Nome, Cognome, Telefono, Citta;
    int NumLike;


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

    public String getCitta() {
        return Citta;
    }

    public void setCitta(String citta) {
        Citta = citta;
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

    /*
       String s;
        Date d= null;
        System.out.println("Inserisci la data nel formato [gg/mm/yyyy]: ");
        Scanner in= new Scanner(System.in);
        s = in.nextLine();
        try{
            DateFormat formatDate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.IT);
            formatDate.setLenient(false); // la conversione deve essere rigorosa
            d = formatDate.parse(s);
        } catch (ParseException e) {
            System.out.println("Formato inserito errato");
        }
        System.out.println(d);
    */

}
