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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener)
    {
        this.onItemClickListener = onItemClickListener;
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
    }

    /**
     * 带有表项点击监听的ViewHolder
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public BaseViewHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            if (onItemClickListener != null){
                int position = getAdapterPosition() ;
                onItemClickListener.onItemClick(datas.get(position));
            }
        }
    }
}
