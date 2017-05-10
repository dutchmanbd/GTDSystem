package com.zxdmjr.gtdsystem;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.zxdmjr.gtdsystem.Activitys.AboutActivity;
import com.zxdmjr.gtdsystem.Activitys.HowToUseActivity;
import com.zxdmjr.gtdsystem.Objects.AppConfig;
import com.zxdmjr.gtdsystem.Objects.Task;
import com.zxdmjr.gtdsystem.TabFragment.CollectionFragment;
import com.zxdmjr.gtdsystem.TabFragment.OrganizeFragment;
import com.zxdmjr.gtdsystem.TabFragment.ReviewFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CollectionFragment.OnDataUpdateListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private List<Task> tasks;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){

            case R.id.action_how_to_use:

                Intent howtouseIntent = new Intent(this, HowToUseActivity.class);
                howtouseIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(howtouseIntent);

                return true;

            case R.id.action_about:

                Intent aboutInent = new Intent(this, AboutActivity.class);
                aboutInent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(aboutInent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDataUpdate() {


        OrganizeFragment organizeFragment = (OrganizeFragment)
                getSupportFragmentManager().findFragmentById(R.id.rlOrgContainer);

        if (organizeFragment != null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
//            articleFrag.updateArticleView(position);
            organizeFragment.getData();

        } else {
            // Otherwise, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            OrganizeFragment newFragment = new OrganizeFragment();
//            Bundle args = new Bundle();
//            args.putInt(OrganizeFragment.ARG_POSITION, position);
//            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.rlOrgContainer, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();


        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).



            switch (position) {
                case 0:
                    OrganizeFragment organizeFragment = new OrganizeFragment();
                    return organizeFragment;
                case 1:
                    CollectionFragment collectionFragment = new CollectionFragment();
                    return collectionFragment;
//                case 2:
//                    ReviewFragment fragment = new ReviewFragment();
//                    return fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Organize";
                case 1:
                    return "Collection";
//                case 2:
//                    return "How To Use";
            }
            return null;
        }
    }
}
