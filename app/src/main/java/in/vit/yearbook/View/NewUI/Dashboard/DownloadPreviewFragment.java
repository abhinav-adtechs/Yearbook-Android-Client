package in.vit.yearbook.View.NewUI.Dashboard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;

public class DownloadPreviewFragment extends BaseFragment{

    @BindView(R.id.new_fragment_downloading_prev_tv_size)
    TextView tvSize ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_downloading_preview, container, false) ;
        ButterKnife.bind(this, view) ;

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

    }
}
