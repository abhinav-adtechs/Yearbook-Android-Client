package in.vit.yearbook.View.NewUI.Team;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.vit.yearbook.R;


public class TeamMembersAdapter extends RecyclerView.Adapter<TeamMembersAdapter.MembersViewHolder> {

    private List<String> members ;

    public TeamMembersAdapter(List<String> members) {
        this.members = members;
    }

    public void updateArrayList(List<String> members){
        this.members = members ;
    }

    @Override
    public MembersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item_rv_team, parent, false) ;
        return new MembersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MembersViewHolder holder, int position) {
        holder.tvNames.setText(members.get(position));
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public class MembersViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNames ;

        public MembersViewHolder(View itemView) {
            super(itemView);

            tvNames = (TextView) itemView.findViewById(R.id.new_item_rv_team_tv) ;
        }
    }

}
