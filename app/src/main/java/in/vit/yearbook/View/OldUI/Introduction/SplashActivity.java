package in.vit.yearbook.View.OldUI.Introduction;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.ChangeBounds;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.OldUI.BaseActivity;
import in.vit.yearbook.View.OldUI.Dashboard.MainActivity;

public class SplashActivity extends BaseActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener{

    private SplashActivity splashActivity ;

    @BindView(R.id.activity_splash_view_videoview)
    SurfaceView videoView ;

    SurfaceHolder surfaceHolder ;
    MediaPlayer mediaPlayer ;
    String filePath ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_view);
        ButterKnife.bind(this) ;
        splashActivity = this ;

        filePath = "android.resource://" + getPackageName() + "/" + R.raw.yb_cover ;

        surfaceHolder = videoView.getHolder() ;
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mediaPlayer = new MediaPlayer() ;
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setScreenOnWhilePlaying(true);


        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 8000) ;*/
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
        Intent intent = new Intent(splashActivity, in.vit.yearbook.View.NewUI.MainActivity.class) ;
        startActivity(intent);
        finish();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        int videoWidth = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        float videoProportion = (float) videoWidth / (float) videoHeight;
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();
        float screenProportion = (float) screenWidth / (float) screenHeight;
        Log.i("TAG", "onPrepared: " + screenHeight + " " + screenWidth + " " + screenProportion);
        android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();

        if (videoProportion > screenProportion) {
            lp.width = screenWidth;
            lp.height = (int) ((float) screenWidth / videoProportion);
        } else {
            lp.width = (int) (videoProportion * (float) screenHeight);
            lp.height = screenHeight;
        }
        Log.i("TAG", "onPrepared: " + lp.height + " " + lp.width);

        videoView.setLayoutParams(lp);

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer.setDisplay(surfaceHolder);
        playVideo();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void playVideo() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    mediaPlayer.setDataSource(SplashActivity.this, Uri.parse(filePath));
                    mediaPlayer.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                    Intent intent = new Intent(splashActivity, in.vit.yearbook.View.NewUI.MainActivity.class) ;
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }


}
