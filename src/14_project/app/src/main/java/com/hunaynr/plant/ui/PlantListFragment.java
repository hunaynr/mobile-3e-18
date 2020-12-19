package com.hunaynr.plant.ui;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.hunaynr.plant.R;
import com.hunaynr.plant.adapters.PlantAdapter;
import com.hunaynr.plant.data.Plant;
import com.hunaynr.plant.utils.InjectorUtils;
import com.hunaynr.plant.view_models.PlantListViewModel;
import com.hunaynr.plant.view_models.PlantListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class PlantListFragment extends Fragment {

    private List<Plant> plantsList = new ArrayList<>();
    private PlantListViewModel viewModel;
    LayoutInflater mInflater;
    ViewGroup container;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plant_list, container, false);
//        mInflater = inflater;
//        this.container = container;

        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(getActivity());
        viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

        PlantAdapter adapter = new PlantAdapter(plantsList);
        RecyclerView recyclerView = view.findViewById(R.id.plant_list);
        recyclerView.setAdapter(adapter);
        subscribeUI(adapter);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_plant_list, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                updateSearchData(s);
                Log.e("search1,",s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                updateSearchData(s);
//                Log.e("search2,",s);
                return false;
            }
        });

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == R.id.ic_sort){
//            updateSortData();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    private void updateData() {
//        if(viewModel.isFiltered()){
//            viewModel.clearGrowZoneNumber();
//        }else{
//            viewModel.setGrowZoneNumber(9);
//        }
//    }

    private void updateSearchData(String s) {
        if(viewModel.isSearch()){
            viewModel.clearSearchList();
        }else{
            viewModel.setSearchList(s);
        }
    }

    private void updateSortData() {
        if(viewModel.isSort()){
            viewModel.setSortList(1);
        }else{
            viewModel.clearSortList();
        }
    }

    private void subscribeUI(PlantAdapter adapter) {
        viewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(@Nullable List<Plant> plants) {
                adapter.updateItems(plants);
            }
        });
    }

}
