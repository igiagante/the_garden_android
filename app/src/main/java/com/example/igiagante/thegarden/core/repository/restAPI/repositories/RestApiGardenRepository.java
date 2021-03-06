package com.example.igiagante.thegarden.core.repository.restAPI.repositories;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.igiagante.thegarden.core.Session;
import com.example.igiagante.thegarden.core.domain.entity.Garden;
import com.example.igiagante.thegarden.core.repository.Repository;
import com.example.igiagante.thegarden.core.repository.Specification;
import com.example.igiagante.thegarden.core.repository.network.ServiceFactory;
import com.example.igiagante.thegarden.core.repository.realm.GardenRealmRepository;
import com.example.igiagante.thegarden.core.repository.restAPI.services.GardenRestApi;

import java.util.List;

import rx.Observable;

/**
 * @author Ignacio Giagante, on 3/7/16.
 */
public class RestApiGardenRepository extends BaseRestApiRepository<Garden> implements Repository<Garden> {

    private final GardenRestApi api;

    public RestApiGardenRepository(Context context, Session session) {
        super(context, session);
        api = ServiceFactory.createRetrofitService(GardenRestApi.class, session.getToken());
    }

    @Override
    public Observable<Garden> getById(String id) {
        return api.getGarden(id);
    }

    @Override
    public Observable<Garden> getByName(String name) {
        return null;
    }

    @Override
    public Observable<Garden> add(Garden garden) {
        return addOrUpdate(garden, false);
    }

    @Override
    public Observable<Integer> add(Iterable<Garden> gardens) {
        return null;
    }

    @Override
    public Observable<Garden> update(Garden garden) {
        return addOrUpdate(garden, true);
    }

    @NonNull
    private Observable addOrUpdate(Garden garden, boolean update) {

        Observable<Garden> apiResult;

        if (update) {
            apiResult = api.updateGarden(garden.getId(), garden).asObservable();
        } else {
            apiResult = api.createGarden(garden).asObservable();
        }

        Garden result = execute(apiResult, GardenRealmRepository.class, update);

        return Observable.just(result);
    }

    @Override
    public Observable<Integer> remove(String gardenId) {
        return null;
    }

    public Observable<Integer> remove(String gardenId, String userId) {
        return api.deleteGarden(gardenId, userId).asObservable()
                .map(response -> response.isSuccessful() ? 1 : -1);
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Observable<List<Garden>> query(Specification specification) {
        return api.getGardens();
    }

    public Observable<List<Garden>> getGardensByUser(String username) {
        return api.getGardensByUserName(username);
    }
}
