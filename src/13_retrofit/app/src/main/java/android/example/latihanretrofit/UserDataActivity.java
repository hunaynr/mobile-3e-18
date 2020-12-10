package android.example.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.example.latihanretrofit.adapter.DataAdapter;
import android.example.latihanretrofit.databinding.ActivityUserDataBinding;
import android.example.latihanretrofit.models.Repo;
import android.example.latihanretrofit.services.GitHubService;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_data);
        ActivityUserDataBinding activityUserDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_data);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        dataAdapter = new DataAdapter();

        String data = getIntent().getExtras().getString("keyName");

        ProgressDialog mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos(data);
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> repoList) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                if(repoList.body() == null) {
                    Intent intent = new Intent(getBaseContext(), ResultActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.e("successResult", String.valueOf(repoList.body()));
                    List<Repo> repo = repoList.body();
                    dataAdapter.setData(repo);
                    recyclerView.setAdapter(dataAdapter);
                }

            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}