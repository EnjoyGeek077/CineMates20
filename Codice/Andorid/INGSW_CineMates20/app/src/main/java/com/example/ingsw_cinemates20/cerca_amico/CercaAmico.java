package com.example.ingsw_cinemates20.cerca_amico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.ingsw_cinemates20.R;
import com.example.ingsw_cinemates20.assistance.Assistance;
import com.example.ingsw_cinemates20.cerca_film.CercaFilm;
import com.example.ingsw_cinemates20.home.Home;
import com.example.ingsw_cinemates20.profile.Profile;
import com.example.ingsw_cinemates20.setting.Settings;

public class CercaAmico extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerca_amico);

        drawerLayout = findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view){
        Home.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        Home.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        Home.redirectActivity(this, Home.class);
    }

    public void ClickProfile(View view){
        Home.redirectActivity(this, Profile.class);
    }

    public void ClickSearch_friend(View view){
        recreate();
    }

    public void ClickSearch_film(View view){
        Home.redirectActivity(this, CercaFilm.class);
    }

    public void ClickSettings(View view){
        Home.redirectActivity(this, Settings.class);
    }

    public void ClickAssistance(View view){
        Home.redirectActivity(this, Assistance.class);
    }
    public void ClickLogout(View view){
        Home.logout(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Close drawer
        Home.closeDrawer(drawerLayout);
    }
    }
