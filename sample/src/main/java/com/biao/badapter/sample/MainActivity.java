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
import com.biao.badapter.sample.multi.DataBindingMultiItemFragment;
import com.biao.badapter.sample.multi.SimpleMultiItemFragment;
import com.biao.badapter.sample.single.DataBindingSingleItemFragment;
import com.biao.badapter.sample.single.SimpleSingleItemFragment;

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
              case R.id.simple_single_item:
                switchNavigationItem(new SimpleSingleItemFragment(),
                    R.string.main_simple_single_item);
                break;
              case R.id.simple_multi_item:
                switchNavigationItem(new SimpleMultiItemFragment(),
                    R.string.main_simple_multi_item);
                break;
              case R.id.databinding_single_item:
                switchNavigationItem(new DataBindingSingleItemFragment(),
                    R.string.main_databinding_single_item);
                break;
              case R.id.databinding_multi_item:
                switchNavigationItem(new DataBindingMultiItemFragment(),
                    R.string.main_databinding_multi_item);
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
