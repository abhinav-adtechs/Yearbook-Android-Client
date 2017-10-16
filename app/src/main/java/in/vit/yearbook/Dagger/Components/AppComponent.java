package in.vit.yearbook.Dagger.Components;

import javax.inject.Singleton;

import dagger.Component;
import in.vit.yearbook.Dagger.Modules.SharedPrefModule;
import in.vit.yearbook.View.NewUI.MainActivity;

@Component(modules = SharedPrefModule.class)
@Singleton
public interface AppComponent {

    void inject(MainActivity mainActivity) ;
}
