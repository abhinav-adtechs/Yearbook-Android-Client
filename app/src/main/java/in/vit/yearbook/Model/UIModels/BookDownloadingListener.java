package in.vit.yearbook.Model.UIModels;

import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadLargeFileListener;


public class BookDownloadingListener extends FileDownloadLargeFileListener {

    @Override
    protected void pending(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.i("TAG", "pending: ");
    }

    @Override
    protected void progress(BaseDownloadTask task, long soFarBytes, long totalBytes) {
        Log.i("TAG", "progress: " + task.getId() + " : " + (soFarBytes/totalBytes) + "%" );
    }

    @Override
    protected void paused(BaseDownloadTask task, long soFarBytes, long totalBytes) {

    }

    @Override
    protected void completed(BaseDownloadTask task) {
        Log.i("TAG", "completed: ");
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        Log.i("TAG", "error: " + e.getLocalizedMessage());
    }

    @Override
    protected void warn(BaseDownloadTask task) {
        Log.d("TAG", "warn: ");
    }
}
