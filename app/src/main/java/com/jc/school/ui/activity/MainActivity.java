package com.jc.school.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jc.school.R;
import com.jc.school.bean.PersonalInfo;
import com.jc.school.interf.NavigationDrawerCallbacks;
import com.jc.school.ui.fragment.AdministrationFragment;
import com.jc.school.ui.fragment.ContractFragment;
import com.jc.school.ui.fragment.HomePageFragment;
import com.jc.school.ui.fragment.LocalNewsFragment;
import com.jc.school.ui.fragment.LostFoundFragment;
import com.jc.school.ui.fragment.NavigationDrawerFragment;
import com.jc.school.ui.fragment.OtherDepartmentFragment;
import com.jc.school.ui.fragment.QueryTelephoneNumberFragment;
import com.jc.school.ui.fragment.TeachDepartmentFragment;
import com.jc.school.ui.fragment.WeatherFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements NavigationDrawerCallbacks {

    private static final String TAG = "MainActivity";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("苏科校园通");

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(
                R.id.fragment_drawer
        );

        // Set up the drawer.
        mNavigationDrawerFragment.setup(
                R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar
        );
        if (fragment==null){
            fragment = new HomePageFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();
        Fragment fragment;

        switch (position) {
            case 0:
                fragment = new HomePageFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 1:
                fragment = new AdministrationFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                //                queryAll();

                break;
            case 2:
                fragment = new TeachDepartmentFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 3:
                fragment = new OtherDepartmentFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 4:
                fragment = new ContractFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 5:
                fragment = new QueryTelephoneNumberFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 6:
                fragment = new WeatherFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                break;
            case 7:
                fragment = new LostFoundFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            case 8:
                fragment = new LocalNewsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();
                break;
            default:
                break;
        }

    }

    private void queryAll() {
        BmobQuery<PersonalInfo> personalInfoBmobQuery = new BmobQuery<PersonalInfo>();
        personalInfoBmobQuery.addWhereEqualTo("name", "孙二娘");
        personalInfoBmobQuery.findObjects(
                MainActivity.this, new FindListener<PersonalInfo>() {
                    @Override
                    public void onSuccess(List<PersonalInfo> list) {
                        for (PersonalInfo personInfo : list) {
                            Logger.i(TAG, personInfo.getName().toString());
                        }
                    }

                    @Override
                    public void onError(String s) {

                    }
                }
        );
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.about_me:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                intent = new Intent(getApplicationContext(), FeedbackActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, "")
                .commit();
    }




}
