package com.example.igiagante.thegarden.core.repository.di.modules;

import android.content.Context;

import com.example.igiagante.thegarden.core.Session;
import com.example.igiagante.thegarden.core.di.PerActivity;
import com.example.igiagante.thegarden.core.repository.managers.PlantRepositoryManager;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ignacio Giagante, on 8/6/16.
 */
@Module
public class PlantRepositoryModule {

    @Provides
    @PerActivity
    PlantRepositoryManager providePlantRepositoryManager(Context context, Session session) {
        return new PlantRepositoryManager(context, session);
    }
}
