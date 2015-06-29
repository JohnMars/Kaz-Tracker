package ninja.janibek.kaztracker.ui;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import ninja.janibek.kaztracker.R;
import ninja.janibek.kaztracker.ui.fragment.BaseFragment;

/**
 * Created by janibek on 6/28/15.
 * Version 1.0
 */
public class BaseActivity extends AppCompatActivity {
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";

    @IntDef({FragmentTransaction.TRANSIT_NONE,
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN,
            FragmentTransaction.TRANSIT_FRAGMENT_CLOSE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface Transit {
    }

    private View mStatusBar;
    private int mStatusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSystemUi();
    }

    protected void initToolBar(@IdRes int toolbarId) {
        Toolbar toolbar = (Toolbar) findViewById(toolbarId);
        if (toolbar == null) {
            throw new Resources.NotFoundException("Toolbar not found");
        }

        setSupportActionBar(toolbar);
    }

    public void replaceContent(@IdRes int viewHolderResId, BaseFragment fragment, boolean stackable) {
        replaceContent(viewHolderResId, fragment, stackable, FragmentTransaction.TRANSIT_NONE);
    }

    public void replaceContent(@IdRes int viewHolderResId, BaseFragment fragment,
                               boolean stackable, @Transit int transition) {
        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction()
                .replace(viewHolderResId, fragment)
                .setTransition(transition)
                .commit();
    }

    private void setupSystemUi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams winParams = getWindow().getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if ((winParams.flags & bits) != 0) {
                ViewGroup decorViewGroup = (ViewGroup) getWindow().getDecorView();

                final int statusBarHeight = getInternalDimensionSize(getResources(), STATUS_BAR_HEIGHT_RES_NAME);
                ViewGroup.LayoutParams vParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);

                mStatusBar = new View(this);
                mStatusBar.setLayoutParams(vParams);

                decorViewGroup.addView(mStatusBar, 0);
                mStatusBarHeight = statusBarHeight;

                TypedValue typedValue = new TypedValue();
                getTheme().resolveAttribute(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?
                        R.attr.colorPrimaryDark : R.attr.colorPrimary, typedValue, true);
                setStatusBarColor(typedValue.data);
            }
        }
    }

    public void setStatusBarColor(final int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (mStatusBar == null) {
                throw new IllegalStateException("StatusBar not initialized yet.");
            }

            int desiredColor = color;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                float maxFactor = Float.MAX_VALUE;
                int colorComps[] = {Color.red(color), Color.green(color), Color.blue(color)};
                for (int comp : colorComps) {
                    maxFactor = Math.min(maxFactor, 255.f / comp);
                }

                final float bestFactor = 1.664f; // lollipop statusbar opacity
                final float factor = Math.min(maxFactor, bestFactor);
                for (int i = 0; i < colorComps.length; i++) {
                    colorComps[i] = Math.round(colorComps[i] * factor);
                }

                desiredColor = Color.argb(Color.alpha(color),
                        colorComps[0], colorComps[1], colorComps[2]);
            }

            mStatusBar.setBackgroundColor(desiredColor);
        }
    }

    public int getStatusBarHeight() {
        if (mStatusBarHeight > 0) {
            return mStatusBarHeight;
        }

        if (mStatusBar != null) {
            return mStatusBarHeight = mStatusBar.getHeight();
        }

        return mStatusBarHeight = getInternalDimensionSize(getResources(), STATUS_BAR_HEIGHT_RES_NAME);
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            return res.getDimensionPixelSize(resourceId);
        }

        return 0;
    }
}
