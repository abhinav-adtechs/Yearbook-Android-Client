package in.vit.yearbook.View.NewUI.Dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import in.vit.yearbook.R;
import in.vit.yearbook.View.OldUI.Dashboard.DashboardBooksAdapter;


public class DashYearAdapter extends RecyclerView.Adapter<DashYearAdapter.YearViewHolder> {

    private OnItemClickListener onItemClickListener ;

    public DashYearAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private String[] years = {
            "2017",
            "2016",
            "2015",
            "2014"
    };

    public interface OnItemClickListener{
        public void onClick(int pos) ;
    }

    @Override
    public YearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item_rv_year, parent, false) ;
        return new YearViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(YearViewHolder holder, int position) {
        holder.tvYear.setText(years[position%4]);
        holder.bindListener(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class YearViewHolder extends RecyclerView.ViewHolder{

        private TextView tvYear ;

        public YearViewHolder(View itemView) {
            super(itemView);

            tvYear = (TextView) itemView.findViewById(R.id.new_item_rv_year_tv) ;
        }

        public void bindListener(final int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(position);
                }
            });
        }
    }
}
