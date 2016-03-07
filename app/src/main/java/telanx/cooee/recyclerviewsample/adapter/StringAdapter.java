package telanx.cooee.recyclerviewsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import telanx.cooee.recyclerviewsample.R;

/**
 * ×Ö·û´®ÊÊÅäÆ÷
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.StringViewHolder>
{
    private Context context ;
    private ArrayList<String> datas ;

    public StringAdapter(Context context ,ArrayList<String> datas){
        this.context = context ;
        this.datas = datas ;
    }

    @Override
    public StringViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                               int i)
    {
        //±ÜÃâ±íÏî¶àÒ»²ãÇ¶Ì×
        View itemView = LayoutInflater.from(context).inflate(R.layout.string_item,null) ;
        return new StringViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StringViewHolder stringViewHolder,
                                 int i)
    {
        stringViewHolder.textView.setText(datas.get(i));
    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    public class StringViewHolder extends RecyclerView.ViewHolder{

        public TextView textView ;

        public StringViewHolder(View itemView)
        {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_StringItem);
        }
    }
}
