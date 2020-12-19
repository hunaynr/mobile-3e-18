package com.hunaynr.plant.ui;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hunaynr.plant.R;
import com.hunaynr.plant.adapters.GardenPlantAdapter;
import com.hunaynr.plant.data.GardenPlanting;
import com.hunaynr.plant.data.PlantAndGardenPlantings;
import com.hunaynr.plant.utils.InjectorUtils;
import com.hunaynr.plant.view_models.GardenPlantingListViewModel;
import com.hunaynr.plant.view_models.GardenPlantingListViewModelFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GardenFragment extends Fragment {

    List<PlantAndGardenPlantings> plantingsList = new ArrayList<>();
    RecyclerView mRecyclerView;
    TextView mTextViewEmptyGarden;

    public GardenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_garden, container, false);
        GardenPlantAdapter adapter = new GardenPlantAdapter(plantingsList);

        mTextViewEmptyGarden = view.findViewById(R.id.empty_garden);
        mRecyclerView = view.findViewById(R.id.garden_list);
        mRecyclerView.setAdapter(adapter);
        subscribeUI(adapter);
        return view;
    }

    private void subscribeUI(GardenPlantAdapter adapter) {
        GardenPlantingListViewModelFactory factory = InjectorUtils.provideGardenPlantListViewModelFactory(getContext());
        GardenPlantingListViewModel viewModel = ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);
        viewModel.gardenPlantings.observe(getViewLifecycleOwner(), new Observer<List<GardenPlanting>>() {
            @Override
            public void onChanged(@Nullable List<GardenPlanting> gardenPlantings) {
                if (gardenPlantings != null && !gardenPlantings.isEmpty()) {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mTextViewEmptyGarden.setVisibility(View.GONE);
                }else {
                    mRecyclerView.setVisibility(View.GONE);
                    mTextViewEmptyGarden.setVisibility(View.VISIBLE);
                }
            }
        });

        viewModel.plantAndGardenPlantings.observe(getViewLifecycleOwner(), new Observer<List<PlantAndGardenPlantings>>() {
            @Override
            public void onChanged(@Nullable List<PlantAndGardenPlantings> gardenPlantingsList) {
                if (gardenPlantingsList != null && !gardenPlantingsList.isEmpty()) {
                    adapter.updateList(gardenPlantingsList);
                }
            }
        });
    }



}
