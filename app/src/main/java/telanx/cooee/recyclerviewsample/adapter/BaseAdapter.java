package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView适配器基类(带表现点击监听器)
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_EMPTY_VIEW = -1;
    public static final int TYPE_CONTENT = -2;

    protected Context context;
    /**
     * 数据集
     */
    protected List<T> datas;
    protected int emptyViewLayout;
    private int currentMode ;
    /**
     * 表项点击监听器
     */
    private OnItemClickListener<T> onItemClickListener;

    public BaseAdapter(Context context) {
        this.context = context;
        init();
    }

    public BaseAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        init();
    }

    private void init() {
        this.registerAdapterDataObserver(new DataObserver());
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param data 单个数据
     */
    public void addData(T data) {
        datas.add(data);
        notifyItemInserted(datas.size() - 1);
    }

    public void setEmptyViewLayout(int emptyViewLayout) {
        this.emptyViewLayout = emptyViewLayout;
    }

    /**
     * 添加数据到指定位置
     *
     * @param data     数据
     * @param position 指定位置
     */
    public void addDataAt(T data,
                          int position) {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 删除单个数据
     *
     * @param position 数据索引
     */
    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        if (payloads == null || payloads.size() == 0) {
            super.onBindViewHolder(holder, position, payloads);
            return;
        }
        refreshItem(holder, position, payloads);
    }

    @Override
    public final BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder;
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_EMPTY_VIEW:
                itemView = layoutInflater.inflate(emptyViewLayout, parent, false);
                viewHolder = new BaseViewHolder(itemView);
                break;
            default:
                viewHolder = createHolder(parent, viewType, layoutInflater);
                break;
        }
        return viewHolder;
    }

    @Override
    public final int getItemCount() {
        if (currentMode == TYPE_EMPTY_VIEW) {
            return 1;
        }
        return getCount();
    }

    @Override
    public final int getItemViewType(int position) {
        if (datas == null || datas.size() == 0) {
            return TYPE_EMPTY_VIEW;
        } else {
            return getViewType(position);
        }
    }

    protected abstract int getCount();

    protected abstract int getViewType(int position);

    protected abstract void refreshItem(BaseViewHolder holder, int position, List<Object> payloads);

    protected abstract BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater);

    /**
     * 表项点击监听器
     *
     * @param <T> 表项数据类型
     */
    public interface OnItemClickListener<T> {
        void onItemClick(T data);

        void onItemLongClick(int position,
                             BaseAdapter adapter);
    }


//    /**
//     * 带有表项点击监听的ViewHolder
//     */
//    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
//    {
//
//        public BaseViewHolder(View itemView)
//        {
//            super(itemView);
//            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v)
//        {
//            if (onItemClickListener != null){
//                int position = getAdapterPosition() ;
//                onItemClickListener.onItemClick(datas.get(position));
//            }
//        }
//
//        @Override
//        public boolean onLongClick(View v)
//        {
//            if (onItemClickListener != null)
//            {
//                int position = getAdapterPosition();
//                onItemClickListener.onItemLongClick(position, BaseAdapter.this);
//                return true;
//            }
//            return false;
//        }
//    }

    //        @Override
//        public void onClick(View v)
//        {
//            if (onItemClickListener != null){
//                int position = getAdapterPosition() ;
//                onItemClickListener.onItemClick(datas.get(position));
//            }
//        }
//
//        @Override
//        public boolean onLongClick(View v)
//        {
//            if (onItemClickListener != null)
//            {
//                int position = getAdapterPosition();
//                onItemClickListener.onItemLongClick(position, BaseAdapter.this);
//                return true;
//            }
//            return false;
//        }
//    }
    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (datas != null && datas.size() != 0) {
                currentMode = TYPE_CONTENT;
            } else {
                currentMode = TYPE_EMPTY_VIEW;
            }
        }
    }
}
