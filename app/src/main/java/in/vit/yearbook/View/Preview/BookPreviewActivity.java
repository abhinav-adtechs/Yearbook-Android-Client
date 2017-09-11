package in.vit.yearbook.View.Preview;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.barteksc.pdfviewer.PDFView;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.BaseActivity;

public class BookPreviewActivity extends BaseActivity{

    @BindView(R.id.activity_book_preview_pdfviewer)
    PDFView pdfView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_preview);
        ButterKnife.bind(this) ;

        setInit() ;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        pdfView.fitToWidth();
    }

    private void setInit() {
        pdfView.fromAsset("testingfile.pdf")
                .enableAntialiasing(true)
                .enableSwipe(true)
                .load();

        pdfView.documentFitsView() ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
