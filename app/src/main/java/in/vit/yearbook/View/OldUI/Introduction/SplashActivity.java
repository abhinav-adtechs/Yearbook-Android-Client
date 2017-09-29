package in.vit.yearbook.View.OldUI.Introduction;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.ChangeBounds;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.OldUI.BaseActivity;
import in.vit.yearbook.View.OldUI.Dashboard.MainActivity;

public class SplashActivity extends BaseActivity{

    private SplashActivity splashActivity ;

    LottieAnimationView lottieAnimationView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_view);

        ButterKnife.bind(this) ;

        splashActivity = this ;

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.activity_splash_view_lottie);
        lottieAnimationView.playAnimation();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, in.vit.yearbook.View.NewUI.MainActivity.class) ;
                startActivity(intent);
                finish();
            }
        }, 1000) ;
    }

}
