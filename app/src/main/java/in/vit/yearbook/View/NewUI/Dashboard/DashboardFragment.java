package in.vit.yearbook.View.NewUI.Dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.vit.yearbook.R;
import in.vit.yearbook.View.NewUI.BaseFragment;


public class DashboardFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_fragment_dashboard, container, false) ;
        addFragmentTransaction(new DownloadPreviewFragment());
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void addFragmentTransaction(android.support.v4.app.Fragment currentFragment) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.new_fragment_dashboard_frame_download, currentFragment)
                .commit();

    }
}
