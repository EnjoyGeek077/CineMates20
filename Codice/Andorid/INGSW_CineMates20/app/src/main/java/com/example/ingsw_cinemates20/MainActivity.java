package com.example.ingsw_cinemates20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


// sto cazzeggiando con i bottoni
        Button evento = (Button) findViewById(R.id.buttonLogin);
        evento.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button y = (Button) findViewById(R.id.buttonLogin);
                y.setText("OK");
            }});



    }




}


