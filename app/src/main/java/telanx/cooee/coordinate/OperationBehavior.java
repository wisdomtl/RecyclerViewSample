package telanx.cooee.coordinate;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class OperationBehavior extends CoordinatorLayout.Behavior {

    public OperationBehavior() {
    }

    public OperationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.v("ttaylor", "OperationBehavior.onDependentViewChanged()" + "  dependency");
        return dependency instanceof RecyclerView;
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        int top = dependency.getTop();
        int scrollY = dependency.getScrollY();
        Log.v("ttaylor", "OperationBehavior.onDependentViewRemoved()" + "  top=" + top + ",scrollY=" + scrollY);
        super.onDependentViewRemoved(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }
}
