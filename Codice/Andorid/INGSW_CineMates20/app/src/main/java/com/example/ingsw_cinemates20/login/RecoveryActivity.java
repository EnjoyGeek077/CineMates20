package com.example.ingsw_cinemates20.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        emailRecovery = (EditText) findViewById(R.id.TextRecoveryEmail);
        send = (Button) findViewById(R.id.btnRecovery);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailRecovery.getText().toString().trim();
                sendResetRequest(email);
            }
        });
    }

    private void sendResetRequest(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RecoveryActivity.this, "Email di recupero inviata, controlla la casella postale!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RecoveryActivity.this, "Errore, controllare se l'utente esiste nel sitema.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
