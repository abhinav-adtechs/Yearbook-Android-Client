package in.vit.yearbook.View.Introduction;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.ChangeBounds;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.BaseActivity;
import in.vit.yearbook.View.Dashboard.MainActivity;

public class SplashActivity extends BaseActivity{

    /*@BindView(R.id.activity_splash_view_rv_grid)
    RecyclerView rvGridImages ;

    private SplashGridAdapter splashGridAdapter ;*/

    @BindView(R.id.activity_splash_view_tv_yearbook)
    TextView tvYearbook ;

    @BindView(R.id.activity_splash_view_iv_yb)
    ImageView ivYearbook ;

    @BindView(R.id.activity_splash_view_tv_rewind)
    TextView tvRewind ;

    private SplashActivity splashActivity ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_view);

        ButterKnife.bind(this) ;

        init() ;
        //setInit() ;

        splashActivity = this ;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class) ;
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(splashActivity, ivYearbook, "yb_logo");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setSharedElementEnterTransition(new ChangeBounds().setDuration(4000));
                }
                startActivity(intent, options.toBundle());
                finish();
            }
        }, 3300) ;
    }

    private void init() {

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(2800);

        tvYearbook.setAnimation(fadeIn);
        ivYearbook.setAnimation(fadeIn);
        tvRewind.setAnimation(fadeIn);
    }

    /*private void setInit() {
        splashGridAdapter = new SplashGridAdapter(this) ;
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, 1) ;
        rvGridImages.setLayoutManager(layoutManager);
        rvGridImages.setAdapter(splashGridAdapter);


    }*/
}
