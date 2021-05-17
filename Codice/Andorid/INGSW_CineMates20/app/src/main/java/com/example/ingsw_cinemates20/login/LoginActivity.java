package com.example.ingsw_cinemates20.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ingsw_cinemates20.R;
import com.example.ingsw_cinemates20.signin.SigninActivity;
import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 100;
    private static final String TAG = "google_sing_tag";

    private EditText postalAddressEmail, passwordPass;
    private TextView textViewRecovery, textViewSignUp;
    private Button buttonFB, buttonGG, buttonLogin, visPass;
    private ProgressBar progressBar;
    int counter=0, r;

    private FirebaseAuth mAuth;

    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        postalAddressEmail = (EditText) findViewById(R.id.postalAddressEmail);
        passwordPass = (EditText) findViewById(R.id.passwordPass);

        textViewRecovery = (TextView) findViewById(R.id.textViewRecovery);
        textViewSignUp = (TextView) findViewById(R.id.textViewSignUp);

        //
        visPass = (Button) findViewById(R.id.eye);
        //

        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonFB = (Button) findViewById(R.id.buttonLoginFB);
        buttonGG = (Button) findViewById(R.id.buttonLoginGG);

        mAuth = FirebaseAuth.getInstance();

        //progressBar = (ProgressBar) findViewById(R.id.progressBar));

        setupFB();
        setupGG();

        visPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                r= counter %2;
                if(r !=0 ){
                passwordPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                    passwordPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                }
        });


        textViewSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
            }});

        textViewRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecoveryActivity.class));
            }});

        buttonFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInFacebook();
            }
        });

        buttonGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInGoogle();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInClassic();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    private void signInClassic() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Varia documentazione (FACEBOOK):
    //https://developers.facebook.com/docs/facebook-login/android/

    private void setupFB() {

        //HASH: ---

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

    }

    private void signInFacebook() {

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Varia Documentazione (GOOGLE):
    //https://firebase.google.com/docs/auth/android/google-signin
    //https://developers.google.com/identity/sign-in/android/sign-in

    private void setupGG() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void signInGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            //Register into RealTime Database

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            //updateUI(null);
                        }
                    }
                });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
}