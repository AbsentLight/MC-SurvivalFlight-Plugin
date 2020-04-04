package xyz.darke.survivalflight.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.data.PlayerData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;


public class PlayerDataIO {

    private static final String filepath = "plugins/SurvivalFlight/player_data.json";
    private static SurvivalFlight main;

    public PlayerDataIO(SurvivalFlight survivalFlight) {
        main = survivalFlight;
    }


    public static PlayerData readPlayerData() {
        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(PlayerData.class, new PlayerDataTypeAdapter());

        Gson gson = builder.create();

        PlayerData players;

        try {
            FileReader reader = new FileReader(filepath);
            players = gson.fromJson(reader, PlayerData.class);
            reader.close();
        } catch (Exception e) {
            main.logger.log(Level.WARNING, "Failed to read from disk");
            players = new PlayerData();
            e.printStackTrace();
        }

        return players;
    }

    public static void writePlayerData(PlayerData players) {
        GsonBuilder builder = new GsonBuilder();
        //builder.registerTypeAdapter(PlayerData.class, new PlayerDataTypeAdapter());

        Gson gson = builder.create();

        try {
            FileWriter writer = new FileWriter(filepath);
            gson.toJson(players, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            main.logger.log(Level.WARNING, "Failed to write data to disk!");
            e.printStackTrace();
        }


    }

    public static void setupPluginFolder() {
        File file = new File("plugins/SurvivalFlight");
        boolean folderWasCreated = file.mkdir();

        file = new File(filepath);

        try {
            boolean fileWasCreated = file.createNewFile();
            FileWriter writer = new FileWriter(filepath);
            writer.write("{}");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            main.logger.log(Level.WARNING, "Failed to create player_data.json");
            e.printStackTrace();
        }

    }


}
