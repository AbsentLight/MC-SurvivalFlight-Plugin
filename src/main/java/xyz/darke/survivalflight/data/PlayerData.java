package xyz.darke.survivalflight.data;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
    private HashMap<UUID, PFPlayer> players = new HashMap<>();

    public PlayerData() {
    }

    public HashMap<UUID, PFPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<UUID, PFPlayer> players) {
        this.players = players;
    }

    public void addPlayer(PFPlayer player) {
        players.put(player.getUniqueID(), player);
    }

    public PFPlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }


}
