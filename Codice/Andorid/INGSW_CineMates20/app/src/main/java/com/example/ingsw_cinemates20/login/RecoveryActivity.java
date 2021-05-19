package com.example.ingsw_cinemates20.login;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.google.firebase.auth.FirebaseAuth;

public class RecoveryActivity extends AppCompatActivity {
    EditText emailRecovery;
    Button send;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperopassword);

        auth = FirebaseAuth.getInstance();
        emailRecovery = findViewById(R.id.TextRecoveryEmail);
        send = findViewById(R.id.btnRecovery);

        send.setOnClickListener(v -> {
            String email = emailRecovery.getText().toString().trim();
            sendResetRequest(email);
        });
    }

    private void sendResetRequest(String email) {

        if(email.isEmpty()) {
            emailRecovery.setError("Campo obbligatorio.");
            emailRecovery.requestFocus();
            return;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRecovery.setError("Email non valida.");
            emailRecovery.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Toast.makeText(RecoveryActivity.this, "Recovery mail inviata con successo.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RecoveryActivity.this, "Ops, qualcosa è andato storto.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
