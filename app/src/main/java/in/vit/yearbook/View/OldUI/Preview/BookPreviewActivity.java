package in.vit.yearbook.View.OldUI.Preview;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.vit.yearbook.R;
import in.vit.yearbook.View.OldUI.BaseActivity;

public class BookPreviewActivity extends BaseActivity{

    @BindView(R.id.activity_book_preview_pdfviewer)
    PDFView pdfView ;

    String fileName = "";
    private File bookToBeRead ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_preview);
        ButterKnife.bind(this) ;

        if (getIntent().hasExtra("fileName")){
            fileName = getIntent().getExtras().getString("fileName") ;
        }

        bookToBeRead = new File(fileName) ;
        if (bookToBeRead.exists()){
            setInit();
        }else {
            Toast.makeText(this, "File does not Exist! Sorry", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        pdfView.fitToWidth();
    }

    private void setInit() {
        if (bookToBeRead != null){
            pdfView.fromFile(bookToBeRead)
                    .enableAntialiasing(true)
                    .enableSwipe(true)
                    .scrollHandle(new DefaultScrollHandle(this, true))
                    .load();
            pdfView.fitToWidth();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
