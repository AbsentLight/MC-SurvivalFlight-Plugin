package xyz.darke.survivalflight.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.darke.survivalflight.SurvivalFlight;

public class OnPlayerLeave implements Listener {

    private final SurvivalFlight main;

    public OnPlayerLeave(SurvivalFlight survivalFlight) {
        this.main = survivalFlight;
    }

    @EventHandler
    void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
    }


}
