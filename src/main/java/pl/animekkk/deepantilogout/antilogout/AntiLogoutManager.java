package pl.animekkk.deepantilogout.antilogout;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class AntiLogoutManager {

    private final HashMap<UUID, Fight> fights = new HashMap<>();

    public Fight getFight(UUID uuid) {
        return this.fights.get(uuid);
    }

    public boolean isFighting(UUID uuid) {
        return this.fights.containsKey(uuid);
    }


    public void addFight(Fight fight) {
        this.fights.put(fight.getVictim().getUniqueId(), fight);
    }

    public void removeFight(UUID uuid) {
        if(fights.containsKey(uuid)) this.fights.remove(uuid);
    }

    public Collection<Fight> getFights() {
        return ((HashMap<UUID, Fight>) this.fights.clone()).values();
    }

    public void clearFights() {
        this.fights.clear();
    }

}
