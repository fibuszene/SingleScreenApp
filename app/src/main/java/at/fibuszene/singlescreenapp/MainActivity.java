package at.fibuszene.singlescreenapp;

import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Parallax Scroll View from https://github.com/antoniolg/MaterializeYourApp
 */
public class MainActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TableLayout businesshours;
    private TextView clickToExpand;
    private Button businessHoursTitle;

    private boolean expanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        supportPostponeEnterTransition();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.company_name));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        businesshours = (TableLayout) findViewById(R.id.businesshours);
        collapse(businesshours);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/fr-fal1.ttf");
        clickToExpand = (TextView) findViewById(R.id.clickToExpand);
        clickToExpand.setTypeface(tf);

        tf = Typeface.createFromAsset(getAssets(), "fonts/fr-title.ttf");
        businessHoursTitle = (Button) findViewById(R.id.businessHoursTitle);
        businessHoursTitle.setTypeface(tf);
    }

    public void toggleHours(View view) {
        if (expanded) {
            collapse(businesshours);
        } else {
            expand(businesshours);
        }
        expanded = !expanded;
    }

    //See http://stackoverflow.com/questions/4946295/android-expand-collapse-animation
    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }
}
