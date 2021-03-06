package com.example.igiagante.thegarden.core.repository.di.components;

import com.example.igiagante.thegarden.core.di.PerActivity;
import com.example.igiagante.thegarden.core.di.components.ApplicationComponent;
import com.example.igiagante.thegarden.core.di.modules.ActivityModule;
import com.example.igiagante.thegarden.core.repository.di.modules.GardenRepositoryModule;
import com.example.igiagante.thegarden.core.repository.managers.GardenRepositoryManager;

import dagger.Component;

/**
 * @author Ignacio Giagante, on 5/7/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, GardenRepositoryModule.class})
public interface GardenRepositoryComponent {
    GardenRepositoryManager gardenRepositoryManager();
}
