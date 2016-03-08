package telanx.cooee.recyclerviewsample.itemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 表项分割器(直线)
 */
public class Line extends RecyclerView.ItemDecoration
{
    private final int[] ATTRS = {android.R.attr.listDivider};
    /**
     * 线左边距
     */
    private int marginLeft;
    /**
     * 线右边距
     */
    private int marginRight;
    /**
     * 线图片
     */
    private Drawable divider;
    private Paint paint;

    public Line(int strokeWidth,
                int color)
    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }

    public Line(Context context)
    {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        divider = a.getDrawable(0);
        a.recycle();
    }

    public int getMarginLeft()
    {
        return marginLeft;
    }

    public Line setMarginLeft(int marginLeft)
    {
        this.marginLeft = marginLeft;
        return this;
    }

    public int getMarginRight()
    {
        return marginRight;
    }

    public Line setMarginRight(int marginRight)
    {
        this.marginRight = marginRight;
        return this;
    }

    @Override
    public void onDraw(Canvas c,
                       RecyclerView parent,
                       RecyclerView.State state)
    {
        super.onDraw(c, parent, state);

        final int left = parent.getPaddingLeft() + marginLeft;
        final int right = parent.getWidth() - parent.getPaddingRight() - marginRight;

        //遍历所有可见孩子绘制其分割线
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            //用Paint绘制
            if (divider == null)
            {
                c.drawLine(left, top, right, top, paint);
            }
            //用drawable绘制
            else
            {
                final int bottom = top + divider.getIntrinsicHeight();
                divider.setBounds(left, top, right, bottom);
                divider.draw(c);
            }
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
