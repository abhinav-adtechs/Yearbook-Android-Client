package in.vit.yearbook.View.NewUI;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;

public class TestingActivity extends BaseActivity {

    @BindView(R.id.testing_activity_test_lottie_animation)
    LottieAnimationView lottieAnimationView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testing_activity_test);
        ButterKnife.bind(this) ;

        lottieAnimationView.setImageAssetsFolder("images");
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);
        lottieAnimationView.setScale(1.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
