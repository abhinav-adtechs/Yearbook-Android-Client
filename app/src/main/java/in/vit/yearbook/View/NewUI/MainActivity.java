package in.vit.yearbook.View.NewUI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.Presenter.Dashboard.DashboardCommunicationInterface;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.Credits.CreditsFragment;
import in.vit.yearbook.View.NewUI.Dashboard.DashboardUpdatedFragment;
import in.vit.yearbook.View.NewUI.Team.TeamFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, DashboardCommunicationInterface{

    @BindView(R.id.new_activity_main_toolbar_main)
    ConstraintLayout toolbarMain ;

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

    private Integer currentState = Constants.STATE_DASHBOARD ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);

        ButterKnife.bind(this) ;
        //setSupportActionBar(toolbarMain);
        //getSupportActionBar().setTitle("");

        currentFragment = new DashboardUpdatedFragment() ;
        addFragmentTransaction(currentFragment);


        ibTabCredits.setOnClickListener(this);
        ibTabDashboard.setOnClickListener(this);
        ibTabTeam.setOnClickListener(this);
    }

    private void addFragmentTransaction(android.support.v4.app.Fragment currentFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.new_activity_main_fragment_frame, currentFragment)
                .commit();

        scaleUpDashboard();
    }

    private void makeFragmentTransaction(android.support.v4.app.Fragment nextFragment, String title) {

        if (nextFragment != null && currentFragment.getClass() != nextFragment.getClass()){
            Log.i("TAG", "makeFragmentTransaction: "+ currentFragment.getClass());
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in_slow, R.anim.fade_out_slow)
                    .replace(R.id.new_activity_main_fragment_frame, nextFragment)
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
                if (currentState == Constants.STATE_CREDITS){
                    scaleDownCredits();
                }
                else if (currentState == Constants.STATE_DASHBOARD){
                    scaleDownDashboard();
                }

                scaleUpTeam();
                break;

            case R.id.new_activity_main_tab_dashboard :
                nextFragment = new DashboardUpdatedFragment() ;
                makeFragmentTransaction(nextFragment, "Dashboard") ;
                if (currentState == Constants.STATE_CREDITS){
                    scaleDownCredits();
                }
                else if (currentState == Constants.STATE_TEAM){
                    scaleDownTeam();
                }

                scaleUpDashboard();
                break;

            case R.id.new_activity_main_tab_info :
                nextFragment = new CreditsFragment() ;
                makeFragmentTransaction(nextFragment, "Credits") ;
                if (currentState == Constants.STATE_TEAM){
                    scaleDownTeam();
                }
                else if (currentState == Constants.STATE_DASHBOARD){
                    scaleDownDashboard();
                }

                scaleUpCredits() ;
                break;
        }
    }

    private void scaleUpCredits() {
        ibTabCredits.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_white_24px));
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabCredits.startAnimation(anim);
        ibTabCredits.setClickable(false);
        currentState = Constants.STATE_CREDITS ;
    }

    private void scaleDownCredits() {
        ibTabCredits.setImageDrawable(getResources().getDrawable(R.drawable.ic_info_outline_white_24px));
        ScaleAnimation anim = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabCredits.startAnimation(anim);
        ibTabCredits.setClickable(true);
    }

    private void scaleUpDashboard() {
        ibTabDashboard.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_white_24px));
        ibTabDashboard.setAlpha(1.0f);
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabDashboard.startAnimation(anim);
        ibTabDashboard.setClickable(false);
        currentState = Constants.STATE_DASHBOARD ;
    }

    private void scaleDownDashboard() {
        ibTabDashboard.setImageDrawable(getResources().getDrawable(R.drawable.ic_dashboard_white_24px));
        ibTabDashboard.setAlpha(0.5f);
        ScaleAnimation anim = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabDashboard.startAnimation(anim);
        ibTabDashboard.setClickable(true);
    }

    private void scaleUpTeam() {
        ibTabTeam.setImageDrawable(getResources().getDrawable(R.drawable.ic_group_black_24px));
        ScaleAnimation anim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabTeam.startAnimation(anim);
        ibTabTeam.setClickable(false);
        currentState = Constants.STATE_TEAM ;
    }

    private void scaleDownTeam() {
        ibTabTeam.setImageDrawable(getResources().getDrawable(R.drawable.ic_people_outline_white_24px));
        ScaleAnimation anim = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF,0.0f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillEnabled(true);
        anim.setFillAfter(true);
        anim.setDuration(200);
        ibTabTeam.startAnimation(anim);
        ibTabTeam.setClickable(true);
    }

    @Override
    public void beginDownload(int year) {
        Log.i("TAG", "beginDownload: " + year);


    }

    @Override
    public void checkDownloadState(int year) {

    }
}
