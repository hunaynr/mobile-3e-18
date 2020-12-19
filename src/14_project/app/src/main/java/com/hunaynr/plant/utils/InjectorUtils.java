package com.hunaynr.plant.utils;

import android.content.Context;

import com.hunaynr.plant.data.AppDatabase;
import com.hunaynr.plant.data.GardenPlantingRepository;
import com.hunaynr.plant.data.PlantRepository;
import com.hunaynr.plant.view_models.GardenPlantingListViewModelFactory;
import com.hunaynr.plant.view_models.PlantDetailViewModelFactory;
import com.hunaynr.plant.view_models.PlantListViewModelFactory;

public class InjectorUtils {


    private static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao());
    }

    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao());
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Context context) {
        PlantRepository repository = getPlantRepository(context);
        PlantListViewModelFactory vmFactory = new PlantListViewModelFactory(repository);
        return vmFactory;
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantListViewModelFactory(Context context) {
        GardenPlantingRepository gardenPlantingRepository = getGardenPlantingRepository(context);
        return new GardenPlantingListViewModelFactory(gardenPlantingRepository);
    }

    public static PlantDetailViewModelFactory providePlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context), getGardenPlantingRepository(context), plantId);
    }
}
