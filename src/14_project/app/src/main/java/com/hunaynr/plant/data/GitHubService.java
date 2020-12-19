package com.hunaynr.plant.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("plants/master/{endpoint}")
    Call<List<Plant>> listData(@Path("endpoint") String point);
}
