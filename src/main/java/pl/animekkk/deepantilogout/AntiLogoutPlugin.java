package pl.animekkk.deepantilogout;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.animekkk.deepantilogout.antilogout.AntiLogoutConfig;
import pl.animekkk.deepantilogout.antilogout.AntiLogoutManager;
import pl.animekkk.deepantilogout.antilogout.task.AntiLogoutTask;
import pl.animekkk.deepantilogout.user.listener.EntityDamageByEntityListener;
import pl.animekkk.deepantilogout.user.listener.InventoryOpenListener;
import pl.animekkk.deepantilogout.user.listener.PlayerDeathListener;
import pl.animekkk.deepantilogout.user.listener.PlayerQuitListener;
import pl.mikigal.config.ConfigAPI;

public final class AntiLogoutPlugin extends JavaPlugin {

    @Getter
    private AntiLogoutConfig antiLogoutConfig;
    @Getter
    private AntiLogoutManager antiLogoutManager;

    @Override
    public void onEnable() {
        antiLogoutConfig = ConfigAPI.init(AntiLogoutConfig.class, this);
        antiLogoutManager = new AntiLogoutManager();
        registerListeners();
        startTasks();
    }

    @Override
    public void onDisable() {
        antiLogoutManager.clearFights();
    }

    public void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new EntityDamageByEntityListener(this), this);
        pluginManager.registerEvents(new InventoryOpenListener(this), this);
        pluginManager.registerEvents(new PlayerDeathListener(this), this);
        pluginManager.registerEvents(new PlayerQuitListener(this), this);
    }

    public void startTasks() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AntiLogoutTask(this), 20L, 20L);
    }

}
