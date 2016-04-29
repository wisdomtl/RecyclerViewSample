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
public class AlphabetAdapter extends BaseAdapter<String> {
    private static final int TYPE_ALPHABET = 1;

    public AlphabetAdapter(Context context,
                           List<String> datas) {
        super(context, datas);
    }


    public AlphabetAdapter(Context context) {
        super(context);
    }


    @Override
    protected void refreshItem(BaseViewHolder holder, int position, List<Object> payloads) {
        ((StringViewHolder)holder).tvAlphabet.setText(datas.get(position));
    }

    @Override
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        BaseViewHolder viewHolder;
        View itemView;
        switch (viewType) {
            case TYPE_ALPHABET:
                //避免表项多一层嵌套
                itemView = inflater.inflate(R.layout.string_item, parent,false);
                viewHolder = new StringViewHolder(itemView);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_EMPTY_VIEW:
                break;
            case TYPE_ALPHABET:
                String alphabet = datas.get(position);
                ((StringViewHolder) holder).tvAlphabet.setText(alphabet);
                break;
            default:
                break;
        }
    }

    @Override
    protected int getCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    protected int getViewType(int position) {
        return TYPE_ALPHABET;
    }

    public class StringViewHolder extends BaseViewHolder {
        public TextView tvAlphabet;

        public StringViewHolder(View itemView) {
            super(itemView);
            tvAlphabet = (TextView) itemView.findViewById(R.id.tv_StringItem);
        }
    }
}

