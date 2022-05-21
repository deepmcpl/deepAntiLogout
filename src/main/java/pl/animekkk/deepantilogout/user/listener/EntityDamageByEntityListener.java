package pl.animekkk.deepantilogout.user.listener;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.animekkk.deepantilogout.AntiLogoutPlugin;
import pl.animekkk.deepantilogout.antilogout.Fight;
import pl.animekkk.deepantilogout.user.util.BarUtil;

public class EntityDamageByEntityListener implements Listener {

    private final AntiLogoutPlugin plugin;

    public EntityDamageByEntityListener(AntiLogoutPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();

        Player damager = null;
        if(event.getDamager() instanceof Player) {
            damager = (Player) event.getDamager();
        }
        else if(event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if(projectile.getShooter() instanceof Player) {
                damager = (Player) projectile.getShooter();
            }
        }

        if(damager == null) return;
        if(damager == player) return;
        handleFight(player, damager, event.getDamage());
        handleFight(damager, player, 0);
    }

    public void handleFight(Player player, Player damager, double damage) {
        Fight playerFight = this.plugin.getAntiLogoutManager().getFight(player.getUniqueId());
        if(playerFight == null) {
            playerFight = createFight(player);
        }
        playerFight.resetTimeLeft();
        playerFight.setLastDamager(damager);
        if(damage > 0) playerFight.addDamage(damager.getUniqueId(), damage);
        BarUtil.sendBarMessage(player, "&cANTYLOGOUT &7[ &e" + playerFight.getTimeLeft() + " &7]", 1.0);
    }

    public Fight createFight(Player victim) {
        Fight fight = new Fight(victim, this.plugin.getAntiLogoutConfig().getAntiLogoutSeconds());
        this.plugin.getAntiLogoutManager().addFight(fight);
        return fight;
    }

}
