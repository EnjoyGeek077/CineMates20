package com.example.ingsw_cinemates20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.ingsw_cinemates20.signin.SigninController;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView signup = (TextView) findViewById(R.id.textViewSignUp);
        signup.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View view) {
            startActivity(new Intent(MainActivity.this, SigninController.class));
            }});


        //Prova! Da implementare con il controller (da creare)
        TextView pass_dim = (TextView) findViewById(R.id.textViewRecovery);
        pass_dim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.recuperopassword);
            }});

    }}





