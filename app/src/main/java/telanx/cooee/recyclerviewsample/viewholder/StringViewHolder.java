package telanx.cooee.recyclerviewsample.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import telanx.cooee.recyclerviewsample.R;

/**
 * 只显示单个字符串的表项Holder
 */
public class StringViewHolder extends RecyclerView.ViewHolder
{
    public TextView textView;

    public StringViewHolder(View itemView)
    {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.tv_StringItem);
    }
}
