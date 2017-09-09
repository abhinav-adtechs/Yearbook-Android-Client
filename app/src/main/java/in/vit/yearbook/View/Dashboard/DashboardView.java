package in.vit.yearbook.View.Dashboard;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.BaseActivity;

public class DashboardView extends BaseActivity {


    @BindView(R.id.activity_dashboard_view_rv_catalog)
    RecyclerView rvBooks ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard_view);

        ButterKnife.bind(this) ;
    }
}
