package com.popupdialog.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new PopupDialog.Builder(this)
                .setTitle("ERROR")
                .setMessage("Service is temporarily unavailable.")
                .setPositiveBtnText("OK")
                .setCancelableOnTouchOutside(false)
                .OnPositiveClicked(() -> {
                }).build();
    }
}