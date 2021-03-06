package com.example.igiagante.thegarden.home.gardens.usecase;

import android.support.annotation.NonNull;

import com.example.igiagante.thegarden.core.executor.PostExecutionThread;
import com.example.igiagante.thegarden.core.executor.ThreadExecutor;
import com.example.igiagante.thegarden.core.repository.managers.GardenRepositoryManager;
import com.example.igiagante.thegarden.core.repository.realm.specification.garden.GardenSpecification;
import com.example.igiagante.thegarden.core.usecase.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * @author Ignacio Giagante, on 3/7/16.
 */
public class GetGardensUseCase extends UseCase<String> {

    /**
     * Repository Manager which delegates the actions to the correct repository
     */
    private final GardenRepositoryManager gardenRepositoryManager;

    @Inject
    public GetGardensUseCase(@NonNull GardenRepositoryManager gardenRepositoryManager,
                             @NonNull ThreadExecutor threadExecutor,
                             @NonNull PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.gardenRepositoryManager = gardenRepositoryManager;
        // set repositories order
        this.gardenRepositoryManager.setRepositoriesOrder(getRepositoryOrder());
    }

    @Override
    protected Observable buildUseCaseObservable(String username) {
        GardenSpecification gardenSpecification = new GardenSpecification();
        return gardenRepositoryManager.query(gardenSpecification);
    }

    @Override
    protected void setRepositoryOrder() {
        repositoryOrder.add(LOCAL_REPOSITORY, REMOTE_REPOSITORY);
    }
}
