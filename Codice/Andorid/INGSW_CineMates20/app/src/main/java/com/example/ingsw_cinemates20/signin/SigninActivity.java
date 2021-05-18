package com.example.ingsw_cinemates20.signin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.example.ingsw_cinemates20.entity.Utente;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {
    private EditText name, surname, email, pass, confPass, city, telephone;
    private CheckBox gdpr;
    private ProgressBar progressBar;
    private Button vPass, visConfPass;
    int counter=0, r, counter2=0, r2;

    DatePickerDialog.OnDateSetListener setListener;

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrazione);

        mAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.TextName);
        surname = findViewById(R.id.TextCognome);
        email = findViewById(R.id.TextEmail);
        pass = findViewById(R.id.TextPassword);
        confPass = findViewById(R.id.TextConfPass);
        city = findViewById(R.id.TextCity);
        telephone = findViewById(R.id.TextTelephone);

        vPass = (Button) findViewById(R.id.visPass);
        visConfPass = (Button) findViewById(R.id.visConPass);

        gdpr = findViewById(R.id.gdprAccept);
        progressBar = findViewById(R.id.progressBar);

        Button buttonSignin = findViewById(R.id.buttonConfirmSignin);
        buttonSignin.setOnClickListener(v -> registerUser());



        vPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                r= counter %2;
                if(r !=0 ){
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    vPass.getBackground().setAlpha(50);
                }else{
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    vPass.getBackground().setAlpha(200);
                }
            }
        });

        visConfPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter2++;
                r2= counter2 %2;
                if(r2 !=0 ){
                    confPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    visConfPass.getBackground().setAlpha(50);
                }else{
                    confPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    visConfPass.getBackground().setAlpha(200);
                }
            }
        });

    }

    private void registerUser() {

        String nameText = name.getText().toString().trim();
        String surnameText = surname.getText().toString().trim();
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

        if(!(passwordText.equals(confPasswordText))){
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
            Toast.makeText(SigninActivity.this,
                    "Accettare la GDPR per continuare.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        Utente usr = new Utente(emailText,passwordText,
                nameText,surnameText,
                telephoneText,cityText);

        progressBar.setVisibility((View.VISIBLE));

        sendData(usr);

    }

    //private Bool dateControl() {
    //        String s;
    //        Date d= null;
    //        System.out.println("Inserisci la data nel formato [gg/mm/yyyy]: ");
    //        Scanner in= new Scanner(System.in);
    //        s = in.nextLine();
    //        try{
    //            DateFormat formatDate = DateFormat.getDateInstance(DateFormat.SHORT, Locale.IT);
    //            formatDate.setLenient(false); // la conversione deve essere rigorosa
    //            d = formatDate.parse(s);
    //        } catch (ParseException e) {
    //            System.out.println("Formato inserito errato");
    //        }
    //        System.out.println(d);
    // }

    private boolean verificaEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean verificaPass(String password) {
        Pattern path = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,15})");
        Matcher m = path.matcher(password);
        return m.matches();
    }

    private void sendVerification() {
        findViewById(R.id.buttonConfirmSignin).setEnabled(false);

        final FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification()
                .addOnCompleteListener(this, task -> {
                    findViewById(R.id.buttonConfirmSignin).setEnabled(true);
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Verification email inviata a " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Errore nell'invio della verification email.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void sendData(Utente user) {

        mAuth.createUserWithEmailAndPassword(user.getEmail().trim(),user.getPassword().trim())
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                .setValue(user).addOnCompleteListener(taskDB -> {
                                    if(taskDB.isSuccessful()) {
                                        Toast.makeText(SigninActivity.this,
                                                "Utente registrato con successo.",
                                                Toast.LENGTH_LONG).show();
                                        //sendVerification();
                                    }else{
                                        Toast.makeText(SigninActivity.this,
                                                "Errore durante la registrazione dell'utente.",
                                                Toast.LENGTH_LONG).show();
                                    }
                            progressBar.setVisibility((View.GONE));
                        });
                    }else{
                        Toast.makeText(SigninActivity.this,
                                "Registrazione fallita.",
                                Toast.LENGTH_LONG).show();
                        progressBar.setVisibility((View.GONE));
                    }
                });

    }
}