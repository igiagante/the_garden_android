package com.example.igiagante.thegarden.repositoryImpl.realm.mapper;

import com.example.igiagante.thegarden.core.repository.Mapper;
import com.example.igiagante.thegarden.plants.domain.entity.Image;
import com.example.igiagante.thegarden.plants.repository.realm.ImageRealm;

import io.realm.Realm;

/**
 * Created by igiagante on 28/4/16.
 */
public class ImageToImageRealm implements Mapper<Image, ImageRealm> {

    private final Realm realm;

    public ImageToImageRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public ImageRealm map(Image image) {

        ImageRealm imageRealm = realm.createObject(ImageRealm.class);
        imageRealm.setId(image.getId());
        imageRealm.setName(image.getName());
        imageRealm.setUrl(image.getUrl());
        imageRealm.setThumbnailUrl(image.getThumbnailUrl());
        imageRealm.setType(image.getType());
        imageRealm.setSize(image.getSize());
        imageRealm.setMain(image.isMain());

        return imageRealm;
    }
}
