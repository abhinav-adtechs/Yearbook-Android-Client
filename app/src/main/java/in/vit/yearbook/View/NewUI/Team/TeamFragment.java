package in.vit.yearbook.View.NewUI.Team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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

public class TeamFragment extends BaseFragment implements View.OnClickListener {

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



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_team, container, false) ;
        ButterKnife.bind(this, view) ;

        teamMembersAdapter = new TeamMembersAdapter(teamMembersList) ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        rvTeam.setLayoutManager(layoutManager);
        controller = AnimationUtils.loadLayoutAnimation(getActivity(), R.anim.layout_animation_fall_down);
        rvTeam.setLayoutAnimation(controller);
        rvTeam.setItemAnimator(new DefaultItemAnimator());
        rvTeam.setAdapter(teamMembersAdapter);
        updateEditorialTeam(2017);

        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibEditorial.setOnClickListener(this);
        ibDesign.setOnClickListener(this);
        ibPhotography.setOnClickListener(this);
        ibManagement.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_fragment_team_btn_editorial:
                updateEditorialTeam(2017);
                break;
            case R.id.new_fragment_team_btn_design:
                Log.i("TAG", "onClick: ");
                updateDesignTeam(2017);
                break;
            case R.id.new_fragment_team_btn_photography:
                updatePhotographyTeam(2017);
                break;
            case R.id.new_fragment_team_btn_management:
                updateManagementTeam(2017);
                break;
        }
    }

    void updateEditorialTeam(Integer year){
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
        rvTeam.scheduleLayoutAnimation();
    }

    void updateDesignTeam(Integer year){
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
        rvTeam.scheduleLayoutAnimation();
    }

    void updatePhotographyTeam(Integer year){
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
        rvTeam.scheduleLayoutAnimation();
    }

    void updateManagementTeam(Integer year){
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
        rvTeam.scheduleLayoutAnimation();
    }
}
