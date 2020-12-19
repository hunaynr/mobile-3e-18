package com.hunaynr.plant.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hunaynr.plant.data.GardenPlanting;
import com.hunaynr.plant.data.GardenPlantingRepository;
import com.hunaynr.plant.data.Plant;
import com.hunaynr.plant.data.PlantRepository;

public class PlantDetailViewModel extends ViewModel {
    private final LiveData<GardenPlanting> gardenPlantingForPlant;
    GardenPlantingRepository gardenPlantingRepository;
    LiveData<Boolean> isPlanted;
    public LiveData<Plant> plant;
    String plantId;

    public PlantDetailViewModel(PlantRepository plantRepository,
                                GardenPlantingRepository gardenPlantingRepository, String plantId){
        this.plantId = plantId;
        this.plant = plantRepository.getPlant(plantId);
        this.gardenPlantingRepository = gardenPlantingRepository;
        gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
    }

    public void addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId);
    }

    public LiveData<Plant> getPlant(){
        return plant;
    }

//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        viewModelScope.cancel();
//    }
}
