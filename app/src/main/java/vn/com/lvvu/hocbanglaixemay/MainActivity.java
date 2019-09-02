package vn.com.lvvu.hocbanglaixemay;

import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import vn.com.lvvu.hocbanglaixemay.alltest.AllTestFragment;
import vn.com.lvvu.hocbanglaixemay.boardnotice.AllBoardFragment;
import vn.com.lvvu.hocbanglaixemay.practice.PracticeFragment;
import vn.com.lvvu.hocbanglaixemay.safedrive.SafeDriveFragment;
import vn.com.lvvu.hocbanglaixemay.theory.TheoryTipTrickFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        startAllTestExamFragment();

    }

    /**
     * Mở giao diện danh sách đề thi thử
     */
    private void startAllTestExamFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_contain, AllTestFragment.getInstance());
            fragmentTransaction.commit();

            mToolbar.setTitle(getString(R.string.all_test_exam));
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Mở giao diện danh sách đề thi thử
     */
    private void startAllBoardFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_contain, AllBoardFragment.newInstance());
            fragmentTransaction.commit();

            mToolbar.setTitle(getString(R.string.notice_board));
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all_test) {
            // Chuyển sang màn hình danh sách đề thi thử
            startAllTestExamFragment();
        } else if (id == R.id.nav_all_board) {
            //chuyển sang màn hình biển báo giao thông
            startAllBoardFragment();

        } else if (id == R.id.nav_facebook) {

        } else if (id == R.id.nav_gmail) {

        } else if (id == R.id.nav_safe_drive) {
            startSafeDriveFragment();
        } else if (id == R.id.nav_practice) {
            startPracticeFragment();
        } else if (id == R.id.nav_tip_trick) {
            startTheoryTipTrickFragment();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startTheoryTipTrickFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_contain, TheoryTipTrickFragment.getInstance());
            fragmentTransaction.commit();

            mToolbar.setTitle(getString(R.string.tip_trick));
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    private void startPracticeFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_contain, PracticeFragment.getInstance());
            fragmentTransaction.commit();

            mToolbar.setTitle(getString(R.string.tip_practice));
        } catch (Exception e) {
            Common.handleException(e);
        }
    }

    /**
     * Chuyển sang màn hình dạy kĩ năng lái xe an toàn
     */
    private void startSafeDriveFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.layout_contain, SafeDriveFragment.getInstance());
            fragmentTransaction.commit();

            mToolbar.setTitle(getString(R.string.save_drive));
        } catch (Exception e) {
            Common.handleException(e);
        }
    }
}
