package com.hsun.mvvmbrowser.activities.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import com.hsun.mvvmbrowser.Config;
import com.hsun.mvvmbrowser.R;
import com.hsun.mvvmbrowser.activities.adapter.HintAdapter;
import com.hsun.mvvmbrowser.databinding.ActivityMainBinding;
import com.hsun.mvvmbrowser.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding activityMainBinding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Config.init(this);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new MainActivityViewModel(this, activityMainBinding);
        activityMainBinding.setViewModel(mainActivityViewModel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * Spinner
         */
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.web_more_settings_label, R.layout.spinner_layout);
        final List<String> objects = Arrays.asList(getResources().getStringArray(R.array.web_more_settings_label));
        HintAdapter adapter = new HintAdapter(this, objects, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        //activityMainBinding.container.spinner.setFocusedByDefault(false);
        activityMainBinding.container.spinner.setAdapter(adapter);
        activityMainBinding.container.spinner.setSelection(objects.size() - 1);
        activityMainBinding.container.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> arrayList = Arrays.asList(getResources().getStringArray(R.array.web_more_settings_value));
                switch (arrayList.get(i)) {
                    case "set_home_page":
                        String nowUrl = activityMainBinding.container.edSearchUrl.getText().toString();
                        Config.sharedPreferences.edit()
                                .putString(Config.SP_HOME_URL, nowUrl).apply();
                        Config.HOME_URL = nowUrl;
                        SnackbarUtil.text(MainActivity.this, getString(R.string.alert_setting_finish));
                        activityMainBinding.container.spinner.setSelection(objects.size() - 1);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //刷新Webview設定
                case Config.REFRESH_WEB_SETTING:
                    mainActivityViewModel.setWebSetting();
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mainActivityViewModel.webCanGoBack.get()) {
                mainActivityViewModel.webBack(null);
            } else {
                moveTaskToBack(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
