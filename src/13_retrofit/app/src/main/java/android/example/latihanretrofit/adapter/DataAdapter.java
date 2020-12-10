package android.example.latihanretrofit.adapter;

import android.content.Context;
import android.example.latihanretrofit.R;
import android.example.latihanretrofit.models.Repo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataAdapterViewHolder> {
    private List<Repo> repoList;
    private Context context;

    public void setData(List<Repo> repo) {
        this.repoList = repo;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataAdapter.DataAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new DataAdapter.DataAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.row_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataAdapterViewHolder holder, int position) {
        Repo repo = repoList.get(position);

        String html = repo.getHtmlUrl();
        holder.htmlUser.setText(html);

    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class DataAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView htmlUser;

        public DataAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            htmlUser = itemView.findViewById(R.id.htmlUser);
//            html = itemView.findViewById(R.id.htmlUrl);
        }
    }
}
