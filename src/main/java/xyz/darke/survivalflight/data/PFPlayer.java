package xyz.darke.survivalflight.data;

import java.util.UUID;

public class PFPlayer {
    private UUID uniqueID;
    private int flightTimeRemaining;

    public PFPlayer(UUID uuid, int flightTimeRemaining) {
        this.uniqueID = uuid;
        this.flightTimeRemaining = flightTimeRemaining;
    }

    public UUID getUniqueID() {
        return uniqueID;
    }

    public boolean canFly() {
        return flightTimeRemaining > 0;
    }

    public void setUniqueID(UUID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public int getFlightTimeRemaining() {
        return flightTimeRemaining;
    }

    public void setFlightTimeRemaining(int flightTimeRemaining) {
        this.flightTimeRemaining = flightTimeRemaining;
    }

    public void addFlightTime(int extraFlightTime) {
        this.flightTimeRemaining += extraFlightTime;
    }


    public void decTimeRemaining() {
        if (flightTimeRemaining > 0) {
            flightTimeRemaining--;
        }
    }
}
