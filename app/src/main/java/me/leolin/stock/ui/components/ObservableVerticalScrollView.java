package me.leolin.stock.ui.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author leolin
 */
public class ObservableVerticalScrollView extends ScrollView {

    private ScrollView subscriberScrollerView;

    public ObservableVerticalScrollView(Context context) {
        super(context);
    }

    public ObservableVerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
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
