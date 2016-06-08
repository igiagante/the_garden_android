package com.example.igiagante.thegarden.core.repository.di.components;

import com.example.igiagante.thegarden.core.di.PerActivity;
import com.example.igiagante.thegarden.core.di.components.ApplicationComponent;
import com.example.igiagante.thegarden.core.di.modules.ActivityModule;
import com.example.igiagante.thegarden.core.repository.di.modules.PlantModule;
import com.example.igiagante.thegarden.core.repository.managers.PlantRepositoryManager;

import dagger.Component;

/**
 * @author Ignacio Giagante, on 8/6/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlantModule.class})
public interface PlantComponent {

    PlantRepositoryManager plantRepositoryManager();
}
