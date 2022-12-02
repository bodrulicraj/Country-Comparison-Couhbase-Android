package com.countrycomparison.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.login.LoginActivity;
import com.countrycomparison.util.DatabaseManager;

import java.util.HashMap;
import java.util.Map;

public class UserProfileActivity extends AppCompatActivity implements UserProfileContract.View {

    private UserProfileContract.UserActionsListener mActionListener;


    EditText nameInput;
    EditText usernameInput;
    EditText emailInput;
    EditText addressInput;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);



        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.usernameInput);
        addressInput = findViewById(R.id.addressInput);

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
        profile.put("name", nameInput.getText().toString());
        profile.put("email", emailInput.getText().toString());
        profile.put("address", addressInput.getText().toString());
        DatabaseManager dbMgr = DatabaseManager.getSharedInstance();
        dbMgr.initCouchbaseLite(getApplicationContext());
        dbMgr.openOrCreateDatabaseForUser(getApplicationContext(), emailInput.getText().toString());
        mActionListener.saveProfile(profile);

        Toast.makeText(this, "Successfully updated profile!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showProfile(Map<String, Object> profile) {
        nameInput.setText((String)profile.get("name"));
        emailInput.setText((String)profile.get("email"));
        addressInput.setText((String)profile.get("address"));

        }
    }

