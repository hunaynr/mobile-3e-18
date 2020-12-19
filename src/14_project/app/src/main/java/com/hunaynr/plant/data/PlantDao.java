package com.hunaynr.plant.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PlantDao {
    @Insert(onConflict = REPLACE)
    void insertAll(List<Plant> plants);

    @Query("SELECT * FROM plants ORDER BY name")
    LiveData<List<Plant>> getPlants();

    @Query("SELECT * FROM plants WHERE id = :plantId")
    LiveData<Plant> getPlant(String plantId);

    @Query("SELECT * FROM plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    LiveData<List<Plant>> getPlantsByGrowZoneNumber(int growZoneNumber);

    @Query("SELECT * FROM plants WHERE name = :name")
    LiveData<List<Plant>> getPlantsByName(String name);

    @Query("SELECT * FROM plants ORDER BY name ASC")
    LiveData<List<Plant>> getPlantsAsc();

    @Query("SELECT * FROM plants ORDER BY name DESC")
    LiveData<List<Plant>> getPlantsDesc();
}
