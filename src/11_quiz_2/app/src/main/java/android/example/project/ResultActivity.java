package android.example.project;

import android.content.Context;
import android.content.Intent;
import android.example.project.databinding.ActivityResultBinding;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class ResultActivity extends AppCompatActivity {
    public static final String arg_param = "key_param";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityResultBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_result);

        Intent i = getIntent();
        String param = i.getStringExtra(arg_param);
        ResultActivityVM viewModel = new ResultActivityVM(param);
        binding.setVm(viewModel);

        Button changeFragment = findViewById(R.id.btnChange);
        changeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, ColorFragment.class));
            }
        });
    }

    public static void startThisActivity(Context context, String param) {
        Intent intent = new Intent(context, ResultActivity.class);
        intent.putExtra(arg_param, param);
        context.startActivity(intent);
    }
}
