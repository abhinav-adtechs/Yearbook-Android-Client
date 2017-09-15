package in.vit.yearbook.View.Team;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.R;


public class TeamRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private String[][] teamList ;
    private Context context ;

    public TeamRVAdapter(String[][] teamList, Context context) {
        this.teamList = teamList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView ;

        if (viewType == Constants.LIST_TYPE_HEADER){
            itemView =  LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_team_list_header, parent, false) ;
            return new TeamViewHolderHeader(itemView) ;
        }else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_team_list_member, parent, false) ;
            return new TeamViewHolderMember(itemView) ;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int type = getItemViewType(position) ;
        if(type == Constants.LIST_TYPE_HEADER){
            TeamViewHolderHeader holderHeader = (TeamViewHolderHeader) holder ;
            holderHeader.tvTitle.setText(teamList[position][0]);
            switch (teamList[position][0]){
                case "Editorial" :
                    holderHeader.ivDescription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_notepad_2));
                    break;
                case "Design" :
                    holderHeader.ivDescription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_layers));
                    break;
                case "Photography" :
                    holderHeader.ivDescription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_photo_camera));
                    break;
                case "Management" :
                    holderHeader.ivDescription.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_customer_service));
                    break;
                default:
            }

        }else {
            TeamViewHolderMember holderMember = (TeamViewHolderMember) holder ;
            holderMember.tvMemberName.setText(teamList[position][0]);
        }
    }


    @Override
    public int getItemCount() {
        return teamList.length ;
    }

    public class TeamViewHolderHeader extends RecyclerView.ViewHolder{

        private TextView tvTitle ;
        private ImageView ivDescription ;

        public TeamViewHolderHeader(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.item_team_list_header_title) ;
            ivDescription = (ImageView) itemView.findViewById(R.id.item_team_list_header_iv) ;
        }
    }

    public class TeamViewHolderMember extends RecyclerView.ViewHolder{

        private TextView tvMemberName ;

        public TeamViewHolderMember(View itemView) {
            super(itemView);

            tvMemberName = (TextView) itemView.findViewById(R.id.item_team_list_member_tv) ;

        }
    }


    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(teamList[position][1]) ;
    }

}
