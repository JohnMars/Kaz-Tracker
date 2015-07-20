package ninja.janibek.kaztracker.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ninja.janibek.kaztracker.R;
import ninja.janibek.kaztracker.ui.fragment.TrackingFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.activity_main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.activity_main_navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()) {
            case R.id.nav_tracking:
                replaceContent(R.id.activity_main_frame_layout_content, new TrackingFragment(), false);
                return true;
            default:
                return false;
        }
    }
}
