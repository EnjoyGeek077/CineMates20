package com.example.ingsw_cinemates20;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SignUpFragment extends Fragment {
    EditText name, surname, email, telephone, city, pass, confirmpass;
    TextView info;
    Button signup;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container,  false);

        name = root.findViewById(R.id.email);
        surname = root.findViewById(R.id.pass);
        email = root.findViewById(R.id.forget_pass);
        telephone = root.findViewById(R.id.buttonLogin);
        city = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        confirmpass = root.findViewById(R.id.forget_pass);
        info = root.findViewById(R.id.buttonLogin);
        signup = root.findViewById(R.id.buttonLogin);

        name.setTranslationY(300);
        surname.setTranslationY(300);
        email.setTranslationY(300);
        telephone.setTranslationY(300);
        city.setTranslationY(300);
        pass.setTranslationY(300);
        confirmpass.setTranslationY(300);
        info.setTranslationY(300);
        signup.setTranslationY(300);

        name.setAlpha(v);
        surname.setAlpha(v);
        email.setAlpha(v);
        telephone.setAlpha(v);
        city.setAlpha(v);
        pass.setAlpha(v);
        confirmpass.setAlpha(v);
        info.setAlpha(v);
        signup.setAlpha(v);

        name.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        surname.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        telephone.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        city.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        confirmpass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        info.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        signup.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        return root;
    }
}
