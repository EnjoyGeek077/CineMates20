package com.example.ingsw_cinemates20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    EditText postalAddressEmail, passwordPass, textViewRecovery, textViewSignUp;
    TabLayout panel;
    Button buttonLogin, buttonLoginFB, buttonLoginGG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        TextView signup = (TextView) findViewById(R.id.textViewSignUp);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                setContentView(R.layout.registrazione);
            }});

    }

    /*
        Button evento = (Button) findViewById(R.id.buttonLogin);
        evento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button y = (Button) findViewById(R.id.buttonLogin);
                y.setText("OK");
            }});
    */


    }





