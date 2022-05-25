package pl.animekkk.deepantilogout.user.util;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class BarUtil {

    private static final HashMap<UUID, BossBar> bossBars = new HashMap<>();

    public static BossBar sendBarMessage(Player player, String message, double progress) {
        BossBar bossBar = bossBars.get(player.getUniqueId());
        if(bossBar != null) bossBar.removeAll();
        bossBar = Bukkit.createBossBar(ChatUtil.format(message), BarColor.PURPLE, BarStyle.SEGMENTED_20);
        bossBar.setProgress(progress);
        bossBar.addPlayer(player);
        bossBars.put(player.getUniqueId(), bossBar);
        return bossBar;
    }

    public static void removeBossBar(Player player) {
        BossBar bossBar = bossBars.get(player.getUniqueId());
        if(bossBar == null) return;
        bossBar.removeAll();
        bossBars.remove(player.getUniqueId());
    }

}
