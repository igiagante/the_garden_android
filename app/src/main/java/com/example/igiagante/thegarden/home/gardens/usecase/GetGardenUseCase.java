package com.example.igiagante.thegarden.home.gardens.usecase;

import android.support.annotation.NonNull;

import com.example.igiagante.thegarden.core.domain.entity.Garden;
import com.example.igiagante.thegarden.core.executor.PostExecutionThread;
import com.example.igiagante.thegarden.core.executor.ThreadExecutor;
import com.example.igiagante.thegarden.core.repository.managers.GardenRepositoryManager;
import com.example.igiagante.thegarden.core.repository.realm.specification.garden.GardenByIdSpecification;
import com.example.igiagante.thegarden.core.usecase.UseCase;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Ignacio Giagante, on 6/7/16.
 */
public class GetGardenUseCase extends UseCase<String> {

    /**
     * Repository Manager which delegates the actions to the correct repository
     */
    private final GardenRepositoryManager gardenRepositoryManager;

    @Inject
    public GetGardenUseCase(@NonNull GardenRepositoryManager gardenRepositoryManager,
                            @NonNull ThreadExecutor threadExecutor,
                            @NonNull PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.gardenRepositoryManager = gardenRepositoryManager;
        // set repositories order
        this.gardenRepositoryManager.setRepositoriesOrder(getRepositoryOrder());
    }

    @Override
    protected Observable buildUseCaseObservable(String gardenId) {
        Observable<ArrayList<Garden>> query = gardenRepositoryManager.query(new GardenByIdSpecification(gardenId));

        return query.flatMap(list -> Observable.just(list.get(0)));
    }
}
