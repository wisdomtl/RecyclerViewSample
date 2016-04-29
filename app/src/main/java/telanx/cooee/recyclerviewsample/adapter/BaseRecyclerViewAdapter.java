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
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int TYPE_EMPTY_VIEW = -1;
    public static final int TYPE_CONTENT = -2;

    protected Context context;
    /**
     * data set
     */
    protected List<T> datas;
    /**
     * layout resource for empty view
     */
    private int emptyViewLayout;
    /**
     * current view type (empty view or content)
     */
    private int currentType;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
        init();
    }

    public BaseRecyclerViewAdapter(Context context, List<T> datas) {
        this.context = context;
        this.datas = datas;
        init();
    }

    private void init() {
        this.registerAdapterDataObserver(new DataObserver());
    }

    public void setData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    /**
     * add single data
     *
     * @param data single data
     */
    public void addData(T data) {
        datas.add(data);
        notifyItemInserted(datas.size() - 1);
    }

    public void setEmptyViewLayout(int emptyViewLayout) {
        this.emptyViewLayout = emptyViewLayout;
    }

    /**
     * add data to specified position
     *
     * @param data     data to be added
     * @param position specified position
     */
    public void addDataAt(T data,
                          int position) {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * remove data at specified position
     *
     * @param position specified position
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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_EMPTY_VIEW:
                break;
            default:
                bindHolder(holder , position);
                break;
        }
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
        if (currentType == TYPE_EMPTY_VIEW) {
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

    protected abstract void bindHolder(BaseViewHolder holder, int position) ;

    protected abstract int getCount();

    protected abstract int getViewType(int position);

    protected abstract void refreshItem(BaseViewHolder holder, int position, List<Object> payloads);

    protected abstract BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater);


    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (datas != null && datas.size() != 0) {
                currentType = TYPE_CONTENT;
            } else {
                currentType = TYPE_EMPTY_VIEW;
            }
        }
    }
}
