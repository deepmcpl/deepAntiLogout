package pl.animekkk.deepantilogout.antilogout;

import pl.mikigal.config.Config;
import pl.mikigal.config.annotation.ConfigName;

import java.util.Collections;
import java.util.List;

@ConfigName("config.yml")
public interface AntiLogoutConfig extends Config {

    default int getAntiLogoutSeconds() {
        return 20;
    }

    default List<String> getEnabledCommands() {
        return Collections.singletonList("tell");
    }

}
