package xyz.darke.survivalflight.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.data.PFPlayer;

public class CanIHasFly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            SurvivalFlight main = JavaPlugin.getPlugin(SurvivalFlight.class);

            PFPlayer pfPlayer = main.getPlayerData().getPlayer(player.getUniqueId());

            if (!pfPlayer.canFly()) {
                player.sendMessage(ChatColor.RED + "No!");
            } else {
                int timeRemaining = pfPlayer.getFlightTimeRemaining();

                int hours = timeRemaining / 3600;
                int hours_rem = timeRemaining % 3600;
                int mins = hours_rem / 60;
                int secs = hours_rem % 60;

                String remainingTimeForFlight = String.format("%sh%sm%ss", hours, mins, secs);

                player.sendMessage(ChatColor.GREEN + "Yes! " + ChatColor.WHITE + "You have " + remainingTimeForFlight + " remaining.");

            }

        }
        return false;
    }
}
