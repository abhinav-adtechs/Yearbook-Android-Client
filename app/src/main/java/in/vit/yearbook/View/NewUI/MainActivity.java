package in.vit.yearbook.View.NewUI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.new_activity_main_toolbar_main)
    Toolbar toolbarMain ;

    @BindView(R.id.new_activity_main_fragment_title)
    TextView fragmentTitle ;

    @BindView(R.id.new_activity_main_fragment_frame)
    FrameLayout fragmentFrameLayout ;

    @BindView(R.id.new_activity_main_tab_team)
    ImageButton ibTabTeam;

    @BindView(R.id.new_activity_main_tab_info)
    ImageButton ibTabCredits;

    @BindView(R.id.new_activity_main_tab_dashboard)
    ImageButton ibTabDashboard;

    private android.support.v4.app.Fragment currentFragment = null ;
    private android.support.v4.app.Fragment nextFragment = null ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);

        ButterKnife.bind(this) ;
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("");

        currentFragment = new DashboardFragment() ;
        addFragmentTransaction(currentFragment);


        ibTabCredits.setOnClickListener(this);
        ibTabDashboard.setOnClickListener(this);
        ibTabTeam.setOnClickListener(this);
    }

    private void addFragmentTransaction(android.support.v4.app.Fragment currentFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.new_activity_main_fragment_frame, currentFragment)
                .setCustomAnimations(R.anim.fade_in_slow, R.anim.fade_out_slow)
                .commit();

    }

    private void makeFragmentTransaction(android.support.v4.app.Fragment nextFragment, String title) {

        if (nextFragment != null && currentFragment.getClass() != nextFragment.getClass()){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.new_activity_main_fragment_frame, nextFragment)
                    .setCustomAnimations(R.anim.fade_in_slow, R.anim.fade_out_slow)
                    .commit();

            currentFragment = nextFragment ;

            fragmentTitle.setText(title);
        }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.new_activity_main_tab_team :
                nextFragment = new TeamFragment() ;
                makeFragmentTransaction(nextFragment, "Team") ;
                break;

            case R.id.new_activity_main_tab_dashboard :
                nextFragment = new DashboardFragment() ;
                makeFragmentTransaction(nextFragment, "Dashboard") ;
                break;

            case R.id.new_activity_main_tab_info :
                nextFragment = new CreditsFragment() ;
                makeFragmentTransaction(nextFragment, "Credits") ;
                break;
        }
    }
}
