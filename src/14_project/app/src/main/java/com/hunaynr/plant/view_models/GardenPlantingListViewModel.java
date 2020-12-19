package com.hunaynr.plant.view_models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hunaynr.plant.data.GardenPlanting;
import com.hunaynr.plant.data.GardenPlantingRepository;
import com.hunaynr.plant.data.PlantAndGardenPlantings;

import java.util.ArrayList;
import java.util.List;

public class GardenPlantingListViewModel extends ViewModel {

    public LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;
    public LiveData<List<GardenPlanting>> gardenPlantings;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {
        gardenPlantings = gardenPlantingRepository.getGardenPlants();
        plantAndGardenPlantings =
                Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings(), plantings -> {
                    List<PlantAndGardenPlantings> plantingsListNew = new ArrayList<>();
                    for (int i = 0; i < plantings.size(); i++) {
                        if (plantings.get(i).getGardenPlantings() != null && !plantings.get(i).getGardenPlantings().isEmpty()) {
                            plantingsListNew.add(plantings.get(i));
                        }
                    }
                    return plantingsListNew;
                });
    }


}
