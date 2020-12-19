package com.hunaynr.plant.workers;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.hunaynr.plant.adapters.PlantAdapter;
import com.hunaynr.plant.data.AppDatabase;
import com.hunaynr.plant.data.GitHubService;
import com.hunaynr.plant.data.Plant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSettings extends AppCompatActivity {
    PlantAdapter adapter;
    public RetrofitSettings(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/hunaynr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Plant>> data = service.listData("plants.json");
        data.enqueue(new Callback<List<Plant>>() {
            @Override
            public void onResponse(Call<List<Plant>> call, Response<List<Plant>> response) {
                List<Plant> plants = response.body();
                AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
                appDatabase.plantDao().insertAll(plants);
                adapter.pojoProcess();
                adapter.updateItems(plants);
            }

            @Override
            public void onFailure(Call<List<Plant>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
