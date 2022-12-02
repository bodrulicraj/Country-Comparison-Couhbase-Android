package com.countrycomparison.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.profile.CreateActivity;
import com.countrycomparison.profile.DeleteActivity;
import com.countrycomparison.profile.EinputActivity;

public class LoginActivity extends AppCompatActivity{


    EditText usernameInput;

    EditText nameInput;

    EditText addressInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.usernameInput);

        nameInput = findViewById(R.id.nameInput);

        addressInput = findViewById(R.id.addressInput);


    }

    public void onLoginTapped(View view) {

            Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

    }
    public void onCreateTapped(View view) {
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void onShowTapped(View view) {

        Intent intent = new Intent(getApplicationContext(), EinputActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void onDeleteTapped(View view) {

        Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    }


