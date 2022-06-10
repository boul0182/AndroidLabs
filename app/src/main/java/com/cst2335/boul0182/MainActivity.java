package com.cst2335.boul0182;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    public final static String PREFERENCES_FILE = "MyData";
    private static final String TAG = "MAIN_ACTIVITY";

    TextView editTextEmail;
//    TextView editTextPswd = findViewById(R.id.editTextPassword);

    //SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_main_shared_pref);

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);

        //Read preferences for email
        editTextEmail = findViewById(R.id.editTextEmailAddress);
        String previousEmail = prefs.getString("Email", "defaultEmail");
        editTextEmail.setText(previousEmail);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(clk -> {

            Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
            goToProfile.putExtra("Email", editTextEmail.getText().toString());

            startActivity(goToProfile);
        });
    }

    @Override //screen is visible but not responding
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override //screen is visible but not responding
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override //screen is visible but not responding
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");

        SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("Email", editTextEmail.getText().toString());
        //prefsEditor.putString("USERINPUT", editTextPswd.getText().toString());

        prefsEditor.apply(); //save to disk

    }

    @Override //not visible
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override  //garbage collected
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}