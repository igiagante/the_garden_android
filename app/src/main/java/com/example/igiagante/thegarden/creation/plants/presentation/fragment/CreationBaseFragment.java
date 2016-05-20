package com.example.igiagante.thegarden.creation.plants.presentation.fragment;

import android.support.v4.view.ViewPager;

import com.example.igiagante.thegarden.core.presentation.BaseFragment;

/**
 * @author Ignacio Giagante, on 20/5/16.
 */
public class CreationBaseFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    protected int myPosition;


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position == myPosition) {
            move();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * Notify to the builder that the fragment have some data for saving.
     */
    protected void move(){}
}
