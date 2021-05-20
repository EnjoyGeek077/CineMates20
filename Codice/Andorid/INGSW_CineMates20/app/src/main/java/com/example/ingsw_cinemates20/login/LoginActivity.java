package com.example.ingsw_cinemates20.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView textViewRecovery, textViewSignUp, textView;
    private Button buttonFB, buttonGG, buttonLogin, visPass, eye;

    private ProgressBar progressBar;

    private int counter=0, r;
    private Animation anim_btn = null, anim_txtview = null;

    private FirebaseAuth mAuth;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        postalAddressEmail = findViewById(R.id.postalAddressEmail);
        passwordPass = findViewById(R.id.passwordPass);

        textViewRecovery = findViewById(R.id.textViewRecovery);
        textViewSignUp = findViewById(R.id.textViewSignUp);
        textView = findViewById(R.id.textView);

        visPass = findViewById(R.id.eye);
        buttonLogin = findViewById(R.id.buttonLogin);
        eye = findViewById(R.id.eye);
        buttonFB = findViewById(R.id.buttonLoginFB);
        buttonGG = findViewById(R.id.buttonLoginGG);

        anim_btn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_bottone);
        anim_txtview = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_textview);

        mAuth = FirebaseAuth.getInstance();

        //progressBar = (ProgressBar) findViewById(R.id.progressBar));

        setupFB();
        setupGG();
        
        animazioni();

        visPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                r= counter %2;
                if(r !=0 ){
                passwordPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                visPass.getBackground().setAlpha(50);
            }else{
                    passwordPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    visPass.getBackground().setAlpha(200);
                }
                }
        });

        textViewSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SigninActivity.class));
                // Animazione per apertura della pagina
              //  overridePendingTransition(R.anim.frame_in,R.anim.frame_in);
                //
            }});

        textViewRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RecoveryActivity.class));
            }});

        buttonFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFB.startAnimation(anim_btn);
                signInFacebook();
            }
        });

        buttonGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonGG.startAnimation(anim_btn);
                signInGoogle();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           buttonLogin.startAnimation(anim_btn);
           signInClassic();
            }
        });

        postalAddressEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postalAddressEmail.startAnimation(anim_txtview);
            }
        });

        passwordPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passwordPass.startAnimation(anim_txtview);
            }
        });

    }


    private void animazioni(){
        postalAddressEmail.setTranslationX(1000);
        postalAddressEmail.animate().translationX(0).setDuration(800).setStartDelay(400).start();
        passwordPass.setTranslationX(1000);
        passwordPass.animate().translationX(0).setDuration(800).setStartDelay(600).start();
        buttonLogin.setTranslationX(1000);
        buttonLogin.animate().translationX(0).setDuration(800).setStartDelay(800).start();
        textViewRecovery.setTranslationX(1000);
        textViewRecovery.animate().translationX(0).setDuration(800).setStartDelay(1000).start();
        eye.setTranslationX(1000);
        eye.animate().translationX(0).setDuration(1000).setStartDelay(600).start();

        buttonFB.setTranslationY(1000);
        buttonFB.animate().translationY(0).setDuration(800).setStartDelay(400).start();
        buttonGG.setTranslationY(1000);
        buttonGG.animate().translationY(0).setDuration(800).setStartDelay(600).start();
        textView.setTranslationY(1000);
        textView.animate().translationY(0).setDuration(800).setStartDelay(800).start();
        textViewSignUp.setTranslationY(1000);
        textViewSignUp.animate().translationY(0).setDuration(800).setStartDelay(1000).start();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }

    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            finish();
            Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            FirebaseAuth.getInstance().signOut();

            //restart this activity
        }
    }

    private void signInClassic() {
        FirebaseAuth.getInstance().signOut();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Varia documentazione (FACEBOOK):
    //https://developers.facebook.com/docs/facebook-login/android/

    private void setupFB() {

        //HASH: ---

        CallbackManager callbackManager = CallbackManager.Factory.create();

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