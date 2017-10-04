package in.vit.yearbook.View.NewUI.Dashboard;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.CircularProgressButton;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.BookDownloadingListener;
import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.OldUI.Dashboard.CenterZoomLayoutManager;
import in.vit.yearbook.View.OldUI.Dashboard.MainActivity;


public class DashboardFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;

    @BindView(R.id.new_fragment_dashboard_rv_year)
    RecyclerView rvDashboardTopBook;

    @BindView(R.id.new_fragment_dashboard_btn_download)
    CircularProgressButton ibDownload ;

    @BindView(R.id.new_fragment_dashboard_tv_details)
    TextView tvDownloadDetails ;

    @BindView(R.id.new_fragment_dashboard_btn_read)
    Button btnRead ;


    private boolean downloadingState[] = {false, false, false, false} ;
    private boolean showStateSelected = false ;

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;
    private BookDownloadingListener bookDownloadingListener ;

    private NotificationManager notificationManager ;
    private NotificationCompat.Builder notificationBuilder ;

    private DashYearAdapter dashYearAdapter ;

    private String currentYear = "2017" ;
    private int currentYearPosition = 0 ;

    private boolean viewVisible = false ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        ButterKnife.bind(this, view);

        ivCoverPhoto.setOnClickListener(this);
        ibDownload.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnRead.setEnabled(false);

        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRv() ;

    }

    @Override
    public void onResume() {
        viewVisible = true ;
        super.onResume();
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
                    ibDownload.setAnimation(fadeIn);
                    ibDownload.setVisibility(View.VISIBLE);
                    handleReadingBegin() ;
                    ibDownload.setEnabled(true);
                    showStateSelected = true ;
                }else {
                    ivCoverPhoto.startAnimation(slideRightAnimation());
                    AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
                    fadeOut.setDuration(800);
                    fadeOut.setFillEnabled(true);
                    fadeOut.setFillAfter(true);
                    tvDownloadDetails.setAnimation(fadeOut);
                    ibDownload.setAnimation(fadeOut);
                    ibDownload.setVisibility(View.VISIBLE);
                    handleReadingBegin();
                    ibDownload.setEnabled(false);
                    showStateSelected = false ;
                }

                break;

            case R.id.new_fragment_dashboard_btn_download:
                Log.i("TAG", "onClick: ");
                if (ActivityCompat.checkSelfPermission(
                        getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
                }else {
                    if (!downloadingState[currentYearPosition]){

                        startDownloading(currentYear) ;
                    } else
                        pauseDownloading() ;
                }
                break;

            case R.id.new_fragment_dashboard_btn_read:
                break;
        }
    }

    private void handleReadingBegin() {
        String fileName = Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + currentYear + ".pdf";
        File file = new File(fileName) ;
        if (file.exists()){
            Log.i("TAG", "File exists ");
            if (!showStateSelected){
                AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f) ;
                fadeIn.setDuration(1200);
                fadeIn.setFillEnabled(true);
                fadeIn.setFillAfter(true);
                btnRead.setEnabled(true);
                btnRead.setVisibility(View.VISIBLE);
            }else if (btnRead.getVisibility() == View.VISIBLE){
                AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
                fadeOut.setDuration(800);
                fadeOut.setFillEnabled(true);
                fadeOut.setFillAfter(true);
                btnRead.setAnimation(fadeOut);
                btnRead.setEnabled(false);
                btnRead.setVisibility(View.INVISIBLE);
            }
        }


    }


    private void setBooks(int position) {

        if (showStateSelected){
            ibDownload.setProgress(0);
            ivCoverPhoto.startAnimation(slideRightAnimation());
            AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
            fadeOut.setDuration(800);
            fadeOut.setFillEnabled(true);
            fadeOut.setFillAfter(true);
            tvDownloadDetails.setAnimation(fadeOut);
            ibDownload.setAnimation(fadeOut);
            ibDownload.setVisibility(View.VISIBLE);
            showStateSelected = false ;
            ibDownload.setEnabled(false);
        }

        switch (position%4){
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

    private void startDownloading(final String year) {

        ibDownload.setVisibility(View.VISIBLE);
        String downloadURL = Constants.BASE_URL + Constants.URL_BOOK + year + ".pdf" ;
        Log.i("TAG", "startDownloading: " + downloadURL);
        FileDownloader.setup(this.getActivity());
        downloadingState[currentYearPosition] = true ;

        Log.i("TAG", "getExistingState: " + FileDownloader.getImpl().getStatus(Constants.BASE_URL + Constants.URL_BOOK + year + ".pdf",
                        Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + year + ".pdf"));

        notificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(getActivity());
        notificationBuilder.setContentTitle("Yearbook Rewind " + year)
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.yearbook_logo);

        notificationBuilder.setContentIntent(PendingIntent.getActivity(this.getActivity(), 0,
                new Intent(this.getActivity(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT)) ;

        bookDownloadingListener = new BookDownloadingListener(Integer.parseInt(year), notificationManager, notificationBuilder) ;
        FileDownloader.getImpl().create(downloadURL)
                .setPath(Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + year + ".pdf")
                .setListener(new FileDownloadLargeFileListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Log.i("TAG", "pending: ");
                        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Download Pending") ;
                        notificationManager.notify(task.getId(), notificationBuilder.build());
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Double percent = Double.parseDouble(soFarBytes + "")/Double.parseDouble(totalBytes + "") ;
                        ibDownload.setIndeterminateProgressMode(false);
                        ibDownload.setProgress(percent.intValue());
                        Log.i("TAG", "progress: " + task.getId() + " : " + soFarBytes + " : " + totalBytes );
                        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Downloading") ;
                        notificationManager.notify(task.getId(), notificationBuilder.build());
                        downloadingState[currentYearPosition] = true ;
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
                        Log.i("TAG", "paused: ");
                        ibDownload.setIndeterminateProgressMode(true);
                        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Paused") ;
                        notificationManager.notify(task.getId(), notificationBuilder.build());
                        downloadingState[currentYearPosition] = false ;
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Log.i("TAG", "completed: ");
                        ibDownload.setProgress(100);
                        notificationBuilder.setProgress(0, 0, false).setContentText("Download Complete") ;
                        notificationManager.notify(task.getId(), notificationBuilder.build());
                        downloadingState[currentYearPosition] = false ;

                        if (viewVisible){
                            handleReadingBegin();
                        }


                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        Log.i("TAG", "error: " + e.getLocalizedMessage());
                        ibDownload.setProgress(-1);
                        notificationBuilder.setProgress(0, 0, false).setContentText("Error Downloading") ;
                        notificationManager.notify(task.getId(), notificationBuilder.build());
                        downloadingState[currentYearPosition] = false ;

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        Log.d("TAG", "warn: ");
                    }
                })
                .start() ;
        FileDownloader.getImpl().start(bookDownloadingListener, true) ;
    }

    private void pauseDownloading() {
        FileDownloader.setup(this.getActivity());
        FileDownloader.getImpl().pause(bookDownloadingListener) ;
        downloadingState[currentYearPosition] = false ;
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
        currentYearPosition = state ;
        switch (state){
            case 0:
                currentYear = "2017" ;
                ibDownload.setText("Download 65.8 MB");

                break;
            case 1:
                currentYear = "2016" ;
                ibDownload.setText("Download 42.9 MB");
                break;
            case 2:
                currentYear = "2015" ;
                ibDownload.setText("Download 42.9 MB");
                break;
            case 3:
                currentYear = "2014" ;
                ibDownload.setText("Download 42.9 MB");
                break;
        }
        ibDownload.setPadding(7,7,7,7);
    }

    @Override
    public void onPause() {
        viewVisible = false ;
        super.onPause();
    }
}
