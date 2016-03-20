package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import telanx.cooee.recyclerviewsample.R;

/**
 * BaseAdapter的一种实现
 */
public class AlphabetAdapter extends BaseAdapter<String, AlphabetAdapter.StringViewHolder>
{
    private Context context;

    public AlphabetAdapter(Context context,
                           List<String> datas)
    {
        super(datas);
        this.context = context;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType)
    {
        //避免表项多一层嵌套
        View itemView = LayoutInflater.from(context)
                                      .inflate(R.layout.string_item , parent , false);
        return new StringViewHolder(itemView);
    }

    @Override
    public void bindData(StringViewHolder holder,
                         String data)
    {
        holder.tvAlphabet.setText(data);
    }

    public class StringViewHolder extends BaseAdapter.BaseViewHolder
    {
        public TextView tvAlphabet;

        public StringViewHolder(View itemView)
        {
            super(itemView);
            tvAlphabet = (TextView) itemView.findViewById(R.id.tv_StringItem);
        }
    }
}

