package in.vit.yearbook.View.OldUI.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.DashboardBook;
import in.vit.yearbook.R;
import in.vit.yearbook.View.OldUI.Preview.BookPreviewActivity;

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

    @Override
    public void onResume() {
        super.onResume();
        Log.i("TAG", "onResume: ");
    }

    private void setInit() {

        dashboardBooksAdapter = new DashboardBooksAdapter(dashboardBooks, this.getActivity(), new DashboardBooksAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                handleBookPreviewClick(position) ;
            }
        }) ;
        RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getActivity().getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false) ;
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setAdapter(dashboardBooksAdapter);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvBooks);

        addBooksToList() ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvBooks.smoothScrollToPosition(1);
            }
        }, 20) ;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void addBooksToList() {

        dashboardBooks.add(new DashboardBook(2018, "Class of 2018"));
        dashboardBooks.add(new DashboardBook(2017, "Class of 2017"));
        dashboardBooks.add(new DashboardBook(2016, "Class of 2016"));
        dashboardBooks.add(new DashboardBook(2015, "Class of 2015"));
        dashboardBooks.add(new DashboardBook(2014, "Class of 2014"));

        dashboardBooksAdapter.notifyDataSetChanged();
    }

    private void handleBookPreviewClick(int position) {

        Intent intent = new Intent(this.getActivity(), BookPreviewActivity.class) ;
        startActivity(intent);
    }
}
