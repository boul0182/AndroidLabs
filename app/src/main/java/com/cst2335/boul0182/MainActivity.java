package com.cst2335.boul0182;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        //now your xml is loaded
        TextView topView = findViewById(R.id.textView); //must match XML id
        String oldText = topView.getText().toString();
        topView.setText("Java put this here");


        Button btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() { //anonymous class
            @Override
            public void onClick(View v) {
                topView.setText("Edit text has " + btn.getText());
            }
        });

        Switch switchBotton = (Switch) findViewById(R.id.switch1);

        switchBotton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Snackbar snackbar;
                if (isChecked) {
                    snackbar = Snackbar.make(buttonView, R.string.switch_msg_on,
                            Snackbar.LENGTH_LONG).setAction("undo", click ->
                            buttonView.setChecked(!isChecked));
                } else {
                    snackbar = Snackbar.make(buttonView, R.string.switch_msg_off, Snackbar.LENGTH_LONG);
                }
                snackbar.show();
            }
        });
    }
}