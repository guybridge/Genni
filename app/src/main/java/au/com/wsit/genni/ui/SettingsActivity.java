package au.com.wsit.genni.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import au.com.wsit.genni.R;

/**
 * Created by guyb on 13/08/17.
 */

public class SettingsActivity extends PreferenceActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.activity_settings);
    }
}
