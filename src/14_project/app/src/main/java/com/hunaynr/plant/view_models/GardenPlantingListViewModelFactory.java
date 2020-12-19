package com.hunaynr.plant.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.hunaynr.plant.data.GardenPlantingRepository;

public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    GardenPlantingRepository gardenPlantingRepository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        this.gardenPlantingRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(gardenPlantingRepository);
    }
}
