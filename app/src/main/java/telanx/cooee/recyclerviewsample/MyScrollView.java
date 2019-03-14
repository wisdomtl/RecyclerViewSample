package telanx.cooee.recyclerviewsample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //touch event case1: make ScrollView which is in an item of RecyclerView scrollable
        //the way is requesting parent not to intercept touch event, let ScrollView deal itself
        getParent().requestDisallowInterceptTouchEvent(true);
        super.dispatchTouchEvent(ev) ;
        return true;
    }
}
