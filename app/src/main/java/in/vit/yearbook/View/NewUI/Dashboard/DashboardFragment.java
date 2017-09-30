package in.vit.yearbook.View.NewUI.Dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;


public class DashboardFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.new_fragment_dashboard_frame_download)
    FrameLayout frameDownload ;

    @BindView(R.id.new_fragment_dashboard_iv_cover)
    ImageView ivCoverPhoto ;

    @BindView(R.id.new_fragment_dashboard_top_animation)
    LottieAnimationView lottieAnimationView ;

    DownloadPreviewFragment downloadPreviewFragment ;
    //DownloadDashFragment downloadDashFragment ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        ButterKnife.bind(this, view);

        frameDownload.setOnClickListener(this);
        ivCoverPhoto.setOnClickListener(this);
        lottieAnimationView.setImageAssetsFolder("images");
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        downloadPreviewFragment = new DownloadPreviewFragment() ;
        addFragmentTransaction(downloadPreviewFragment);
        //downloadDashFragment = new DownloadDashFragment() ;

    }


    private void addFragmentTransaction(android.support.v4.app.Fragment currentFragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.new_fragment_dashboard_frame_download, currentFragment)
                .commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_fragment_dashboard_frame_download:

                ScaleAnimation anim = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setFillEnabled(true);
                anim.setFillAfter(true);
                anim.setDuration(400);
                frameDownload.startAnimation(anim);
                frameDownload.setClickable(false);

                ScaleAnimation anim2 = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f, Animation.RELATIVE_TO_SELF,0.1f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim2.setFillEnabled(true);
                anim2.setFillAfter(true);
                anim2.setDuration(400);
                ivCoverPhoto.startAnimation(anim2);
                ivCoverPhoto.setClickable(true);

                downloadPreviewFragment.increaseSize();

                //makeFragmentTransaction(downloadDashFragment);
                frameDownload.requestLayout();

                break;

            case R.id.new_fragment_dashboard_iv_cover:

                ScaleAnimation anim3 = new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, Animation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim3.setFillEnabled(true);
                anim3.setFillAfter(true);
                anim3.setDuration(400);
                frameDownload.startAnimation(anim3);
                frameDownload.setClickable(true);

                ScaleAnimation anim4 = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_SELF,0.1f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim4.setFillEnabled(true);
                anim4.setFillAfter(true);
                anim4.setDuration(400);
                ivCoverPhoto.startAnimation(anim4);
                ivCoverPhoto.setClickable(false);

                downloadPreviewFragment.decreaseSize();

                //makeFragmentTransaction(downloadPreviewFragment);
                frameDownload.requestLayout();
                break;

        }
    }

    private void makeFragmentTransaction(android.support.v4.app.Fragment nextFragment) {

        if (nextFragment != null){
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in_slow, R.anim.fade_out_slow)
                    .replace(R.id.new_fragment_dashboard_frame_download, nextFragment)
                    .commit();

        }

    }
}
