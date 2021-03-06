package com.example.igiagante.thegarden.creation.plants.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.example.igiagante.thegarden.R;
import com.example.igiagante.thegarden.core.domain.entity.Image;
import com.example.igiagante.thegarden.core.presentation.BaseActivity;
import com.example.igiagante.thegarden.core.ui.CirclePageIndicator;
import com.example.igiagante.thegarden.creation.plants.presentation.adapters.CarouselAdapter;
import com.example.igiagante.thegarden.creation.plants.presentation.fragments.CarouselFragment;
import com.example.igiagante.thegarden.creation.plants.presentation.fragments.PhotoGalleryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ignacio Giagante, on 23/5/16.
 */
public class CarouselActivity extends BaseActivity implements CarouselFragment.OnDeleteImageInCarousel {

    public static final String PICTURE_SELECTED_KEY = "PICTURE_SELECTED";

    private CirclePageIndicator mIndicator;
    private ArrayList<String> urls;
    private CarouselAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carousel_activity);

        Intent intent = getIntent();
        int position = intent.getIntExtra(PICTURE_SELECTED_KEY, 0);
        ArrayList<Image> images = intent.getParcelableArrayListExtra(PhotoGalleryFragment.IMAGES_KEY);

        if (images != null) {
            urls = getImagesFilesPaths(images);
        }

        ViewPager mPager = (ViewPager) findViewById(R.id.viewpager_carousel);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

        mAdapter = new CarouselAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mAdapter.setUrlsImages(urls);

        //add circle indicator
        mIndicator.setViewPager(mPager, position);
        setupCircleIndicator();
    }

    /**
     * Add style to circle indicator
     */
    private void setupCircleIndicator() {
        final float density = getResources().getDisplayMetrics().density;
        mIndicator.setFillColor(0xFFFFFFFF);
        mIndicator.setStrokeColor(0xFFFFFFFF);
        mIndicator.setStrokeWidth(2);
        mIndicator.setRadius(6 * density);
    }

    @Override
    public void deleteImageInCarousel(int position) {
        urls.remove(position);
        mAdapter.setUrlsImages(urls);
    }

    @Override
    public void onBackPressed() {

        Intent data = new Intent();

        data.putParcelableArrayListExtra(PhotoGalleryFragment.IMAGES_KEY, createImageList());
        setResult(Activity.RESULT_OK, data);

        finish();
    }

    private ArrayList<Image> createImageList() {

        ArrayList<Image> images = new ArrayList<>();

        for (int i = 0; i < urls.size(); i++) {
            Image image = new Image();
            image.setUrl(urls.get(i));
            images.add(image);
        }

        return images;
    }

    /**
     * Get all the images path from the parcelable image list.
     *
     * @param images Images
     * @return paths images folder path
     */
    private ArrayList<String> getImagesFilesPaths(List<Image> images) {

        ArrayList<String> paths = new ArrayList<>();

        for (int i = 0; i < images.size(); i++) {
            paths.add(images.get(i).getUrl());
        }

        return paths;
    }
}
