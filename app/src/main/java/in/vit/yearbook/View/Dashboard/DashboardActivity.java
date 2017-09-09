package in.vit.yearbook.View.Dashboard;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.DashboardBook;
import in.vit.yearbook.R;
import in.vit.yearbook.View.BaseActivity;

public class DashboardActivity extends BaseActivity {


    @BindView(R.id.activity_dashboard_view_rv_catalog)
    RecyclerView rvBooks ;

    List<DashboardBook> dashboardBooks = new ArrayList<>() ;
    DashboardBooksAdapter dashboardBooksAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard_view);

        ButterKnife.bind(this) ;

        setInit() ;
    }

    private void setInit() {

        dashboardBooksAdapter = new DashboardBooksAdapter(dashboardBooks, this) ;
        RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false) ;
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setAdapter(dashboardBooksAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvBooks);

        addBooksToList() ;

        rvBooks.smoothScrollToPosition(0);

    }

    private void addBooksToList() {

        dashboardBooks.add(new DashboardBook(2017, "Class of 2017"));
        dashboardBooks.add(new DashboardBook(2016, "Class of 2016"));
        dashboardBooks.add(new DashboardBook(2015, "Class of 2015"));
        dashboardBooks.add(new DashboardBook(2014, "Class of 2014"));

        dashboardBooksAdapter.notifyDataSetChanged();
    }
}
