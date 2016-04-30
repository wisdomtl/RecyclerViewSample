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
public class FooterAdapter extends BaseRecyclerViewAdapter<String> {

    private static final int TYPE_ALPHABET = 1;

    public FooterAdapter(Context context,
                         List<String> datas) {
        super(context, datas);
    }

    public FooterAdapter(Context context) {
        super(context);
    }

    @Override
    protected void refreshItem(BaseViewHolder holder, int position, List<Object> payloads) {
    }

    @Override
    protected BaseViewHolder createHolder(ViewGroup parent, int viewType, LayoutInflater inflater) {
        BaseViewHolder viewHolder;
        View itemView;
        switch (viewType) {
            case TYPE_ALPHABET:
                //避免表项多一层嵌套
                itemView = inflater.inflate(R.layout.string_item, parent, false);
                viewHolder = new StringViewHolder(itemView);
                break;
            default:
                viewHolder = null;
                break;
        }
        return viewHolder;
    }

    @Override
    protected void bindHolder(BaseViewHolder holder, int position) {
        String alphabet = datas.get(position);
        ((StringViewHolder) holder).tvAlphabet.setText(alphabet);
    }

    @Override
    protected int getCount() {
        return datas != null ? datas.size() + FOOTER_COUNT : 0;
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

