package com.countrycomparison.profile;

import static com.countrycomparison.profile.DemoClass.executionTime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.couchbase.countrycomparison.R;
import com.countrycomparison.login.LoginActivity;
import com.countrycomparison.util.DatabaseManager;

public class DeleteActivity extends AppCompatActivity {

    EditText usernameInput;
    EditText eTexecTme;
   // EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        usernameInput = findViewById(R.id.usernameInputRead);
        eTexecTme= findViewById(R.id.exectimeETD);
        //passwordInput = findViewById(R.id.passwordInput);
    }

    public void onDeleteTappedTapped(View view) {
        eTexecTme.setText(executionTime+" ms");
        Log.d("timereadmain", String.valueOf(executionTime));
        Toast.makeText(this, "Successfully Deleted !", Toast.LENGTH_SHORT).show();

        }
    public void onClosedTapped(View view) {
        DatabaseManager.getSharedInstance().closeDatabaseForUser();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
