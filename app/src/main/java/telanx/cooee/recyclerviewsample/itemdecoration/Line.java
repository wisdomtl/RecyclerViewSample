package telanx.cooee.recyclerviewsample.itemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 表项分割器(直线)
 */
public class Line extends RecyclerView.ItemDecoration
{
    /**
     * 线高度
     */
    private int strokeWidth;
    /**
     * 线颜色
     */
    private int color;
    /**
     * 线左边距
     */
    private int marginLeft ;
    /**
     * 线右边距
     */
    private int marginRight ;
    private Paint paint ;

    public Line(int strokeWidth , int color){
        this.strokeWidth = strokeWidth ;
        this.color = color ;

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }

    public int getMarginLeft()
    {
        return marginLeft;
    }

    public Line setMarginLeft(int marginLeft)
    {
        this.marginLeft = marginLeft;
        return this ;
    }

    public int getMarginRight()
    {
        return marginRight;
    }

    public Line setMarginRight(int marginRight)
    {
        this.marginRight = marginRight;
        return this ;
    }

    @Override
    public void onDraw(Canvas c,
                       RecyclerView parent,
                       RecyclerView.State state)
    {
        super.onDraw(c, parent, state);
        final int startX = parent.getPaddingLeft()+marginLeft;
        final int endX = parent.getWidth() - parent.getPaddingRight()-marginRight;

        //遍历所有可见孩子绘制的分割线
        final int childCount = parent.getChildCount() ;
        for (int i = 0 ; i <childCount ; i++){
            View child = parent.getChildAt(i) ;
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int startY = child.getBottom() + layoutParams.bottomMargin ;
            c.drawLine(startX , startY , endX , startY , paint);
        }
    }

    @Override
    public void onDrawOver(Canvas c,
                           RecyclerView parent,
                           RecyclerView.State state)
    {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state)
    {
        super.getItemOffsets(outRect, view, parent, state);
    }
}
