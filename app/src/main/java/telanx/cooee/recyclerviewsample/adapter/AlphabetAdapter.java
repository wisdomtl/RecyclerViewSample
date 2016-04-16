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

    public AlphabetAdapter(Context context,
                           List<String> datas)
    {
        super(context, datas);
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType)
    {
        //避免表项多一层嵌套
        View itemView = LayoutInflater.from(context)
                                      .inflate(R.layout.string_item, null);
        return new StringViewHolder(itemView);
    }


    @Override
    protected void bindData(StringViewHolder holder,
                            int position,
                            List<Object> payLoads)
    {
        if (payLoads == null || payLoads.size() == 0)
        {
            holder.tvAlphabet.setText(getDatas().get(position));
            return;
        }

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

