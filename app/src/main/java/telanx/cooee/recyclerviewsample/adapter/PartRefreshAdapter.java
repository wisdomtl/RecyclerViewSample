package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import telanx.cooee.recyclerviewsample.R;
import telanx.cooee.recyclerviewsample.entity.ItemData;

/**
 * Created by Taylor on 2016/4/16.
 */
public class PartRefreshAdapter extends BaseRecyclerViewAdapter<ItemData> {

    public static final int REFRESH_INTERVAL = 1;

    public PartRefreshAdapter(Context context,
                              List<ItemData> datas) {
        super(context, datas);
    }

    @Override
    protected void bindHolder(BaseViewHolder holder, int position) {
        ((NewsViewHolder) holder).tvTitle.setText(datas.get(position)
                .getTitle());
        ((NewsViewHolder) holder).tvTime.setText(datas.get(position)
                .getTime());
    }

    @Override
    protected int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    protected int getViewType(int position) {
        return 0;
    }

    @Override
    protected void refreshItem(BaseViewHolder holder, int position, List<Object> payloads) {
        Integer payload = (Integer) payloads.get(0);
        int color;
        if (payload % 2 == 0) {
            color = Color.GREEN;
        } else {
            color = Color.GRAY;
        }
        ((NewsViewHolder) holder).tvTime.setTextColor(color);
    }

    @Override
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        //avoid nesting layout
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.news_item, null);
        return new NewsViewHolder(itemView);
    }

    public class NewsViewHolder extends BaseViewHolder {

        public TextView tvTitle;
        public TextView tvTime;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.tvNewsTime);
            tvTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
        }
    }
}
