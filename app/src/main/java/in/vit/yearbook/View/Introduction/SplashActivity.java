package in.vit.yearbook.View.Introduction;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Window;
import android.view.WindowManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.BaseActivity;
import in.vit.yearbook.View.Dashboard.DashboardActivity;

public class SplashActivity extends BaseActivity{

    @BindView(R.id.activity_splash_view_rv_grid)
    RecyclerView rvGridImages ;

    private SplashGridAdapter splashGridAdapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_view);

        ButterKnife.bind(this) ;


        setInit() ;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class) ;
                startActivity(intent);
                finish();
            }
        }, 4000) ;
    }

    private void setInit() {
        splashGridAdapter = new SplashGridAdapter(this) ;
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, 1) ;
        rvGridImages.setLayoutManager(layoutManager);
        rvGridImages.setAdapter(splashGridAdapter);


    }
}
