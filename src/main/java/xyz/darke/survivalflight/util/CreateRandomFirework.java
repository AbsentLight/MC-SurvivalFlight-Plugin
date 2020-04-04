package xyz.darke.survivalflight.util;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class CreateRandomFirework {
    public static void createRandomFirework(Location loc) {

        Random r = new Random();

        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        int h = r.nextInt(1) + 1;

        fwm.setPower(h);

        Color color = Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255));

        Type type = Type.BALL;

        int t = r.nextInt(4);
        switch (t) {
            case 0:
                type = Type.BALL;
                break;
            case 1:
                type = Type.BALL_LARGE;
                break;
            case 2:
                type = Type.STAR;
                break;
            case 3:
                type = Type.BURST;
                break;
        }

        fwm.addEffect(FireworkEffect.builder().withColor(color).flicker(true).with(type).build());

        fw.setFireworkMeta(fwm);

    }

}
