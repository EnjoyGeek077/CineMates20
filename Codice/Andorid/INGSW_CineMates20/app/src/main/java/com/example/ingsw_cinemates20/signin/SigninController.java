package com.example.ingsw_cinemates20.signin;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.example.ingsw_cinemates20.entity.Utente;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninController extends AppCompatActivity {
    private EditText name, surname, email, pass, confPass, city, telephone;
    private CheckBox gdpr;
    private Calendar date;
    private ProgressBar progressBar;
    private Button buttonSignin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.TextName);
        surname = (EditText) findViewById(R.id.TextCognome);
        email = (EditText) findViewById(R.id.TextEmail);
        pass = (EditText) findViewById(R.id.TextPassword);
        confPass = (EditText) findViewById(R.id.TextConfPass);
        city = (EditText) findViewById(R.id.TextCity);
        telephone = (EditText) findViewById(R.id.TextTelephone);

        gdpr = (CheckBox) findViewById(R.id.gdprAccept);
        //date = (Calendar) findViewById(R.id.birthSelector);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar));

        buttonSignin = (Button) findViewById(R.id.buttonConfirmSignin);
        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caricamento();
                registerUser();
            }});
    }

    private void caricamento(){
        TextView x = (TextView) findViewById(R.id.textCm24);
        x.setVisibility(View.VISIBLE);

        ProgressBar pbar = (ProgressBar) findViewById(R.id.progressBar);
        pbar.setVisibility(View.VISIBLE);
    }


    private void registerUser() {


        String nameText = name.getText().toString().trim();
        String surnameText = surname.getText().toString().trim();
        //String dateText = getDate();
        String emailText = email.getText().toString().trim();
        String passwordText = pass.getText().toString().trim();
        String confPasswordText = confPass.getText().toString().trim();
        String cityText = city.getText().toString().trim();
        String telephoneText = telephone.getText().toString().trim();

        if(nameText.isEmpty()) {
            name.setError("Campo obbligatorio.");
            name.requestFocus();
            return;
        }

        if(surnameText.isEmpty()) {
            surname.setError("Campo obbligatorio.");
            surname.requestFocus();
            return;
        }

        //if(dateText.isEmpty()) {}

        if(emailText.isEmpty()) {
            email.setError("Campo obbligatorio.");
            email.requestFocus();
            return;
        }

        if(passwordText.isEmpty()) {
            pass.setError("Campo obbligatorio.");
            pass.requestFocus();
            return;
        }

        if(confPasswordText.isEmpty()) {
            confPass.setError("Campo obbligatorio.");
            confPass.requestFocus();
            return;
        }

        if(!verificaPass(passwordText)){
            pass.setError("La password non rispetta le policy.");
            pass.requestFocus();
            return;
        }

        if(!(passwordText==confPasswordText)){
            pass.setError("Le password non corrispondono.");
            pass.requestFocus();

            confPass.setError("Le password non corrispondono.");
            confPass.requestFocus();

            return;
        }

        if(!(verificaEmail(emailText))){
            email.setError("Email non valida.");
            email.requestFocus();
            return;
        }

        if(!(gdpr.isChecked())){
            Toast.makeText(SigninController.this,
                    "Accettare la GDPR per continuare.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Utente usr = new Utente(emailText,passwordText,
                                nameText,surnameText,
                                telephoneText,cityText);
        sendData(usr);

    }

    //private String getDate() {}

    private boolean verificaEmail(String email) {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            return  false;
    }

    private boolean verificaPass(String password) {
        Pattern path = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");
        Matcher m = path.matcher(password);
        boolean controllo = m.matches();
        return controllo;
    }

    private void sendData(Utente user) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference("Utenti")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(SigninController.this,
                                                "Utente registrato con successo.",
                                                Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility((View.GONE));
                                    }else{
                                        Toast.makeText(SigninController.this,
                                                "Errore durante la registrazione dell'utente.",
                                                Toast.LENGTH_LONG).show();
                                        //progressBar.setVisibility((View.GONE));
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(SigninController.this,
                            "Registrazione fallita.",
                                    Toast.LENGTH_LONG).show();
                            //progressBar.setVisibility((View.GONE));
                        }
                    }
                });
    }
}
