package com.example.ingsw_cinemates20.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class LoginController extends AppCompatActivity {
    private EditText postalAddressEmail, passwordPass;

    private TextView textViewRecovery, textViewSignUp;

    private Button buttonFB, buttonGG, buttonLogin;
    private ProgressBar progressBar;

    private FirebaseAuth FBAuth;
    private CallbackManager callbackManager;

    private FirebaseAuth GGAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postalAddressEmail = (EditText) findViewById(R.id.postalAddressEmail);
        passwordPass = (EditText) findViewById(R.id.passwordPass);

        textViewRecovery = (TextView) findViewById(R.id.textViewRecovery);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonFB = (Button) findViewById(R.id.buttonLoginFB);
        buttonGG = (Button) findViewById(R.id.buttonLoginGG);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar));

        setupFB();
        setupGG();
    }


    private void setupFB() {
        //FACEBOOK

        //HASH: k+2+1yCaaW4lvIlw67X11Ow3qTU=

        callbackManager = CallbackManager.Factory.create();

        //buttonFB.setReadPermissions(Arrays.asList("email","public_profile"));
//
//        buttonLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
        // App code
//            }
//        });


        //Continua sulla doc:
        //https://developers.facebook.com/docs/facebook-login/android/
    }

    private void setupGG() {
        //GOOGLE

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //Continua sulla doc:
        //https://firebase.google.com/docs/auth/android/google-signin
        //https://developers.google.com/identity/sign-in/android/sign-in

    }
}
