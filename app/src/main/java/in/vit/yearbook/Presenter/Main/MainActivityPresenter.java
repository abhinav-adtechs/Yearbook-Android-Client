package in.vit.yearbook.Presenter.Main;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.liulishuo.filedownloader.FileDownloader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import in.vit.yearbook.Model.UIModels.BookDownloadingListener;
import in.vit.yearbook.Model.UIModels.ProgressUpdateEvent;
import in.vit.yearbook.Model.Utils.Constants;

public class MainActivityPresenter {

    private MainActivityInterface mainActivityInterface ;

    private NotificationManager notificationManager ;
    private NotificationCompat.Builder notificationBuilder ;

    @Inject
    public MainActivityPresenter() {
    }

    public void register(MainActivityInterface mainActivityInterface){
        this.mainActivityInterface = mainActivityInterface ;
        EventBus.getDefault().register(this);
    }

    public void startDownloadSetup(int year, NotificationManager notificationManager, NotificationCompat.Builder notificationBuilder){
        this.notificationBuilder = notificationBuilder ;
        this.notificationManager = notificationManager ;

        String downloadURL = Constants.BASE_URL + Constants.URL_BOOK + year + ".pdf" ;

        Log.i("TAG", "startDownloadSetup: ");


        FileDownloader.getImpl().create(downloadURL)
                .setPath(Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/" + year + ".pdf")
                .setListener(new BookDownloadingListener(year, notificationManager, notificationBuilder))
                .start();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDownloadStatus(ProgressUpdateEvent progressUpdateEvent){
        mainActivityInterface.updateDownloadStatus(progressUpdateEvent);
    }

    public void unRegister(){
        EventBus.getDefault().unregister(this);
    }
}

