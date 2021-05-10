package com.example.ingsw_cinemates20;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    }}





