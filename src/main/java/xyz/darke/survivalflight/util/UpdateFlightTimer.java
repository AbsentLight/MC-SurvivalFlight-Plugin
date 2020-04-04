package xyz.darke.survivalflight.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.data.PFPlayer;

public class UpdateFlightTimer extends BukkitRunnable {

    final SurvivalFlight main;

    public UpdateFlightTimer(SurvivalFlight main) {
        this.main = main;
    }

    public void run() {

        // Get all online players
        Player[] players = main.getServer().getOnlinePlayers().toArray(new Player[0]);

        for (Player p : players) {
            PFPlayer player = main.getPlayerData().getPlayer(p.getUniqueId());
            player.decTimeRemaining();

            switch (player.getFlightTimeRemaining()) {
                case 0:
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    break;
                case 300:
                    warnPlayer(p, "You have 5 minutes of flight remaining!");
                    break;
                case 60:
                    warnPlayer(p, "You have 1 minute of flight remaining!");
                    break;
                case 15:
                    warnPlayer(p, "You have 15 seconds of flight remaining!");
                    break;
                default:
            }
        }

    }

    public void warnPlayer(Player player, String message) {
        player.sendMessage(ChatColor.RED + message);
    }
}
