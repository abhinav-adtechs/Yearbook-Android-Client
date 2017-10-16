package in.vit.yearbook.View.NewUI.Dashboard;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;


import com.daimajia.numberprogressbar.NumberProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.Utils.AnimationUtils;
import in.vit.yearbook.Presenter.Main.MainActivityCommunicationInterface;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.NewUI.MainActivity;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;
import in.vit.yearbook.View.OldUI.Preview.BookPreviewActivity;


public class DashboardUpdatedFragment extends BaseFragment implements View.OnClickListener, MainActivityCommunicationInterface{

    @BindView(R.id.new_fragment_dashboard_rv_year)
    RecyclerView rvDashboardTopBook;

    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;

    @BindView(R.id.new_fragment_dashboard_btn_download)
    Button morphingBtnDownload;

    @BindView(R.id.new_fragment_dashboard_pb_download)
    NumberProgressBar nbpDownloadBar ;

    private DashYearAdapter dashYearAdapter ;
    private boolean showingDownloadState = false ;

    private AnimationUtils animationUtils ;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;
    private Integer currentYear = Integer.parseInt("2017") ;

    private MainActivity mainActivity ;
    private Context context ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        mainActivity = (MainActivity) this.getActivity() ;
        ButterKnife.bind(this, view) ;
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
        final RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(context.getApplicationContext(),
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

                    setBooks(pos) ;
                }
            }
        });

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity ;
    }

    private void setBooks(int position) {

        if (showingDownloadState){
            Log.i("TAG", "showingDownloadState: " + position);
            hideDownloadSettings();
        }

        switch (position%4){
            case 0: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2017_cover));
                currentYear = Integer.parseInt("2017") ;
                break;
            case 1: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                currentYear = Integer.parseInt("2016") ;
                break;
            case 2: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                currentYear = Integer.parseInt("2015") ;
                break;
            case 3: ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                currentYear = Integer.parseInt("2014") ;
                break;
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.new_fragment_dashboard_iv_cover:
                if (!showingDownloadState){
                    mainActivity.checkDownloadState(currentYear);
                    viewDownloadSettings();
                }else {
                   hideDownloadSettings();
                }
                break;
            case R.id.new_fragment_dashboard_btn_download:
                Log.i("TAG", "onClick: " + morphingBtnDownload.getText());
                if (ActivityCompat.checkSelfPermission(
                        context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                }else if (morphingBtnDownload.getText() == "OPEN BOOK"){
                    Intent intent = new Intent(context, BookPreviewActivity.class) ;
                    String fileName = Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + currentYear + ".pdf";
                    Log.i("TAG", "onClick: " + fileName);
                    intent.putExtra("fileName", fileName) ;
                    startActivity(intent);
                }else {
                    mainActivity.beginDownload(currentYear);
                }

                /*DOWNLOADING*/
                break;
        }
    }


    private void viewDownloadSettings(){
        ivCoverPhoto.startAnimation(animationUtils.slideLeftAnimation());
        showingDownloadState = true ;
        morphingBtnDownload.startAnimation(animationUtils.fadeInAnimation());
        nbpDownloadBar.startAnimation(animationUtils.fadeInAnimation());

    }

    private void hideDownloadSettings(){
        ivCoverPhoto.startAnimation(animationUtils.slideRightAnimation());
        showingDownloadState = false ;
        morphingBtnDownload.startAnimation(animationUtils.fadeOutAnimation());
        nbpDownloadBar.startAnimation(animationUtils.fadeOutAnimation());

    }

    @Override
    public void notifyStatus(int status) {
        Log.d("TAG", "notifyStatus() called with: status = [" + status + "]");
        switch (status){
            case 0:
                Log.i("TAG", "notifyStatus: case 0"  );
                nbpDownloadBar.setMax(100);
                nbpDownloadBar.setProgress(100);
                morphingBtnDownload.setText("OPEN BOOK");
                Log.i("TAG", "notifyStatus: " + morphingBtnDownload.getText() + " : " + nbpDownloadBar.getProgress());
                break;
            case -1:
                nbpDownloadBar.setMax(100);
                nbpDownloadBar.setProgress(0);
                setDownloadText() ;
                break;
            default:
                nbpDownloadBar.setMax(100);
                nbpDownloadBar.setProgress(status);
                morphingBtnDownload.setText("DOWNLOADING...");
                break;
        }

        Log.i("TAG", "notifyStatus: " + nbpDownloadBar.getProgress());

    }

    @Override
    public void updateDownloadingStatus(int year, int progress) {

        Log.i("TAG", "updateDownloadingStatus: Downloading..." );
        morphingBtnDownload.setText("DOWNLOADING...");

        if (currentYear == year){
            nbpDownloadBar.setMax(100);
            nbpDownloadBar.setProgress(progress);
        }
    }


    private void setDownloadText() {

        switch (currentYear){
            case 2017:morphingBtnDownload.setText("Download 75MB");
                break;
            case 2016:morphingBtnDownload.setText("Download 42MB");
                break;
            case 2015:morphingBtnDownload.setText("Download 2MB");
                break;
            case 2014:morphingBtnDownload.setText("Download 6MB");
                break;
        }
    }

    @Override
    public void scrollToYear(int year) {
        Log.d("TAG", "scrollToYear() called with: position = [" + Math.abs(year-2017) + "]");
        rvDashboardTopBook.smoothScrollToPosition(Math.abs(year-2017));
        viewDownloadSettings();
    }
}
