package in.vit.yearbook.Model.UIModels;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;


public class BookDownloadingListener extends FileDownloadLargeFileListener {

    private NotificationManager notificationManager ;
    private NotificationCompat.Builder notificationBuilder ;

    public BookDownloadingListener(NotificationManager notificationManager, NotificationCompat.Builder notificationBuilder) {
        this.notificationManager = notificationManager ;
        this.notificationBuilder = notificationBuilder ;
    }

    @Override
    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.i("TAG", "pending: ");
        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Download Pending") ;
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.i("TAG", "progress: " + task.getId() + " : " + soFarBytes + " : " + totalBytes );
        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Downloading") ;
        notificationManager.notify(1, notificationBuilder.build());

    }

    @Override
    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.i("TAG", "paused: ");
        notificationBuilder.setProgress((int)totalBytes, (int)soFarBytes, false).setContentText("Paused") ;
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void completed(BaseDownloadTask task) {
        Log.i("TAG", "completed: ");
        notificationBuilder.setProgress(0, 0, false).setContentText("Download Complete") ;
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        Log.i("TAG", "error: " + e.getLocalizedMessage());
        notificationBuilder.setProgress(0, 0, false).setContentText("Error Downloading") ;
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void warn(BaseDownloadTask task) {
        Log.d("TAG", "warn: ");
    }
}
