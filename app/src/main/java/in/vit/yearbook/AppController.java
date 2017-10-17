package in.vit.yearbook;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import in.vit.yearbook.Dagger.Components.AppComponent;
import in.vit.yearbook.Dagger.Components.DaggerAppComponent;
import in.vit.yearbook.Dagger.Modules.SharedPrefModule;
import in.vit.yearbook.Model.Utils.FontsOverride;
import io.fabric.sdk.android.Fabric;


public class AppController extends Application {

    private AppComponent appComponent ;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        this.appComponent = DaggerAppComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .build();

        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/VarelaRound-Regular.ttf");
    }

    public AppComponent get() {
        return appComponent;
    }
}
