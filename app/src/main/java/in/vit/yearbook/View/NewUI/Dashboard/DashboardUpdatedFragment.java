package in.vit.yearbook.View.NewUI.Dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;


import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.Utils.AnimationUtils;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.NewUI.MainActivity;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;


public class DashboardUpdatedFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_rv_year)
    RecyclerView rvDashboardTopBook;
    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;
    @BindView(R.id.new_fragment_dashboard_btn_download)
    Button morphingBtnDownload;

    private DashYearAdapter dashYearAdapter ;
    private boolean showStateSelected = false ;

    private AnimationUtils animationUtils ;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;
    private Integer currentYear = Integer.parseInt("2017") ;

    private MainActivity mainActivity ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        ButterKnife.bind(this, view) ;
        mainActivity = (MainActivity) this.getActivity() ;
        animationUtils = new AnimationUtils() ;
        ivCoverPhoto.setOnClickListener(this);
        morphingBtnDownload.setOnClickListener(this);
        setupRv();

        return view ;
    }

    private void setupRv() {

        dashYearAdapter = new DashYearAdapter(new DashYearAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                rvDashboardTopBook.smoothScrollToPosition(pos);
            }
        }) ;
        final RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getActivity().getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false) ;
        rvDashboardTopBook.setLayoutManager(layoutManager);
        rvDashboardTopBook.setAdapter(dashYearAdapter);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvDashboardTopBook);

        rvDashboardTopBook.requestFocus() ;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvDashboardTopBook.smoothScrollToPosition(0);
            }
        }, 400) ;


        rvDashboardTopBook.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    int pos = layoutManager.getPosition(centerView);
                    setCurrentYear(pos);
                    setBooks(pos) ;
                }
            }
        });

    }

    private void setBooks(int position) {

        if (showStateSelected){
            hideDownloadSettings();
        }

        switch (position%4){
            case 0: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2017_cover));
                break;
            case 1: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
            case 2: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
            case 3: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.new_fragment_dashboard_iv_cover:
                if (!showStateSelected){
                    viewDownloadSettings();
                }else {
                   hideDownloadSettings();
                }
                break;
            case R.id.new_fragment_dashboard_btn_download:
                if (ActivityCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                }else {
                    mainActivity.beginDownload(currentYear);
                }

                /*DOWNLOADING*/
                break;
        }
    }


    private void viewDownloadSettings(){
        ivCoverPhoto.startAnimation(animationUtils.slideLeftAnimation());
        showStateSelected = !showStateSelected ;
        morphingBtnDownload.startAnimation(animationUtils.fadeInAnimation());
    }

    private void hideDownloadSettings(){
        ivCoverPhoto.startAnimation(animationUtils.slideRightAnimation());
        showStateSelected = !showStateSelected ;
        morphingBtnDownload.startAnimation(animationUtils.fadeOutAnimation());
    }

    void setCurrentYear(int state){
        switch (state){
            case 0: currentYear = Integer.parseInt("2017") ;
                break;
            case 1: currentYear = Integer.parseInt("2016") ;
                break;
            case 2: currentYear = Integer.parseInt("2015") ;
                break;
            case 3: currentYear = Integer.parseInt("2014") ;
                break;
        }
    }
}
