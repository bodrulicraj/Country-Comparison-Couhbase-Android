package com.countrycomparison.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.login.LoginActivity;
import com.countrycomparison.util.DatabaseManager;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity implements UserProfileContract.View {

    private UserProfileContract.UserActionsListener mActionListener;


    EditText nameInput;
    EditText usernameInput;
    EditText emailInput;
    EditText executionTimev;
    EditText continentInput;
    EditText citiesInput;
    EditText parksInput;
    EditText airportsInput;
    EditText hotelsInput;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        nameInput = findViewById(R.id.userInput);
        continentInput = findViewById(R.id.continentInput);
        citiesInput = findViewById(R.id.citiesInput);
        parksInput = findViewById(R.id.parksInput);
        airportsInput = findViewById(R.id.airportsInput);
        hotelsInput = findViewById(R.id.hotelsInput);
        executionTimev=findViewById(R.id.exectimeETC);


        mActionListener = new UserProfilePresenter(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActionListener.fetchProfile();
            }
        });
    }

    public void onLogoutTapped(View view) {
        DatabaseManager.getSharedInstance().closeDatabaseForUser();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onSaveTapped(View view) {
        // tag::userprofile[]
        Map<String, Object> profile = new HashMap<>();
        profile.put("email", nameInput.getText().toString());
        profile.put("name", continentInput.getText().toString());
        profile.put("cities", citiesInput.getText().toString());
        profile.put("parksInput", parksInput.getText().toString());
        profile.put("airportsInput", airportsInput.getText().toString());
        profile.put("hotelsInput", hotelsInput.getText().toString());

        DatabaseManager dbMgr = DatabaseManager.getSharedInstance();
        dbMgr.initCouchbaseLite(getApplicationContext());
        dbMgr.openOrCreateDatabaseForUser(getApplicationContext(), nameInput.getText().toString());
        mActionListener.saveProfile(profile);


        executionTimev.setText(DemoClass.executionTime+" ms");
        Log.d("timeCreatemain", String.valueOf(DemoClass.executionTime));

        Toast.makeText(this, "Successfully updated !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showProfile(Map<String, Object> profile) {


    }
}

