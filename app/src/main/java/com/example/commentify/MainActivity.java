package com.example.commentify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //zorgt voor werkende navigationcontroller
        NavController navController  = Navigation.findNavController(this,R.id.nav_host);

        //
        NavigationUI.setupActionBarWithNavController(this, navController);

    }
}
