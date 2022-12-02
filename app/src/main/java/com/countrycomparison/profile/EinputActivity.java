package com.countrycomparison.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.util.DatabaseManager;

public class EinputActivity extends AppCompatActivity {

    EditText usernameInput;
   // EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einput);

        usernameInput = findViewById(R.id.usernameInputRead);
        //passwordInput = findViewById(R.id.passwordInput);
    }

    public void onLoginTapped(View view) {
        if (usernameInput.length() > 0) {
            DatabaseManager dbMgr = DatabaseManager.getSharedInstance();
            dbMgr.initCouchbaseLite(getApplicationContext());
            dbMgr.openOrCreateDatabaseForUser(getApplicationContext(), usernameInput.getText().toString());

            Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
