package com.example.igiagante.thegarden.core.presentation;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.igiagante.thegarden.core.AndroidApplication;
import com.example.igiagante.thegarden.core.di.components.ApplicationComponent;
import com.example.igiagante.thegarden.core.di.modules.ActivityModule;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * @author Ignacio Giagante, on 18/4/16.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication)getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    protected boolean isLandScape(){
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
