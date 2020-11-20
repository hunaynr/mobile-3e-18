package android.example.project;

import android.view.View;

import androidx.databinding.ObservableField;

public class MainActivityVM {
    public ObservableField<String> bindTextInputParam = new ObservableField<>();
    public MainActivity mActivity;

    public MainActivityVM(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    public void onClickSearch(View v) {
        ResultActivity.startThisActivity(mActivity, bindTextInputParam.get());
    }
}
