package pl.animekkk.deepantilogout.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.animekkk.deepantilogout.AntiLogoutPlugin;

public class PlayerQuitListener implements Listener {

    private final AntiLogoutPlugin plugin;

    public PlayerQuitListener(AntiLogoutPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(plugin.getAntiLogoutManager().isFighting(player.getUniqueId())) {
            player.setHealth(0);
            plugin.getAntiLogoutManager().removeFight(player.getUniqueId());
        }
    }

}
