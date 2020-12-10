package android.example.latihanretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.example.latihanretrofit.adapter.DataAdapter;
import android.example.latihanretrofit.databinding.ActivityMainBinding;
import android.example.latihanretrofit.models.Repo;
import android.example.latihanretrofit.services.GitHubService;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    String getParam;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        button = (Button) findViewById(R.id.btn);
        editText = (EditText) findViewById(R.id.param);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParam = editText.getText().toString();
                Log.e("edit", getParam);
                Intent intent = new Intent(getBaseContext(), UserDataActivity.class);
                intent.putExtra("keyName",getParam);
                startActivity(intent);
            }
        });

    }
}