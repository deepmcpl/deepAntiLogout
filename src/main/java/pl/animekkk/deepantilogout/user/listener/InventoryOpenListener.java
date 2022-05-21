package pl.animekkk.deepantilogout.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import pl.animekkk.deepantilogout.AntiLogoutPlugin;
import pl.animekkk.deepantilogout.user.util.BarUtil;
import pl.animekkk.deepantilogout.user.util.ChatUtil;

public class InventoryOpenListener implements Listener {

    private final AntiLogoutPlugin plugin;

    public InventoryOpenListener(AntiLogoutPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onOpen(InventoryOpenEvent event) {
        if(!(event.getPlayer() instanceof Player)) return;
        Player player = (Player) event.getPlayer();
        if(this.plugin.getAntiLogoutManager().isFighting(player.getUniqueId()) && event.getInventory().getType() == InventoryType.ENDER_CHEST) {
            event.setCancelled(true);
            ChatUtil.sendMessage(player, "&7Nie możesz tego zrobić podczas walki.");
        }
    }

}
