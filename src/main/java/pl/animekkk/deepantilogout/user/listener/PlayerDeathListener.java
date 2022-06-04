package pl.animekkk.deepantilogout.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import pl.animekkk.deepantilogout.AntiLogoutPlugin;
import pl.animekkk.deepantilogout.user.util.BarUtil;

public class PlayerDeathListener implements Listener{

    private final AntiLogoutPlugin plugin;

    public PlayerDeathListener(AntiLogoutPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        this.plugin.getAntiLogoutManager().removeFight(player.getUniqueId());
        BarUtil.removeBossBar(player);
    }

}
