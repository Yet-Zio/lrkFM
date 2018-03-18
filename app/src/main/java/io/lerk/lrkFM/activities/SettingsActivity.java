package io.lerk.lrkFM.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

import org.jraf.android.alibglitch.GlitchEffect;

import java.util.List;

import io.lerk.lrkFM.R;

/**
 */
public class SettingsActivity extends AppCompatPreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || UIPreferenceFragment.class.getName().equals(fragmentName)
                || AnalyticsPreferenceFragment.class.getName().equals(fragmentName);
    }


    public static class GeneralPreferenceFragment extends PreferenceFragment {

        private static final String TAG = GeneralPreferenceFragment.class.getCanonicalName();

        private static int c=0;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_general);
            setHasOptionsMenu(true);

            try{Preference app_version=findPreference("app_version");app_version.setSummary(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);app_version.setOnPreferenceClickListener(preference->{c++;if(c%8==0){GlitchEffect.showGlitch(getActivity());}return true;});}catch(PackageManager.NameNotFoundException e){Log.e(TAG,"Unable to get App version!",e);}

            Preference context_for_ops_toast = findPreference("context_for_ops_toast");
            SwitchPreference context_for_ops = (SwitchPreference) findPreference("context_for_ops");

            context_for_ops_toast.setEnabled(context_for_ops.isChecked());
            context_for_ops.setOnPreferenceChangeListener((preference, newValue) -> {
                context_for_ops_toast.setEnabled((Boolean) newValue);
                return true;
            });
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), PreferenceActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class AnalyticsPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_analytics);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), PreferenceActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    public static class UIPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_data_ui);
            setHasOptionsMenu(true);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == android.R.id.home) {
                startActivity(new Intent(getActivity(), PreferenceActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
