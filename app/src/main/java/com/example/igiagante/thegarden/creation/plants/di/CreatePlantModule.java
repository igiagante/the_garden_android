package com.example.igiagante.thegarden.creation.plants.di;

import com.example.igiagante.thegarden.core.di.PerActivity;
import com.example.igiagante.thegarden.core.usecase.UseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.ExistPlantUseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.GetAttributesUseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.GetFlavorsUseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.GetImagesUseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.GetPlaguesUseCase;
import com.example.igiagante.thegarden.creation.plants.usecase.SavePlantUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ignacio Giagante, on 19/5/16.
 */
@Module
public class CreatePlantModule {

    public CreatePlantModule() {}

    @Provides
    @PerActivity
    @Named("images")
    UseCase provideGetImagesUseCase(GetImagesUseCase images) {
        return images;
    }

    @Provides
    @PerActivity
    @Named("flavors")
    UseCase provideGetFlavorsUseCase(GetFlavorsUseCase flavors) {
        return flavors;
    }

    @Provides
    @PerActivity
    @Named("attributes")
    UseCase provideGetAttributesUseCase(GetAttributesUseCase attributes) {
        return attributes;
    }

    @Provides
    @PerActivity
    @Named("plagues")
    UseCase provideGetPlaguesUseCase(GetPlaguesUseCase plagues) {
        return plagues;
    }

    @Provides
    @PerActivity
    @Named("savePlant")
    UseCase provideSavePlantUseCase(SavePlantUseCase savePlant) {
        return savePlant;
    }

    @Provides
    @PerActivity
    @Named("existPlant")
    UseCase provideExistPlantUseCase(ExistPlantUseCase existPlant) {
        return existPlant;
    }
}
