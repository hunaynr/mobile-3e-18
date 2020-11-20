package android.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.example.project.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public MainActivity mActivity;
    Button changeFragment;
    Boolean condition = true;
    ResultActivity resultActivity = new ResultActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        final MainActivityVM viewModel = new MainActivityVM(this);
        setContentView(R.layout.activity_main);
        binding.setVm(viewModel);

        Button button = findViewById(R.id.btnSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

//        changeFragment = findViewById(R.id.btn2orAll);
//        changeFragment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, ColorFragment.class));
//            }
//        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChange:
                if(condition) {
                    ColorFragment color = new ColorFragment();
                    FragmentManager FM = getSupportFragmentManager();
                    FragmentTransaction FT = FM.beginTransaction();
                    FT.commit();
                    condition = false;
                }
        }
    }
}