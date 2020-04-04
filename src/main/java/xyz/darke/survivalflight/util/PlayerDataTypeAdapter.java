package xyz.darke.survivalflight.util;

import com.google.gson.*;
import org.bukkit.OfflinePlayer;
import xyz.darke.survivalflight.SurvivalFlight;
import xyz.darke.survivalflight.data.PFPlayer;
import xyz.darke.survivalflight.data.PlayerData;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.UUID;

public class PlayerDataTypeAdapter implements JsonSerializer<PlayerData>, JsonDeserializer<PlayerData> {

    final Gson gson = new Gson();

    public JsonElement serialize(PlayerData players, Type typeOfT, JsonSerializationContext context) {
        JsonObject json = new JsonObject();

        // Get array of all players who have ever played on the server
        OfflinePlayer[] allPlayers = SurvivalFlight.getInstance().getServer().getOfflinePlayers();

        for (OfflinePlayer oPlayer : allPlayers) {

            PFPlayer player = players.getPlayer(oPlayer.getUniqueId());

            if (player == null) {
                player = new PFPlayer(oPlayer.getUniqueId(), 0);
            }

            json.addProperty(String.valueOf(player.getUniqueID()), gson.toJson(player));
        }

        return json;
    }

    public PlayerData deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) {
        JsonObject json = element.getAsJsonObject();

        PlayerData players = new PlayerData();

        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            String name = entry.getKey();
            PFPlayer PFPlayer = gson.fromJson(entry.getValue(), PFPlayer.class);
            PFPlayer.setUniqueID(UUID.fromString(name));

            players.addPlayer(PFPlayer);
        }

        return players;
    }

}
