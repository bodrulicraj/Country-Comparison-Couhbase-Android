package com.countrycomparison.profile;

import static com.countrycomparison.profile.DemoClass.executionTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.login.LoginActivity;
import com.countrycomparison.util.DatabaseManager;

import java.util.Map;

public class ReadActivity extends AppCompatActivity implements UserProfileContract.View {
    UserProfilePresenter myDB;

    private UserProfileContract.UserActionsListener mActionListener;



    EditText nameInput;
    EditText usernameInput;
    EditText emailInput;
    EditText addressInput;
    EditText continentInput;
    EditText citiesInput;
    EditText parksInput;
    EditText airportsInput;
    EditText hotelsInput;
    EditText edittextET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        myDB = new UserProfilePresenter(this);

        nameInput = findViewById(R.id.userInput);
        continentInput = findViewById(R.id.continentInput);
        citiesInput = findViewById(R.id.citiesInput);
        parksInput = findViewById(R.id.parksInput);
        airportsInput = findViewById(R.id.airportsInput);
        hotelsInput = findViewById(R.id.hotelsInput);
        edittextET =findViewById(R.id.exectimeET);




        mActionListener = new UserProfilePresenter(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActionListener.fetchProfile();
            }
        });

        edittextET.setText(executionTime+" ms");
        Log.d("timereadmain", String.valueOf(executionTime));
    }




    public void onLogoutTapped(View view) {
        DatabaseManager.getSharedInstance().closeDatabaseForUser();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }




    @Override
    public void showProfile(Map<String, Object> profile) {



        nameInput.setText((String)profile.get("email"));
        continentInput.setText((String)profile.get("name"));
        citiesInput.setText((String)profile.get("cities"));
        parksInput.setText((String)profile.get("parksInput"));
        airportsInput.setText((String)profile.get("airportsInput"));
        hotelsInput.setText((String)profile.get("hotelsInput"));


    }
}
