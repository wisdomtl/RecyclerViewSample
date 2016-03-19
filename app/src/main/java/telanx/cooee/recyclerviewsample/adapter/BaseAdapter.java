package telanx.cooee.recyclerviewsample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * RecyclerView适配器基类(带表现点击监听器)
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>
{
    /**
     * 数据集
     */
    private List<T> datas;
    /**
     * 表项点击监听器
     */
    private OnItemClickListener<T> onItemClickListener ;

    public BaseAdapter(List<T> datas){
        this.datas = datas ;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 添加数据
     *
     * @param data 单个数据
     */
    public void addData(T data)
    {
        datas.add(data);
        notifyItemInserted(datas.size() - 1);
    }

    /**
     * 添加数据到指定位置
     *
     * @param data     数据
     * @param position 指定位置
     */
    public void addDataAt(T data,
                          int position)
    {
        datas.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 删除单个数据
     *
     * @param position 数据索引
     */
    public void removeData(int position)
    {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onBindViewHolder(VH holder,
                                 int position)
    {
        bindData(holder, datas.get(position));
    }

    @Override
    public int getItemCount()
    {
        return datas != null ? datas.size() : 0;
    }

    public abstract void bindData(VH holder,
                                  T data);

    /**
     * 表项点击监听器
     *
     * @param <T> 表项数据类型
     */
    public interface OnItemClickListener<T>
    {
        void onItemClick(T data);

        void onItemLongClick(int position,
                             BaseAdapter adapter);
    }

    /**
     * 带有表项点击监听的ViewHolder
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {

        public BaseViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (onItemClickListener != null){
                int position = getAdapterPosition() ;
                onItemClickListener.onItemClick(datas.get(position));
            }
        }

        @Override
        public boolean onLongClick(View v)
        {
            if (onItemClickListener != null)
            {
                int position = getAdapterPosition();
                onItemClickListener.onItemLongClick(position, BaseAdapter.this);
                return true;
            }
            return false;
        }
    }
}
