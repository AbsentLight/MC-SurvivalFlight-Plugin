package xyz.darke.survivalflight;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import xyz.darke.survivalflight.command.CanIHasFly;
import xyz.darke.survivalflight.data.PlayerData;
import xyz.darke.survivalflight.event.OnInteractEvent;
import xyz.darke.survivalflight.event.OnPlayerJoin;
import xyz.darke.survivalflight.event.OnPlayerLeave;
import xyz.darke.survivalflight.util.PlayerDataIO;
import xyz.darke.survivalflight.util.UpdateFlightTimer;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class SurvivalFlight extends JavaPlugin {

    static Random random = new Random();
    private PlayerData playerData = new PlayerData();
    public final Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Setup Plugin Folder
        PlayerDataIO.setupPluginFolder();

        // Attempt to load player data
        playerData = PlayerDataIO.readPlayerData();
        if (playerData == null) {
            logger.log(Level.WARNING, "PlayerDataIO.readPlayerData() returned null");
        }

        getServer().getPluginManager().registerEvents(new OnPlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(this), this);
        getServer().getPluginManager().registerEvents(new OnInteractEvent(), this);

        getCommand("canihasfly").setExecutor(new CanIHasFly());

        // Start periodic management task
        BukkitTask TaskName = new UpdateFlightTimer(this).runTaskTimer(this, 20, 20);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player player : getServer().getOnlinePlayers()) {
            player.setAllowFlight(false);
        }

        PlayerDataIO.writePlayerData(playerData);

    }

    public static SurvivalFlight getInstance() {
        return JavaPlugin.getPlugin(SurvivalFlight.class);
    }

    public PlayerData getPlayerData() {
        return playerData;
    }


}
