package com.example.igiagante.thegarden.creation.plants.presentation.views;

import com.example.igiagante.thegarden.core.domain.entity.Image;
import com.example.igiagante.thegarden.core.presentation.mvp.IView;

import java.util.Collection;

/**
 * @author Ignacio Giagante, on 1/6/16.
 */
public interface PhotoGalleryView extends IView {

    /**
     * Add some images to the Builder plant.
     * @param images images collection
     */
    void addImagesToBuilder(Collection<Image> images);
}