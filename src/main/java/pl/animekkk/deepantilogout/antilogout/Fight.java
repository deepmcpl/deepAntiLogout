package pl.animekkk.deepantilogout.antilogout;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

@Data public class Fight {

    private final Player victim;
    private Player lastDamager;
    private final int fightTime;
    @Setter(AccessLevel.NONE)
    private int timeLeft;

    @Setter(AccessLevel.NONE)
    private double totalDamageDealt = 0;
    private final HashMap<UUID, Double> damageDealt = new HashMap<>();

    public void resetTimeLeft() {
        this.timeLeft = this.fightTime;
    }

    public void subtractSecond() {
        this.subtractSeconds(1);
    }

    public void subtractSeconds(int seconds) {
        this.timeLeft -= seconds;
    }

    public void addDamage(UUID uuid, double damage) {
        this.totalDamageDealt += damage;
        if(this.damageDealt.containsKey(uuid)) damage += this.damageDealt.get(uuid);
        this.damageDealt.put(uuid, damage);
    }

}
