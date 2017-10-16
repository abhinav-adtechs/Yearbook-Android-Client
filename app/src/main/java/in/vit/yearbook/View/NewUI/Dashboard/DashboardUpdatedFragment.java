package in.vit.yearbook.View.NewUI.Dashboard;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.dd.morphingbutton.MorphingButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.Utils.AnimationUtils;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;


public class DashboardUpdatedFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_rv_year)
    RecyclerView rvDashboardTopBook;
    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;
    @BindView(R.id.new_fragment_dashboard_btn_download)
    MorphingButton morphingBtnDownload;

    private DashYearAdapter dashYearAdapter ;
    private boolean showStateSelected = false ;

    private AnimationUtils animationUtils ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        ButterKnife.bind(this, view) ;
        animationUtils = new AnimationUtils() ;
        ivCoverPhoto.setOnClickListener(this);
        morphingBtnDownload.setOnClickListener(this);
        setupMorphingButton();
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
                    //setCurrentYear(pos);
                    setBooks(pos) ;
                }
            }
        });

    }

    private void setBooks(int position) {

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

    private void setupMorphingButton(){

        MorphingButton.Params circle = MorphingButton.Params.create()
                .cornerRadius(20)
                .color(getResources().getColor(R.color.mb_blue_dark))
                .colorPressed(getResources().getColor(R.color.mb_blue_dark))
                .text("Download 75MB") ;
        morphingBtnDownload.morph(circle);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.new_fragment_dashboard_iv_cover:
                if (!showStateSelected){
                    ivCoverPhoto.startAnimation(animationUtils.slideLeftAnimation());
                    showStateSelected = !showStateSelected ;
                }else {
                    ivCoverPhoto.startAnimation(animationUtils.slideRightAnimation());
                    showStateSelected = !showStateSelected ;
                }
                break;
            case R.id.new_fragment_dashboard_btn_download:
                MorphingButton.Params circle = MorphingButton.Params.create()
                        .text("Downloading") ;
                morphingBtnDownload.morph(circle);
                break;
        }
    }
}
