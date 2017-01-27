package at.fibuszene.singlescreenapp.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by benedikt on 27.01.2017.
 */
public class CustomTitleTextView extends TextView {

    public CustomTitleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTitleTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/fr-title.ttf");
            setTypeface(tf);
        }
    }
}
