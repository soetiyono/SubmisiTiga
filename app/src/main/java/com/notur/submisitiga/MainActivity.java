package com.notur.submisitiga;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;


import com.notur.submisitiga.adapter.SectionsPagerAdapter;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    String language = "";
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        language = getIntent().getStringExtra(TAG);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_english:
                Language(getString(R.string.english));
                return true;

            case R.id.action_indonesia:
                Language(getString(R.string.indo));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Language(String codeLanguage) {
        Configuration conf = getResources().getConfiguration();
        conf.locale = new Locale(codeLanguage);
        DisplayMetrics dm = new DisplayMetrics();
        Resources res = getResources();
        res.updateConfiguration(conf,dm);
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra(TAG,codeLanguage);
        startActivity(intent);
        finish();
    }

}