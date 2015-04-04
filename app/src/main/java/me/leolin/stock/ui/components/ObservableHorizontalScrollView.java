package me.leolin.stock.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * @author leolin
 */
public class ObservableHorizontalScrollView extends HorizontalScrollView {

    private HorizontalScrollView subscriberScrollerView;

    public ObservableHorizontalScrollView(Context context) {
        super(context);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (subscriberScrollerView != null) {
            subscriberScrollerView.scrollTo(l, t);
        }
    }
}
