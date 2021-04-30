package com.example.ingsw_cinemates20.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ingsw_cinemates20.R;

public class LoginTabFragment extends Fragment {
    EditText email, pass;
    TextView forgetPass;
    Button login;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container,  false);

        email = root.findViewById(R.id.email);
        pass = root.findViewById(R.id.pass);
        forgetPass = root.findViewById(R.id.forget_pass);
        login = root.findViewById(R.id.buttonLogin);

        email.setTranslationY(300);
        pass.setTranslationY(300);
        forgetPass.setTranslationY(300);
        login.setTranslationY(300);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetPass.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        pass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        forgetPass.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        return root;
    }
}
