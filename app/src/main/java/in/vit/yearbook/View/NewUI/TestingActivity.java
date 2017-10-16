package in.vit.yearbook.View.NewUI;


import android.os.Bundle;
import android.support.annotation.Nullable;


import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;

public class TestingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.testing_activity_test);
        ButterKnife.bind(this) ;

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
