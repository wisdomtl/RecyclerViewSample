package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import telanx.cooee.recyclerviewsample.R;
import telanx.cooee.recyclerviewsample.viewholder.StringViewHolder;

/**
 * RecyclerView适配器最简单用法
 */
public class SimpleAdapter extends RecyclerView.Adapter<StringViewHolder>
{
    private Context context ;
    private ArrayList<String> datas ;

    public SimpleAdapter(Context context,
                         ArrayList<String> datas){
        this.context = context ;
        this.datas = datas ;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int i)
    {
        //避免表项多一层嵌套
        View itemView = LayoutInflater.from(context)
                                      .inflate(R.layout.string_item, viewGroup, false);
        return new StringViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StringViewHolder stringViewHolder,
                                 int i)
    {
        stringViewHolder.textView.setText(datas.get(i));
    }

    @Override
    public int getItemCount()
    {
        return datas != null ?datas.size() : 0;
    }
}
