package in.vit.yearbook.Presenter.Main;


import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import in.vit.yearbook.Model.UIModels.ProgressUpdateEvent;

public class MainActivityPresenter {

    private MainActivityInterface mainActivityInterface ;

    @Inject
    public MainActivityPresenter() {
    }

    public void register(MainActivityInterface mainActivityInterface){
        this.mainActivityInterface = mainActivityInterface ;
        EventBus.getDefault().register(this);
    }

    public void startDownloadSetup(int year){
        Log.i("TAG", "startDownloadSetup: ");

    }

    @Subscribe
    public void getDownloadStatus(ProgressUpdateEvent progressUpdateEvent){
        mainActivityInterface.updateDownloadStatus(progressUpdateEvent);
    }

    public void unRegister(){
        EventBus.getDefault().unregister(this);
    }
}

