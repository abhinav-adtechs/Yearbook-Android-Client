package in.vit.yearbook.View.NewUI;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @BindView(R.id.new_activity_main_toolbar_main)
    Toolbar toolbarMain ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);

        ButterKnife.bind(this) ;
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
