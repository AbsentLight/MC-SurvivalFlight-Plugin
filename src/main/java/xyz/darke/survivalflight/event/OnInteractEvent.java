package xyz.darke.survivalflight.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnInteractEvent implements Listener {

    @EventHandler
    void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (event.hasItem()) {
            if (block != null && block.getType() == Material.JUKEBOX) {
                if (item != null && item.getType() == Material.ELYTRA) {

                    OnFlightRitual.flightRitual(block, player, item);

                    event.setCancelled(true);
                }
            }
        }
    }
}
