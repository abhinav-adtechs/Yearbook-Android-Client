package in.vit.yearbook.View.Dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.DashboardBook;
import in.vit.yearbook.R;

public class DashboardFragment extends Fragment {


    @BindView(R.id.activity_dashboard_view_rv_catalog)
    RecyclerView rvBooks ;

    List<DashboardBook> dashboardBooks = new ArrayList<>() ;
    DashboardBooksAdapter dashboardBooksAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_view, container, false) ;

        ButterKnife.bind(this, view) ;
        setInit();

        return view;
    }

    private void setInit() {

        dashboardBooksAdapter = new DashboardBooksAdapter(dashboardBooks, this.getActivity()) ;
        RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getActivity().getApplicationContext(),
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
