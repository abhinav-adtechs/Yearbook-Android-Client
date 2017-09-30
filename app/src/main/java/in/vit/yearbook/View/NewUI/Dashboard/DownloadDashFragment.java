package in.vit.yearbook.View.NewUI.Dashboard;


import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.liulishuo.filedownloader.FileDownloader;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.Model.UIModels.BookDownloadingListener;
import in.vit.yearbook.Model.Utils.Constants;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;

public class DownloadDashFragment extends BaseFragment implements View.OnClickListener{

    FragmentActivity parentActivity ;


    @BindView(R.id.new_fragment_downloading_dash_btn_downloading)
    ImageButton ibDownload ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_downloading_dash, container, false) ;

        ButterKnife.bind(this, view) ;

        ibDownload.setOnClickListener(this);
        parentActivity = this.getActivity() ;
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getExistingState() ;

    }

    private void getExistingState() {
        FileDownloader.setup(parentActivity);

        Log.i("TAG", "getExistingState: " +
                FileDownloader.getImpl().getStatus(Constants.BASE_URL + Constants.URL_BOOK_2017,
                        Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/2017.pdf"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_fragment_downloading_dash_btn_downloading :

                BookDownloadingListener bookDownloadingListener = new BookDownloadingListener() ;
                FileDownloader.getImpl().create(Constants.BASE_URL + Constants.URL_BOOK_2017)
                        .setPath(Environment.getExternalStorageDirectory().toString() + "/YearbookVIT/2017.pdf")
                        .setListener(bookDownloadingListener)
                        .asInQueueTask()
                        .enqueue() ;

                FileDownloader.getImpl().start(bookDownloadingListener, true) ;
                break;
        }
    }



}
