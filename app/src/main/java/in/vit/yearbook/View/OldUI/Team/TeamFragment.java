package in.vit.yearbook.View.OldUI.Team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;


public class TeamFragment extends Fragment {

    @BindView(R.id.fragment_team_view_rv_team_list)
    RecyclerView rvTeamList ;


    private TeamRVAdapter teamRVAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_view, container, false);

        ButterKnife.bind(this, view) ;

        setInit() ;
        return view ;
    }

    private void setInit() {


        teamRVAdapter = new TeamRVAdapter(teamList, this.getActivity()) ;
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false) ;
        rvTeamList.setLayoutManager(layoutManager);
        rvTeamList.setAdapter(teamRVAdapter);

    }


    String[][] teamList = {
            {"Editorial", "9010"},
            {"Aarti Susan Kuruvilla", "9011"},
            {"Shreya Ishani", "9011"},
            {"Abhinav Das", "9011"},
            {"", "9011"},
            {"Adrija Khan", "9011"},
            {"Amita Varma", "9011"},
            {"Akshaya Parthasarathy", "9011"},
            {"K. Damodaran", "9011"},
            {"Anshaj Khare", "9011"},
            {"Sayan B", "9011"},
            {"Anuradha Iyer", "9011"},
            {"Ananya Kar", "9011"},
            {"Aparna Ramachandran", "9011"},


            {"Design", "9010"},
            {"Shreya Gupta", "9011"},
            {"Pranav Chimote", "9011"},
            {"", "9011"},
            {"Rahul Ghosh", "9011"},
            {"Adhvik Madhav", "9011"},
            {"Sreeram Pallavi", "9011"},
            {"Kshitij Srivastava", "9011"},
            {"Ajay Kumar", "9011"},


            {"Photography", "9010"},
            {"Siddhartha Srivastava", "9011"},
            {"Mridul Markandya", "9011"},
            {"Manojkummar M", "9011"},
            {"MSK Siddhartha", "9011"},
            {"", "9011"},
            {"Krishnav biyani", "9011"},
            {"K. Damodaran", "9011"},
            {"K. Mohiuddin Farooqui", "9011"},
            {"Anmol Srinivas", "9011"},
            {"M.kishore.", "9011"},


            {"Management", "9010"},
            {"Harshil Shah", "9011"},
            {"Prakhar Sahay", "9011"},
            {"Ujjawal Surana", "9011"},
            {"D.Akhileshwar Reddy", "9011"},
            {"Abhivishrut Singh", "9011"},
            {"", "9011"},
            {"Nishta Jetly", "9011"},
            {"Sneha Aggarwal", "9011"},
            {"Soham Dutta", "9011"},
            {"Debarati Ray", "9011"},
            {"Ankita Das", "9011"},
            {"Nandita Chandel", "9011"},
            {"Abhishek Gupta", "9011"},
            {"Puneet", "9011"},
            {"Revati", "9011"},
            {"Malvika Kharbanda", "9011"},
            {"Amrita", "9011"},
            {"Akanksha Lal", "9011"},
            {"Swastika", "9011"},
            {"Soumyadeep", "9011"},
            {"Priyal", "9011"},
            {"Sneha", "9011"},
            {"Siddhant", "9011"},
            {"Akhil", "9011"},
            {"Abhas Singh", "9011"},
            {"Ayush Bhattacharya", "9011"},
            {"Y. Mohan SaiVenkat", "9011"},
            {"Meghana P. R.", "9011"},
            {"Sidarth Kumaran", "9011"},
            {"T Srihari", "9011"},
            {"M K Manoj", "9011"},
            {"Ayush Lohiya", "9011"},
            {"Bodapati Saivineeth", "9011"},
            {"B Kinnera", "9011"},
            {"Neha Duggal", "9011"},
            {"A Swathi", "9011"},
            {"Pavan Sreenivas", "9011"},
            {"Anjani", "9011"},
            {"Awari Renuka Sambhaji", "9011"},
            {"S Naveen Kumar", "9011"},
            {"M V Anand", "9011"},
            {"P Ekalaivan", "9011"},
            {"Amandeep Batra", "9011"},
            {"Mishank Modi", "9011"},
            {"D Satya Saikrishna", "9011"},
            {"Kota Sudhakar", "9011"},
            {"A Mohith", "9011"},
            {"Rivana Christie", "9011"},
            {"Ranganath Narayan", "9011"},
            {"Ann George", "9011"},
            {"Akshay Shaji", "9011"},
            {"Nitya Reddy", "9011"},
            {"Kshitij Sharama", "9011"},
            {"Pallavi Sreeram", "9011"},
            {"Srijani", "9011"},
            {"Kashish", "9011"},
            {"Shivam", "9011"},
            {"Nithya", "9011"},
            {"Praveen", "9011"},
            {"Rajat", "9011"},

    } ;

}
