package in.vit.yearbook.Model.UIModels;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;

import org.greenrobot.eventbus.EventBus;


public class BookDownloadingListener extends FileDownloadLargeFileListener {

    private static final String TAG = "TAG";
    private int yearDownloading ;
    private ProgressUpdateEvent progressUpdateEvent ;

    private NotificationManager notificationManager ;
    private android.support.v7.app.NotificationCompat.Builder notificationBuilder ;

    public BookDownloadingListener(int yearDownloading, NotificationManager notificationManager,
                                   android.support.v7.app.NotificationCompat.Builder notificationBuilder){
        this.yearDownloading = yearDownloading ;
        progressUpdateEvent = new ProgressUpdateEvent(yearDownloading, 0) ;
        this.notificationManager = notificationManager ;
        this.notificationBuilder = notificationBuilder ;

    }

    @Override
    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.d(TAG, "pending() called with: task = [" + task + "], soFarBytes = [" + soFarBytes + "], totalBytes = [" + totalBytes + "]" );
        progressUpdateEvent.setProgress(0);
        progressUpdateEvent.setStatusString("Pending");
        notificationBuilder.setProgress(100, progressUpdateEvent.getProgress(), false).setContentText(progressUpdateEvent.getStatusString()) ;
        notificationManager.notify(progressUpdateEvent.getYear(), notificationBuilder.build());
        EventBus.getDefault().post(progressUpdateEvent);
    }

    @Override
    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.d(TAG, "progress() called with: task = [" + task + "], soFarBytes = [" + soFarBytes + "], totalBytes = [" + totalBytes + "]  " + (int)(((float)soFarBytes/totalBytes)*100));
        progressUpdateEvent.setProgress((int)(((float)soFarBytes/totalBytes)*100));
        progressUpdateEvent.setStatusString("Downloading");
        notificationBuilder.setProgress(100, progressUpdateEvent.getProgress(), false).setContentText(progressUpdateEvent.getStatusString()) ;
        notificationManager.notify(progressUpdateEvent.getYear(), notificationBuilder.build());
        EventBus.getDefault().post(progressUpdateEvent);
    }

    @Override
    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.d(TAG, "paused() called with: task = [" + task + "], soFarBytes = [" + soFarBytes + "], totalBytes = [" + totalBytes + "]");
        progressUpdateEvent.setProgress((int)(((float)soFarBytes/totalBytes)*100));
        progressUpdateEvent.setStatusString("Paused");
        notificationBuilder.setProgress(100, progressUpdateEvent.getProgress(), false).setContentText(progressUpdateEvent.getStatusString()) ;
        notificationManager.notify(progressUpdateEvent.getYear(), notificationBuilder.build());
        EventBus.getDefault().post(progressUpdateEvent);
    }

    @Override
    protected void completed(BaseDownloadTask task) {
        Log.d(TAG, "completed() called with: task = [" + task + "]");
        progressUpdateEvent.setStatusString("Completed");
        notificationBuilder.setProgress(100, progressUpdateEvent.getProgress(), false).setContentText(progressUpdateEvent.getStatusString()) ;
        notificationManager.notify(progressUpdateEvent.getYear(), notificationBuilder.build());
        EventBus.getDefault().post(progressUpdateEvent);
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        Log.d(TAG, "error() called with: task = [" + task + "], e = [" + e + "]");
        progressUpdateEvent.setStatusString("Error");
        notificationBuilder.setProgress(100, progressUpdateEvent.getProgress(), false).setContentText(progressUpdateEvent.getStatusString()) ;
        notificationManager.notify(progressUpdateEvent.getYear(), notificationBuilder.build());
        EventBus.getDefault().post(progressUpdateEvent);
    }

    @Override
    protected void warn(BaseDownloadTask task) {
        Log.d(TAG, "warn() called with: task = [" + task + "]");
    }
}
