package org.richcode.campusdiary;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.richcode.campusdiary.Adapter.PagerAdapter;
import org.richcode.campusdiary.DataClass.DiaryData;
import org.richcode.campusdiary.DataClass.HomeWorkData;
import org.richcode.campusdiary.DataClass.MemoData;
import org.richcode.campusdiary.Dialog.DialogExit;
import org.richcode.campusdiary.Dialog.MyDialogListener;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("과제"));
        tabLayout.addTab(tabLayout.newTab().setText("메모"));
        tabLayout.addTab(tabLayout.newTab().setText("일기"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(false);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {

                    case R.id.nav_sub_menu_item01:
                        Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent.putExtra("link","https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EA%B3%84%EC%82%B0%EA%B8%B0");
                        startActivity(intent);
                        break;
                    case R.id.nav_sub_menu_item02:
                        Intent intent2 = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent2.putExtra("link","https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%EB%B2%88%EC%97%AD%EA%B8%B0");
                        startActivity(intent2);
                        break;
                    case R.id.nav_sub_menu_item03:
                        Intent intent3 = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent3.putExtra("link","https://search.naver.com/search.naver?sm=top_hty&fbm=1&ie=utf8&query=%ED%95%99%EC%A0%90%EA%B3%84%EC%82%B0%EA%B8%B0");
                        startActivity(intent3);
                        break;
                    case R.id.nav_sub_menu_item04:
                        Intent intent4 = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent4.putExtra("link","https://www.wpws.kr/hangang/");
                        startActivity(intent4);
                        break;
                    case R.id.nav_sub_menu_item05:
                        Intent intent5 = new Intent(getApplicationContext(),WebViewActivity.class);
                        intent5.putExtra("link","https://namu.wiki/w/%EB%82%98%EB%AC%B4%EC%9C%84%ED%82%A4:%EB%8C%80%EB%AC%B8");
                        startActivity(intent5);
                        break;

                }

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        DialogExit exitDialog = new DialogExit(MainActivity.this);
        exitDialog.setCanceledOnTouchOutside(false);
        exitDialog.setDialogListener(new MyDialogListener() {
            @Override
            public void onPositiveClicked(String input) {
                finish();
            }

            @Override
            public void onMemoClicked(MemoData input) {

            }

            @Override
            public void onHomeWorkClicked(HomeWorkData input) {

            }

            @Override
            public void onDiaryClicked(DiaryData input) {

            }

            @Override
            public void onNegativeClicked() {

            }
        });
        exitDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
