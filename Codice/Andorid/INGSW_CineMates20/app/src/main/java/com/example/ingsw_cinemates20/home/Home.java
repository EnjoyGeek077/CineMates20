package com.example.ingsw_cinemates20.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.ingsw_cinemates20.R;
import com.example.ingsw_cinemates20.assistance.Assistance;
import com.example.ingsw_cinemates20.cerca_amico.CercaAmico;
import com.example.ingsw_cinemates20.cerca_film.CercaFilm;
import com.example.ingsw_cinemates20.profile.Profile;
import com.example.ingsw_cinemates20.setting.Settings;

public class Home extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
    }


    public void ClickMenu(View view){
        //Open drawer
        openDrawer(drawerLayout);

    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            //Quando il drawer è aperto viene chiuso
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        //Ricrea activity
        recreate();
    }

    public void ClickProfile(View view){
        redirectActivity(this, Profile.class);
    }

    public void ClickSearch_friend(View view){
        redirectActivity(this, CercaAmico.class);
    }

    public void ClickSearch_film(View view){
        redirectActivity(this, CercaFilm.class);
    }

    public void ClickSettings(View view){
        redirectActivity(this, Settings.class);
    }

    public void ClickAssistance(View view){
        redirectActivity(this, Assistance.class);
    }

    public void ClickLogout(View view){
        logout(this);
    }

    public static void logout(Activity activity){
        //alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Risposta negativa
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}
