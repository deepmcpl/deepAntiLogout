package pl.animekkk.deepantilogout.antilogout.task;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import pl.animekkk.deepantilogout.AntiLogoutPlugin;
import pl.animekkk.deepantilogout.user.util.BarUtil;

public class AntiLogoutTask implements Runnable {

    private final AntiLogoutPlugin plugin;

    public AntiLogoutTask(AntiLogoutPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        this.plugin.getAntiLogoutManager().getFights().forEach(fight -> {
            double secondsLeft = fight.getTimeLeft();
            if(secondsLeft < 1) {
                this.plugin.getAntiLogoutManager().removeFight(fight.getVictim().getUniqueId());
                removeBossBar(BarUtil.sendBarMessage(fight.getVictim(), "&aANTYLOGOUT &7[ &eMOŻESZ SIĘ WYLOGOWAĆ &7]", 0));
                return;
            }
            BarUtil.sendBarMessage(fight.getVictim(), "&cANTYLOGOUT &7[ &e" + fight.getTimeLeft() + " &7]",
                    ((double) fight.getTimeLeft() / fight.getFightTime()));
            fight.subtractSecond();
        });
    }

    private void removeBossBar(BossBar bossBar) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(this.plugin, bossBar::removeAll, 20 * 5L);
    }

}
