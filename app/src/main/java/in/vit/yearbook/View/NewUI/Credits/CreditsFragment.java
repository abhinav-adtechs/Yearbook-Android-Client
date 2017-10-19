package in.vit.yearbook.View.NewUI.Credits;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.NewUI.Team.TeamMembersAdapter ;

public class CreditsFragment extends BaseFragment {


    @BindView(R.id.new_fragment_credits_tv_developed)
    TextView tvDeveloped ;

    @BindView(R.id.new_fragment_credits_rv_credits_list)
    RecyclerView rvCredits ;

    List<String> creditsList = new ArrayList<>() ;
    TeamMembersAdapter creditsAdapter ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_credits, container, false) ;
        ButterKnife.bind(this, view) ;

        tvDeveloped.setText("Designed and Developed with " + getEmojiByUnicode(0x2764) + " by Ajay Kumar and Abhinav Das");
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        creditsAdapter = new TeamMembersAdapter(creditsList) ;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext()) ;
        rvCredits.setLayoutManager(layoutManager);
        rvCredits.setAdapter(creditsAdapter);
        creditsList = Arrays.asList(getResources().getStringArray(R.array.credits_list)) ;
        creditsAdapter.updateArrayList(creditsList);
        creditsAdapter.notifyDataSetChanged();
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
