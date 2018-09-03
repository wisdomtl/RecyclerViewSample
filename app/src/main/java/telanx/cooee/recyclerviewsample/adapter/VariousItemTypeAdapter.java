package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import telanx.cooee.recyclerviewsample.R;
import telanx.cooee.recyclerviewsample.entity.ItemData;

/**
 * Created by Taylor <Liang.tang@cootek.cn> on 2016/4/30.
 */
public class VariousItemTypeAdapter extends BaseRecyclerViewAdapter<ItemData> {
    public VariousItemTypeAdapter(Context context) {
        super(context);
    }

    public VariousItemTypeAdapter(Context context, List<ItemData> datas) {
        super(context, datas);
    }

    @Override
    protected void bindHolder(BaseViewHolder holder, int position) {
        int viewType = getViewType(position);
        ItemData data = datas.get(position);
        switch (viewType) {
            case ItemData.LAYOUT_1:
                ItemViewHolder1 viewHolder1 = (ItemViewHolder1) holder;
                viewHolder1.tvTitle.setText(data.getTitle());
                break;
            case ItemData.LAYOUT_2:
                ItemViewHolder2 viewHolder2 = (ItemViewHolder2) holder;
                viewHolder2.tvTitle.setText(data.getTitle());
                viewHolder2.tvTime.setText(data.getTime());
                break;
            case ItemData.LAYOUT_3:
                ItemViewHolder3 viewHolder3 = (ItemViewHolder3) holder;
                break;
            default:
                break;

        }
    }

    @Override
    protected int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    protected int getViewType(int position) {
        return datas.get(position).getLayoutType();
    }

    @Override
    protected void refreshItem(BaseViewHolder holder, int position, List<Object> payloads) {

    }

    @Override
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        BaseViewHolder viewHolder;
        View itemView;
        switch (viewType) {
            case ItemData.LAYOUT_1:
                itemView = inflater.inflate(R.layout.item_layout1, parent, false);
                viewHolder = new ItemViewHolder1(itemView);
                break;
            case ItemData.LAYOUT_2:
                itemView = inflater.inflate(R.layout.item_layout2, parent, false);
                viewHolder = new ItemViewHolder2(itemView);
                break;
            case ItemData.LAYOUT_3:
                itemView = inflater.inflate(R.layout.item_full_screen_header, parent, false);
                viewHolder = new ItemViewHolder3(itemView);
                break;
            default:
                viewHolder = null;
                break;

        }
        return viewHolder;
    }

    private class ItemViewHolder1 extends BaseViewHolder {

        public TextView tvTitle;

        public ItemViewHolder1(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_layout);
        }
    }

    private class ItemViewHolder2 extends ItemViewHolder1 {

        private TextView tvTime;

        public ItemViewHolder2(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_layout);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
        }
    }

    private class ItemViewHolder3 extends BaseViewHolder {

        private ImageView iv;

        public ItemViewHolder3(View itemView) {
            super(itemView);
            iv = ((ImageView) itemView.findViewById(R.id.iv_full_screen_header));
        }
    }
}
