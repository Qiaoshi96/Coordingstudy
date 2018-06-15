package com.example.administrator.coordingstudy;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/6/6 0006.
 */

public class HeaderMovableCoordinatorLayout extends CoordinatorLayout {

    private boolean mHeaderMovable;

    public HeaderMovableCoordinatorLayout(Context context) {
        super(context);
    }

    public HeaderMovableCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderMovableCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeaderMovable(boolean headerMovable) {
        mHeaderMovable = headerMovable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*
         * if (mHeaderMovable)
               return false;
         return super.onInterceptTouchEvent(ev);
         */
        //如果允许顶部纵向move事件。则不对事件进行拦截。继续向下传递，否则调用原有的拦截机制进行处理。
        return !mHeaderMovable && super.onInterceptTouchEvent(ev);
    }
}

