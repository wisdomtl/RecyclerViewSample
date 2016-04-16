package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import telanx.cooee.recyclerviewsample.R;
import telanx.cooee.recyclerviewsample.entity.News;

/**
 * Created by qq on 2016/4/16.
 */
public class PartRefreshAdapter extends BaseAdapter<News, PartRefreshAdapter.NewsViewHolder>
{

    public static final int REFRESH_INTERVAL = 1;

    public PartRefreshAdapter(Context context,
                              List<News> datas)
    {
        super(context, datas);
    }


    @Override
    protected void bindData(NewsViewHolder holder,
                            int position,
                            List<Object> payLoads)
    {
        if (payLoads == null || payLoads.size() == 0)
        {
            holder.tvTitle.setText(getDatas().get(position)
                                             .getTitle());
            holder.tvTime.setText(getDatas().get(position)
                                            .getTime());
            return;
        }
        Integer payload = (Integer) payLoads.get(0);
        int color;
        if (payload % 2 == 0)
        {
            color = Color.GREEN;
        }
        else
        {
            color = Color.GRAY;
        }
        holder.tvTime.setTextColor(color);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType)
    {
        //避免表项多一层嵌套
        View itemView = LayoutInflater.from(context)
                                      .inflate(R.layout.news_item, null);
        return new NewsViewHolder(itemView);
    }

    public class NewsViewHolder extends BaseAdapter.BaseViewHolder
    {

        public TextView tvTitle;
        public TextView tvTime;

        public NewsViewHolder(View itemView)
        {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvNewsTime);
            tvTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
        }
    }

}
