package com.hunaynr.plant.view_models;

import androidx.lifecycle.ViewModel;
import android.content.Context;
import androidx.databinding.ObservableField;

import com.hunaynr.plant.R;
import com.hunaynr.plant.data.GardenPlanting;
import com.hunaynr.plant.data.Plant;
import com.hunaynr.plant.data.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PlantAndGardenPlantingsViewModel extends ViewModel {

    private final ObservableField<String> imageUrl;
    private final ObservableField<String> plantDate;
    private Plant plant;
    private GardenPlanting gardenPlanting;

    public PlantAndGardenPlantingsViewModel(Context context, PlantAndGardenPlantings plantings) {
        this.plant = plantings.getPlant();
        this.gardenPlanting = plantings.getGardenPlantings().get(0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        String plantDateStr = dateFormat.format(gardenPlanting.plantDate.getTime());

        imageUrl = new ObservableField<String>(plant.getImageUrl());
        plantDate = new ObservableField<String>(
                context.getString(R.string.planted_date, plant.getName(), plantDateStr));
    }


    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public ObservableField<String> getPlantDate() {
        return plantDate;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public GardenPlanting getGardenPlanting() {
        return gardenPlanting;
    }

    public void setGardenPlanting(GardenPlanting gardenPlanting) {
        this.gardenPlanting = gardenPlanting;
    }
}
