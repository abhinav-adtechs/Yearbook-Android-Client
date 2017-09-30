package in.vit.yearbook.View.NewUI.Dashboard;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.liulishuo.filedownloader.FileDownloader;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.BookDownloadingListener;
import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;


public class DashboardFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;

    @BindView(R.id.new_fragment_dashboard_rv_year)
    RecyclerView rvDashboardTopBook;

    @BindView(R.id.new_fragment_dashboard_btn_download)
    ImageButton ibDownload ;

    @BindView(R.id.new_fragment_dashboard_tv_details)
    TextView tvDownloadDetails ;

    @BindView(R.id.new_fragment_dashboard_tv_size)
    TextView tvDownloadSize ;

    private boolean downloadingState = false ;
    private boolean showStateSelected = false ;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;
    private BookDownloadingListener bookDownloadingListener ;

    private NotificationManager notificationManager ;
    private NotificationCompat.Builder notificationBuilder ;

    private DashYearAdapter dashYearAdapter ;

    private String currentYear = "2017" ;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        ButterKnife.bind(this, view);

        ivCoverPhoto.setOnClickListener(this);
        ibDownload.setOnClickListener(this);

        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRv() ;

    }

    private void setupRv() {

        dashYearAdapter = new DashYearAdapter() ;
        final RecyclerView.LayoutManager layoutManager = new CenterZoomLayoutManager(this.getActivity().getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false) ;
        rvDashboardTopBook.setLayoutManager(layoutManager);
        rvDashboardTopBook.setAdapter(dashYearAdapter);

        final SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvDashboardTopBook);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvDashboardTopBook.smoothScrollToPosition(0);
            }
        }, 20) ;


        rvDashboardTopBook.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    View centerView = snapHelper.findSnapView(layoutManager);
                    int pos = layoutManager.getPosition(centerView);
                    Log.e("Snapped Item Position:","" + pos);
                    setCurrentYear(pos);
                    setBooks(pos) ;
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_fragment_dashboard_iv_cover:

                if (!showStateSelected){
                    ivCoverPhoto.startAnimation(slideLeftAnimation());
                    AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f) ;
                    fadeIn.setDuration(1200);
                    fadeIn.setFillEnabled(true);
                    fadeIn.setFillAfter(true);
                    tvDownloadDetails.setAnimation(fadeIn);
                    tvDownloadSize.setAnimation(fadeIn);
                    ibDownload.setAnimation(fadeIn);
                    ibDownload.setVisibility(View.VISIBLE);
                    showStateSelected = true ;
                    ibDownload.setEnabled(true);
                }else {
                    ivCoverPhoto.startAnimation(slideRightAnimation());
                    AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
                    fadeOut.setDuration(800);
                    fadeOut.setFillEnabled(true);
                    fadeOut.setFillAfter(true);
                    tvDownloadDetails.setAnimation(fadeOut);
                    tvDownloadSize.setAnimation(fadeOut);
                    ibDownload.setAnimation(fadeOut);
                    ibDownload.setVisibility(View.VISIBLE);
                    showStateSelected = false ;
                    ibDownload.setEnabled(false);
                }

                break;

            case R.id.new_fragment_dashboard_btn_download:
                Log.i("TAG", "onClick: ");
                if (ActivityCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                }else {
                    if (!downloadingState)
                        startDownloading(currentYear) ;
                    else
                        pauseDownloading() ;
                }
                break;

        }
    }


    private void setBooks(int position) {

        if (showStateSelected){
            ivCoverPhoto.startAnimation(slideRightAnimation());
            AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
            fadeOut.setDuration(800);
            fadeOut.setFillEnabled(true);
            fadeOut.setFillAfter(true);
            tvDownloadDetails.setAnimation(fadeOut);
            tvDownloadSize.setAnimation(fadeOut);
            ibDownload.setAnimation(fadeOut);
            ibDownload.setVisibility(View.VISIBLE);
            showStateSelected = false ;
            ibDownload.setEnabled(false);
        }

        switch (position){
            case 0:
                ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2017_cover));
                break;
            case 1:
                ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
            case 2:
                ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
            case 3:
                ivCoverPhoto.setImageDrawable(getResources().getDrawable(R.drawable.yb2016_cover));
                break;
        }
    }

    private void startDownloading(String year) {

        FileDownloader.setup(this.getActivity());
        downloadingState = true ;

        Log.i("TAG", "getExistingState: " + FileDownloader.getImpl().getStatus(Constants.BASE_URL + Constants.URL_BOOK + year + ".pdf",
                        Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + year + ".pdf"));

        notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(getActivity());
        notificationBuilder.setContentTitle("Yearbook Rewind " + year)
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.yearbook_logo);


        bookDownloadingListener = new BookDownloadingListener(Integer.parseInt(year), notificationManager, notificationBuilder) ;
        FileDownloader.getImpl().create(Constants.BASE_URL + Constants.URL_BOOK + year + ".pdf")
                .setPath(Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + year + ".pdf")
                .setListener(bookDownloadingListener)
                .asInQueueTask()
                .enqueue() ;
        FileDownloader.getImpl().start(bookDownloadingListener, true) ;
    }

    private void pauseDownloading() {
        FileDownloader.setup(this.getActivity());
        FileDownloader.getImpl().pause(bookDownloadingListener) ;
        downloadingState = false ;
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT){
            Log.i("TAG", "onRequestPermissionsResult: " + grantResults[0]);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT){
            Log.i("TAG", "onActivityResult: ");
        }
    }









    private Animation slideLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.45f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        inFromLeft.setFillEnabled(true);
        inFromLeft.setFillAfter(true);
        return inFromLeft;
    }

    private Animation slideRightAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -0.45f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        inFromLeft.setFillEnabled(true);
        inFromLeft.setFillAfter(true);
        return inFromLeft;
    }


    void setCurrentYear(int state){
        switch (state){
            case 0:
                currentYear = "2017" ;
                tvDownloadSize.setText("65.8 MB");
                break;
            case 1:
                currentYear = "2016" ;
                tvDownloadSize.setText("42.9 MB");
                break;
            case 2:
                currentYear = "2015" ;
                tvDownloadSize.setText("42.9 MB");
                break;
            case 3:
                currentYear = "2014" ;
                tvDownloadSize.setText("42.9 MB");
                break;
        }
    }

}
