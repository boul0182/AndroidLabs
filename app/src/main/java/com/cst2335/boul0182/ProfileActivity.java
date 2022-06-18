package com.cst2335.boul0182;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.util.Log;

import com.google.android.material.snackbar.Snackbar;

public class ProfileActivity extends AppCompatActivity {


    //public final static String PREFERENCES_FILE = "MyData";
    private final static String TAG = "PROFILE_ACTIVITY";

    //SharedPreferences prefs = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);

    TextView editTextEmail;
    ActivityResultLauncher<Intent> myPictureTakerLauncher;
    ImageButton cameraButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editTextEmail = findViewById(R.id.editTextEmailAddress);
        //TextView editTextPswd = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.loginButton);
        cameraButton = findViewById(R.id.cameraButton);

        //Read preferences for email
        Intent fromMain = getIntent();
        String retrievedEmail = fromMain.getStringExtra("Email");
        editTextEmail.setText(retrievedEmail);

        myPictureTakerLauncher =
                registerForActivityResult( new ActivityResultContracts.StartActivityForResult()
                        ,new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK)
                { Intent data = result.getData();
                    Bitmap imgbitmap = (Bitmap) data.getExtras().get("data");
                    cameraButton.setImageBitmap(imgbitmap); // the imageBuLon
                }
                else if(result.getResultCode() == Activity.RESULT_CANCELED)
                    Log.i(TAG, "User refused to capture a picture.");
                }
        } );

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        Intent nextPage = new Intent(this,ChatRoomActivity.class);
        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener( click ->
        {
            startActivity(nextPage);    //go to Chatctivity.java
        });
    }

    private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                myPictureTakerLauncher.launch(takePictureIntent);
            }
    }



        @Override //screen is visible but not responding
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    };

    @Override //screen is visible but not responding
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    };

    @Override //screen is visible but not responding
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
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