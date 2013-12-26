package jp.mixi.compatibility.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * This is a {@link android.widget.ScrollView} compatibility that enables containing {@link android.support.v4.view.ViewPager}.
 * {@link android.support.v4.view.ViewPager} swipe action will not be properly performed on some devices while it is in the scrollable component, so use this to avoid such a problem.
 */
@SuppressWarnings("unused") // public APIs
public class ViewPagerContainableScrollView extends ScrollView {
    private GestureDetector mGestureDetector;

    public ViewPagerContainableScrollView(Context context) {
        this(context, null);
    }

    public ViewPagerContainableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerContainableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }

    private static class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if(Math.abs(distanceY) > Math.abs(distanceX)) {
                return true;
            }
            return false;
        }
    }
}