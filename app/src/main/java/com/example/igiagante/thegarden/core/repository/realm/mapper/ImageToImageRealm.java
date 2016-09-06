package com.example.igiagante.thegarden.core.repository.realm.mapper;

import android.support.annotation.NonNull;

import com.example.igiagante.thegarden.core.repository.Mapper;
import com.example.igiagante.thegarden.core.domain.entity.Image;
import com.example.igiagante.thegarden.core.repository.network.Settings;
import com.example.igiagante.thegarden.core.repository.realm.modelRealm.ImageRealm;

import io.realm.Realm;

/**
 * @author Ignacio Giagante, on 28/4/16.
 */
public class ImageToImageRealm implements Mapper<Image, ImageRealm> {

    private final Realm realm;

    public ImageToImageRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public ImageRealm map(@NonNull Image image) {

        ImageRealm imageRealm = realm.createObject(ImageRealm.class);
        imageRealm.setId(image.getId());
        copy(image, imageRealm);

        return imageRealm;
    }

    @Override
    public ImageRealm copy(@NonNull Image image, @NonNull ImageRealm imageRealm) {

        String imageUrl = image.getUrl().replace(Settings.DOMAIN_MASK, "");
        imageUrl = imageUrl.replace(Settings.DOMAIN, "");

        String thumbnailUrl = image.getThumbnailUrl().replace(Settings.DOMAIN_MASK, "");
        thumbnailUrl = thumbnailUrl.replace(Settings.DOMAIN, "");

        imageRealm.setName(image.getName());
        imageRealm.setUrl(imageUrl);
        imageRealm.setThumbnailUrl(thumbnailUrl);
        imageRealm.setType(image.getType());
        imageRealm.setSize(image.getSize());
        imageRealm.setMain(image.isMain());

        return imageRealm;
    }
}
