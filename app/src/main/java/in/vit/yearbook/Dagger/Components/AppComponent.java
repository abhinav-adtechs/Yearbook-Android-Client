package in.vit.yearbook.Dagger.Components;

import javax.inject.Singleton;

import dagger.Component;
import in.vit.yearbook.Dagger.Modules.SharedPrefModule;

@Component(modules = SharedPrefModule.class)
@Singleton
public interface AppComponent {


}
