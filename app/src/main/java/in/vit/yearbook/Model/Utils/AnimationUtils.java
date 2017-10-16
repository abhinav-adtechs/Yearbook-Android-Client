package in.vit.yearbook.Model.Utils;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


public final class AnimationUtils {

    public Animation slideLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -0.45f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        inFromLeft.setDuration(300);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        inFromLeft.setFillEnabled(true);
        inFromLeft.setFillAfter(true);
        return inFromLeft;
    }

    public Animation slideRightAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, -0.45f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        inFromLeft.setDuration(300);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        inFromLeft.setFillEnabled(true);
        inFromLeft.setFillAfter(true);
        return inFromLeft;
    }

    public AlphaAnimation fadeInAnimation(){
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f) ;
        fadeIn.setDuration(700);
        fadeIn.setFillEnabled(true);
        fadeIn.setFillAfter(true);
        return fadeIn ;
    }

    public AlphaAnimation fadeOutAnimation(){
        AlphaAnimation fadeOut= new AlphaAnimation(1.0f, 0.0f) ;
        fadeOut.setDuration(400);
        fadeOut.setFillEnabled(true);
        fadeOut.setFillAfter(true);
        return fadeOut ;
    }


}
