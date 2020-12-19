package com.hunaynr.plant.view_models;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.hunaynr.plant.data.GardenPlantingRepository;
import com.hunaynr.plant.data.PlantRepository;

public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public PlantDetailViewModelFactory(PlantRepository plantRepository,
                                       GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}
