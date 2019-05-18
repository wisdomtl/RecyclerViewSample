package telanx.cooee.recyclerviewsample.grid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import telanx.cooee.recyclerviewsample.R;

public class GridAdapter extends RecyclerView.Adapter<GridViewHolder> {
    private List<String> datas = new ArrayList<>();

    public GridAdapter(List<String > datas){
        this.datas = datas;
    }
    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.datas.size();
    }
}
