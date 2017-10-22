package in.vit.yearbook.View.NewUI.Team;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.NewUI.Dashboard.DashYearAdapter;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;

public class TeamFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.new_fragment_team_rv_year)
    RecyclerView rvYear ;
    DashYearAdapter dashYearAdapter ;

    @BindView(R.id.new_fragment_team_btn_editorial)
    ImageButton ibEditorial ;

    @BindView(R.id.new_fragment_team_btn_design)
    ImageButton ibDesign ;

    @BindView(R.id.new_fragment_team_btn_photography)
    ImageButton ibPhotography ;

    @BindView(R.id.new_fragment_team_btn_management)
    ImageButton ibManagement ;

    @BindView(R.id.new_fragment_team_rv_names)
    RecyclerView rvTeam ;

    List<String> teamMembersList = new ArrayList<>() ;
    TeamMembersAdapter teamMembersAdapter ;

    private Integer currentState = Constants.STATE_EDITORIAL ;
    LayoutAnimationController controller ;

    private int currentYear = 2017 ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.testing_activity_test, container, false) ;
        ButterKnife.bind(this, view) ;

        teamMembersAdapter = new TeamMembersAdapter(teamMembersList) ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        rvTeam.setLayoutManager(layoutManager);
        controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);
        rvTeam.setLayoutAnimation(controller);
        rvTeam.setItemAnimator(new DefaultItemAnimator());
        rvTeam.setAdapter(teamMembersAdapter);
        updateEditorialTeam(currentYear);

        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibEditorial.setOnClickListener(this);
        ibDesign.setOnClickListener(this);
        ibPhotography.setOnClickListener(this);
        ibManagement.setOnClickListener(this);
        setupRv();
    }


    private void setupRv() {

        dashYearAdapter = new DashYearAdapter(new DashYearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                rvYear.smoothScrollToPosition(pos);
            }
        }) ;
        final RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getActivity().getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false) ;
        rvYear.setLayoutManager(layoutManager);
        rvYear.setAdapter(dashYearAdapter);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvYear);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvYear.smoothScrollToPosition(0);
            }
        }, 500) ;


        rvYear.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    int pos = layoutManager.getPosition(centerView);
                    Log.e("Snapped Item Position:","" + pos);
                    setCurrentYear(pos);
                }
            }
        });



    }

    private void setCurrentYear(int pos) {
        switch (pos){
            case 0:
                currentYear = 2017 ;
                break;
            case 1:
                currentYear = 2016 ;
                break;
            case 2:
                currentYear = 2015 ;
                break;
            case 3:
                currentYear = 2014 ;
                break;
        }
        updateEditorialTeam(currentYear);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_fragment_team_btn_editorial:
                updateEditorialTeam(currentYear);
                break;
            case R.id.new_fragment_team_btn_design:
                Log.i("TAG", "onClick: ");
                updateDesignTeam(currentYear);
                break;
            case R.id.new_fragment_team_btn_photography:
                updatePhotographyTeam(currentYear);
                break;
            case R.id.new_fragment_team_btn_management:
                updateManagementTeam(currentYear);
                break;
        }

        if (!rvTeam.isAnimating()){
            rvTeam.scheduleLayoutAnimation();
        }

    }

    void updateEditorialTeam(Integer year){
        clearSelection();
        ibEditorial.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar_filled));
        switch (year){
            case 2017:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
            case 2016:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2016_team_editorial)) ;
                break;
            case 2015:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
            case 2014:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
        }
        teamMembersAdapter.updateArrayList(teamMembersList);
        teamMembersAdapter.notifyDataSetChanged();
    }

    void updateDesignTeam(Integer year){
        clearSelection();
        ibDesign.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar_filled));
        currentState = Constants.STATE_DESIGN ;
        switch (year){
            case 2017:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_design)) ;
                break;
            case 2016:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2016_team_design)) ;
                break;
            case 2015:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
            case 2014:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
        }
        teamMembersAdapter.updateArrayList(teamMembersList);
        teamMembersAdapter.notifyDataSetChanged();
    }

    void updatePhotographyTeam(Integer year){
        clearSelection();
        ibPhotography.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar_filled));
        currentState = Constants.STATE_PHOTOGRAPHY ;
        switch (year){
            case 2017:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_photography)) ;
                break;
            case 2016:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2016_team_photography)) ;
                break;
            case 2015:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
            case 2014:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
        }
        teamMembersAdapter.updateArrayList(teamMembersList);
        teamMembersAdapter.notifyDataSetChanged();
    }

    void updateManagementTeam(Integer year){
        clearSelection();
        ibManagement.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar_filled));
        currentState = Constants.STATE_MANAGEMENT ;
        switch (year){
            case 2017:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_management)) ;
                break;
            case 2016:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2016_team_management)) ;
                break;
            case 2015:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
            case 2014:
                teamMembersList = Arrays.asList(getResources().getStringArray(R.array.yb2017_team_editorial)) ;
                break;
        }
        teamMembersAdapter.updateArrayList(teamMembersList);
        teamMembersAdapter.notifyDataSetChanged();
    }

    private void clearSelection(){
        ibDesign.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar));
        ibEditorial.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar));
        ibManagement.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar));
        ibPhotography.setBackground(getResources().getDrawable(R.drawable.rounded_border_action_bar));
    }
}
