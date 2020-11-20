package android.example.project;

import androidx.databinding.ObservableField;

public class ResultActivityVM {
    public ObservableField<String> bindTextParam = new ObservableField<>();

    public ResultActivityVM(String param) {
        bindTextParam.set(param);
    }
}
