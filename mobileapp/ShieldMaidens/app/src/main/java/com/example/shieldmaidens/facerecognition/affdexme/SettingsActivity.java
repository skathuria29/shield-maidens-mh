package com.example.shieldmaidens.facerecognition.affdexme;

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import com.example.shieldmaidens.R;

import java.util.List;

//Activity which works with standard Android Preferences framework to display the preference headers.
public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        ActionBar actionBar = getActionBar();
        actionBar.setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        //actionBar.setDisplayHomeAsUpEnabled(true);

    }

    /*
    @Override
    public boolean onNavigateUp() {
        this.onBackPressed();
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Populate the activity with the top-level headers.
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_headers, target);
    }

    //Boilerplate method, required by Android API
    @Override
    protected boolean isValidFragment(String fragmentName) {
        if (SettingsFragment.class.getName().equals(fragmentName) || MetricSelectionFragment.class.getName().equals(fragmentName)) {
            return(true);
        }
        return(false);
    }

    //This fragment shows the preferences for the first header.
    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.settings_preferences);
        }
    }

    //The second fragment is defined in a separate file.

}