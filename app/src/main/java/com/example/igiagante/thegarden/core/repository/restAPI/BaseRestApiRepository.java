package com.example.igiagante.thegarden.core.repository.restAPI;

import android.content.Context;
import android.util.Log;

import com.example.igiagante.thegarden.core.repository.Repository;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * @author Ignacio Giagante, on 3/7/16.
 */
public class BaseRestApiRepository<T> {

    private static final String TAG = BaseRestApiRepository.class.getSimpleName();

    protected Context mContext;

    public BaseRestApiRepository(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * Go to the api and then continue with the DB
     * @param apiResult after call api
     * @param item the item to be persisted or updated
     * @param repository DB
     * @param update indicate if the transaction is about an updating
     */
    protected T execute(Observable<T> apiResult, Class repository, boolean update) {

        // get Data From api
        List<T> listOne = new ArrayList<>();
        apiResult.subscribeOn(Schedulers.io()).toBlocking().subscribe(garden1 -> listOne.add(garden1));

        // update the garden into database
        Repository dataBase = null;

        try {
            Constructor<?> cons = repository.getConstructor(Context.class);
            dataBase = (Repository) cons.newInstance(mContext);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        } catch (InstantiationException ei) {
            Log.e(TAG, ei.getMessage());
            ei.printStackTrace();
        }

        Observable<T> dbResult = null;

        if(update) {
            dbResult = dataBase.update(listOne.get(0));
        } else {
            dbResult = dataBase.add(listOne.get(0));
        }

        List<T> list = new ArrayList<>();
        dbResult.toBlocking().subscribe(gardenId -> list.add(gardenId));

        return list.get(0);
    }

    /**
     * Add images to the request body which is a {@link okhttp3.MultipartBody.Builder}
     *
     * @param builder MultipartBody.Builder
     * @param files   files to be added in builder
     * @return builder
     */
    protected MultipartBody.Builder addImagesToRequestBody(MultipartBody.Builder builder, ArrayList<File> files) {

        for (int i = 0, size = files.size(); i < size; i++) {
            String mediaType = "image/" + getMediaType(files.get(i));
            RequestBody image = RequestBody.create(MediaType.parse(mediaType), files.get(i));
            builder.addFormDataPart(files.get(i).getName(), files.get(i).getName(), image);
        }

        return builder;
    }

    /**
     * Get media type from file
     *
     * @param file File to be processed
     * @return type
     */
    private String getMediaType(File file) {

        String type = null;
        String[] chain = null;

        if (file != null) {
            chain = file.getAbsolutePath().split("\\.");
        }

        if (chain != null && chain.length > 0) {
            type = chain[1];
        }
        return type;
    }
}
