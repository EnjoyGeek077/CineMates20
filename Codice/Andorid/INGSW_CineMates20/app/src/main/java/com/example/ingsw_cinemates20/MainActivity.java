package com.example.ingsw_cinemates20;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout panel;
    EditText postalAddressEmail, passwordPass, textViewRecovery, textViewSignUp, textView;
    Button buttonLogin, buttonLoginFB, buttonLoginGG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


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





