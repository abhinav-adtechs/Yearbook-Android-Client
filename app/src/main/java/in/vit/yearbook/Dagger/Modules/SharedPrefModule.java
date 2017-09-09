package in.vit.yearbook.Dagger.Modules;


import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import in.vit.yearbook.Model.Utils.Constants;

@Module
public class SharedPrefModule {

    Context context;
    SharedPreferences sharedPrefs;

    public SharedPrefModule(Context context){
        this.context=context;
        sharedPrefs = context.getSharedPreferences(Constants.sharedPreferenceUser,Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPrefs() {
        return sharedPrefs;
    }

    @Provides
    @Singleton
    SharedPreferences.Editor providesSharedPrefsEditor() {
        return context.getSharedPreferences(Constants.sharedPreferenceUser,Context.MODE_PRIVATE).edit();
    }
}
