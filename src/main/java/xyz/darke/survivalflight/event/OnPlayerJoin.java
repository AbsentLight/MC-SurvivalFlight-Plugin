package xyz.darke.survivalflight.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.data.PFPlayer;
import xyz.darke.survivalflight.data.PlayerData;

import java.util.UUID;
import java.util.logging.Level;

public class OnPlayerJoin implements Listener {

    private final SurvivalFlight main;

    public OnPlayerJoin(SurvivalFlight survivalFlight) {
        this.main = survivalFlight;
    }

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        PlayerData players = main.getPlayerData();
        if (players == null) {
            main.logger.log(Level.WARNING, "PlayerData players == null");
        }

        UUID uuid = player.getUniqueId();

        assert players != null;
        PFPlayer pfPlayer = players.getPlayer(uuid);

        // If player is not found in PlayerData
        if (pfPlayer == null) {
            main.logger.log(Level.WARNING, "Player not found: " + player.getDisplayName());
            // Add them with defaults
            main.getPlayerData().addPlayer(new PFPlayer(player.getUniqueId(), 0));


        } else {
            if (pfPlayer.canFly()) {
                player.setAllowFlight(true);
            } else {
                player.setAllowFlight(false);
            }
        }

    }

}
