package telanx.cooee.recyclerviewsample.grid;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CofferItemDecoration extends RecyclerView.ItemDecoration {

    private int horizontalInterval ;
    private int verticalInterval;
    private int spanCount;

    public CofferItemDecoration(int horizontalInterval,int verticalInterval,int spanCount){
        this.horizontalInterval =horizontalInterval ;
        this.verticalInterval=  verticalInterval;
        this.spanCount = spanCount ;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(horizontalInterval / 2, 0, horizontalInterval / 2, verticalInterval);
    }
}
