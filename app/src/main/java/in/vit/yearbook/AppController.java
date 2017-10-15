package in.vit.yearbook;

import android.app.Application;

import in.vit.yearbook.Dagger.Components.AppComponent;
import in.vit.yearbook.Dagger.Components.DaggerAppComponent;
import in.vit.yearbook.Dagger.Modules.SharedPrefModule;
import in.vit.yearbook.Model.Utils.FontsOverride;


public class AppController extends Application {

    private AppComponent appComponent ;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .build();

        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/VarelaRound-Regular.ttf");
    }

    public AppComponent get() {
        return appComponent;
    }
}
