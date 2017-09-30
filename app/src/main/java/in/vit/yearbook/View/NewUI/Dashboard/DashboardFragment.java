package in.vit.yearbook.View.NewUI.Dashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;


public class DashboardFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;

    @BindView(R.id.new_fragment_dashboard_top_animation)
    RecyclerView rvDashboardTopBook;

    @BindView(R.id.new_fragment_dashboard_btn_download)
    ImageButton ibDownload ;

    @BindView(R.id.new_fragment_dashboard_tv_details)
    TextView tvDownloadDetails ;

    @BindView(R.id.new_fragment_dashboard_tv_size)
    TextView tvDownloadSize ;

    private boolean showStateSelected = false ;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;


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
                    startDownloading() ;
                }
                break;

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

    private void startDownloading() {



    }

}
