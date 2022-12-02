package com.countrycomparison.profile;

import android.util.Log;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.MutableDocument;
import com.countrycomparison.util.DatabaseManager;

import java.util.HashMap;
import java.util.Map;

public class UserProfilePresenter implements UserProfileContract.UserActionsListener {
    private UserProfileContract.View mUserProfileView;
    long startTime,execTime;

    public UserProfilePresenter(UserProfileContract.View mUserProfileView) {
        this.mUserProfileView = mUserProfileView;
    }

    // tag::fetchProfile[]
    public void fetchProfile()
    // end::fetchProfile[]
    {

        Database database = DatabaseManager.getDatabase();

        // tag::docfetch[]
        String docId = DatabaseManager.getSharedInstance().getCurrentUserDocId();
        startTime= System.currentTimeMillis();


        if (database != null) {

            Map<String, Object> profile = new HashMap<>(); // <1>

            profile.put("email", DatabaseManager.getSharedInstance().currentUser); // <2>

            Document document = database.getDocument(docId); // <3>

            if (document != null) {
                profile.put("name", document.getString("name")); // <4>
                profile.put("cities", document.getString("cities")); // <4>
                profile.put("parksInput", document.getString("parksInput"));
                profile.put("airportsInput", document.getString("airportsInput"));
                profile.put("hotelsInput", document.getString("hotelsInput"));

            }

            mUserProfileView.showProfile(profile); // <5>
        }
        // end::docfetch[]
        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;
        Log.d("readtime", String.valueOf(DemoClass.executionTime));
    }
    public void deleteProfile() throws CouchbaseLiteException {

        {
            Database database = DatabaseManager.getDatabase();

            // tag::docfetch[]
            String docId = DatabaseManager.getSharedInstance().getCurrentUserDocId();

                Document document = database.getDocument(docId); // <3>

                if (document != null) {
                    database.delete(document);
                }

        }
    }

    // tag::saveProfile[]
    public void saveProfile(Map<String,Object> profile)
    // end::saveProfile[]
    {
        startTime= System.currentTimeMillis();
        Database database = DatabaseManager.getDatabase();

        String docId = DatabaseManager.getSharedInstance().getCurrentUserDocId();

        // tag::docset[]
        MutableDocument mutableDocument = new MutableDocument(docId, profile);
        // end::docset[]

        try {
            // tag::docsave[]
            database.save(mutableDocument);
            // end::docsave[]
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        execTime = System.currentTimeMillis() - startTime;
        DemoClass.executionTime=execTime;
        Log.d("CreateTime", String.valueOf(DemoClass.executionTime));

    }
}
