package com.hunaynr.plant.view_models;

import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.hunaynr.plant.data.Plant;
import com.hunaynr.plant.data.PlantRepository;

import java.util.List;

public class PlantListViewModel extends ViewModel {
    private PlantRepository plantRepository;
    private int NO_GROW_ZONE = -1;
    private String searchParam = "";
    private int asc = 1;
    String choice = "";
    private MutableLiveData<Integer> growZoneNumber = new MutableLiveData<>();
    private MediatorLiveData<List<Plant>> plantList = new MediatorLiveData<>();
    private MutableLiveData<String> searchList = new MutableLiveData<>();
    private MediatorLiveData<List<Plant>> searchPlantList = new MediatorLiveData<>();
    private MutableLiveData<Integer> sortList = new MutableLiveData<>();
    private MediatorLiveData<List<Plant>> sortPlantList = new MediatorLiveData<>();

    PlantListViewModel(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
        growZoneNumber.setValue(NO_GROW_ZONE);
        searchList.setValue(searchParam);
        sortList.setValue(asc);

//        LiveData<List<Plant>> livePlantList = Transformations.switchMap(growZoneNumber, (gz_no) -> {
//            if (gz_no == NO_GROW_ZONE) {
//                return plantRepository.getPlants();
//            } else {
//                return plantRepository.getPlantsByGrowZoneNumber(gz_no);
//            }
//        });
//
//        plantList.addSource(livePlantList, plants -> plantList.setValue(plants));

        LiveData<List<Plant>> sortPlantListName = Transformations.switchMap(sortList, (sort_param) -> {
            if (sort_param == asc) {
                return plantRepository.getPlantsAsc();
            } else {
                return plantRepository.getPlantsDesc();
            }
        });

        sortPlantList.addSource(sortPlantListName, plantsSort -> sortPlantList.setValue(plantsSort));

        LiveData<List<Plant>> livePlantListName = Transformations.switchMap(searchList, (srch_lst) -> {
            if (srch_lst == "") {
                return plantRepository.getPlants();
            } else {
                return plantRepository.getPlantsByName(srch_lst);
            }
        });

        searchPlantList.addSource(livePlantListName, plantsSearch -> searchPlantList.setValue(plantsSearch));

    }


    public MediatorLiveData<List<Plant>> getData() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];//maybe this number needs to be corrected
        String methodName = e.getMethodName();
        Log.e("methodCallBack", methodName);
            return searchPlantList;
    }

    public MediatorLiveData<List<Plant>> getDataAsc() { return sortPlantList; }

//    public void setGrowZoneNumber(int no) {
//        growZoneNumber.setValue(no);
//    }
//
//    public void clearGrowZoneNumber() {
//        growZoneNumber.setValue(NO_GROW_ZONE);
//    }
//
//    public boolean isFiltered() {
//        return growZoneNumber.getValue() != NO_GROW_ZONE;
//    }

    public void setSearchList(String name) {
        searchList.setValue(name);
    }

    public void clearSearchList() {
        searchList.setValue("");
    }

    public boolean isSearch() { return searchList.getValue() != searchParam; }

    public MediatorLiveData<List<Plant>> getPlantsAsc() { return sortPlantList; }

    public void setSortList(int number) { sortList.setValue(number); }

    public void clearSortList() { sortList.setValue(0); }

    public boolean isSort() { return sortList.getValue() != asc; }

}
