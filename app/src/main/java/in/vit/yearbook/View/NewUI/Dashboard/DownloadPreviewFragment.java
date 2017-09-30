package in.vit.yearbook.View.NewUI.Dashboard;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;
import in.vit.yearbook.View.NewUI.MainActivity;

public class DownloadPreviewFragment extends BaseFragment implements View.OnClickListener{

    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 101;
    @BindView(R.id.new_fragment_downloading_prev_tv_size)
    TextView tvSize ;

    @BindView(R.id.new_fragment_downloading_prev_btn_downloading)
    ImageView ivDownload ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_downloading_preview, container, false) ;
        ButterKnife.bind(this, view) ;
        Log.i("TAG", "onCreateView: ");
        ivDownload.setOnClickListener(this);
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    public void increaseSize(){
        tvSize.setVisibility(View.VISIBLE);
    }

    public void decreaseSize(){
        tvSize.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.new_fragment_downloading_prev_btn_downloading){

            Log.i("TAG", "Downloading begin ");

            if (ActivityCompat.checkSelfPermission(
                    getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
            }



        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT){
            Log.i("TAG", "onRequestPermissionsResult: ");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EXTERNAL_STORAGE_PERMISSION_CONSTANT){
            Log.i("TAG", "onActivityResult: ");
        }
    }
}
