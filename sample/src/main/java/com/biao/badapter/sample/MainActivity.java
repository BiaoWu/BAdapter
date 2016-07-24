package com.biao.badapter.sample;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import com.biao.badapter.sample.multi.MultiItemFragment;
import com.biao.badapter.sample.simple.SimpleItemFragment;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";

  private DrawerLayout drawerLayout;
  private Toolbar toolbar;

  private int currentItemId = -1;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    ActionBarDrawerToggle actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_string,
            R.string.close_string);
    actionBarDrawerToggle.syncState();

    drawerLayout.addDrawerListener(actionBarDrawerToggle);

    NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(MenuItem item) {
            int itemId = item.getItemId();
            if (currentItemId == itemId) {
              drawerLayout.closeDrawers();
              return false;
            }

            switch (itemId) {
              case R.id.nav_home:
                switchNavigationItem(new MainFragment(), R.string.app_name);
                break;
              case R.id.simple_item:
                switchNavigationItem(new SimpleItemFragment(), R.string.main_simple_item);
                break;
              case R.id.multi_item:
                switchNavigationItem(new MultiItemFragment(), R.string.main_multi_item);
                break;
              default:
                Log.e(TAG, "Lose this one -> " + itemId);
                return false;
            }
            currentItemId = itemId;
            return true;
          }
        });

    navigationView.setCheckedItem(R.id.nav_home);
    replaceFragment(new MainFragment());
  }

  private void switchNavigationItem(Fragment fragment, @StringRes int idRes) {
    replaceFragment(fragment);
    toolbar.setTitle(idRes);
    drawerLayout.closeDrawers();
  }

  private void replaceFragment(Fragment fragment) {
    getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
  }
}
