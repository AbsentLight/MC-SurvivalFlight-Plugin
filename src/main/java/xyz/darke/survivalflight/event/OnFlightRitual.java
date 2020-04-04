package xyz.darke.survivalflight.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.util.CreateRandomFirework;

public class OnFlightRitual {

    static void flightRitual(Block block, Player player, ItemStack item) {

        SurvivalFlight main = JavaPlugin.getPlugin(SurvivalFlight.class);

        item.setAmount(0); // Remove Elytra from Player
        block.setType(Material.AIR); // Remove Jukebox from world
        player.setAllowFlight(true); // Grant the player flight

        main.getPlayerData().getPlayer(player.getUniqueId()).addFlightTime(8 * 60 * 60);

        Location location = block.getLocation();
        for (int i = 0; i < 5; i++) {
            CreateRandomFirework.createRandomFirework(location);
        }

    }

}
