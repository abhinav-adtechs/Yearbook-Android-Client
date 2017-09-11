package in.vit.yearbook.View.Dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.About.AboutFragment;
import in.vit.yearbook.View.BaseActivity;
import in.vit.yearbook.View.Team.TeamFragment;

public class MainActivity extends BaseActivity {

    private android.support.v4.app.Fragment currentFragment = null ;
    private android.support.v4.app.Fragment nextFragment = null ;

    @BindView(R.id.activity_main_toolbar_main)
    Toolbar toolbarMain ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this) ;

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("");


        currentFragment = new DashboardFragment() ;
        addFragmentTransaction(currentFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_dashboard:
                    nextFragment = new DashboardFragment() ;
                    makeFragmentTransaction(nextFragment) ;
                    return true;
                case R.id.navigation_team:
                    nextFragment = new TeamFragment() ;
                    makeFragmentTransaction(nextFragment) ;
                    return true;
                case R.id.navigation_about:
                    nextFragment = new AboutFragment() ;
                    makeFragmentTransaction(nextFragment) ;
                    return true;
            }


            return false;


        }


    };

    private void makeFragmentTransaction(android.support.v4.app.Fragment nextFragment) {

        if (nextFragment != null && currentFragment.getClass() != nextFragment.getClass()){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.activity_main_framelayout, nextFragment)
                    .commit();

            currentFragment = nextFragment ;
        }

    }

    private void addFragmentTransaction(android.support.v4.app.Fragment currentFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.activity_main_framelayout, currentFragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        FragmentManager fm = getSupportFragmentManager();

        if(fm.getBackStackEntryCount()>=2) {
            String selectedText = fm.getBackStackEntryAt((fm.getBackStackEntryCount() - 2)).getName();
        }else if (fm.getBackStackEntryCount() == 1){
            super.onBackPressed() ;
        }

    }
}
