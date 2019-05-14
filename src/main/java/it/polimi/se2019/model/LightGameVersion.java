package it.polimi.se2019.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class LightGameVersion implements Serializable {
    private int skullsNum;
    private Map<String, String> playerPlatform; //character MAIUSCOLO - platform ("0,0")
    private Map<String, AmmoRep> ammoTilePlatform; //platform - AmmoRep
    private Map<String, List<CardRep>> playerPowerups; //character - CardRep
    private Map<String, List<CardRep>> playerWeapons; // character - CardRep
    private Map<String, BoardRep> playerBoardRep; //character - BoardRep
    private Map<String, List<CardRep>> platformWeapons;

    public int getSkullsNum() {
        return skullsNum;
    }

    public void setSkullsNum(int skullsNum) {
        this.skullsNum = skullsNum;
    }

    public Map<String, String> getPlayerPlatform() {
        return playerPlatform;
    }

    public void setPlayerPlatform(Map<String, String> playerPlatform) {
        this.playerPlatform = playerPlatform;
    }

    public Map<String, List<CardRep>> getPlayerPowerups() {
        return playerPowerups;
    }

    public void setPlayerPowerups(Map<String, List<CardRep>> playerPowerups) {
        this.playerPowerups = playerPowerups;
    }

    public Map<String, BoardRep> getPlayerBoardRep() {
        return playerBoardRep;
    }

    public void setPlayerBoardRep(Map<String, BoardRep> playerBoardRep) {
        this.playerBoardRep = playerBoardRep;
    }

    public Map<String, List<CardRep>> getPlatformWeapons() {
        return platformWeapons;
    }

    public void setPlatformWeapons(Map<String, List<CardRep>> platformWeapons) {
        this.platformWeapons = platformWeapons;
    }

    public Map<String, AmmoRep> getAmmoTilePlatform() {
        return ammoTilePlatform;
    }

    public void setAmmoTilePlatform(Map<String, AmmoRep> ammoTilePlatform) {
        this.ammoTilePlatform = ammoTilePlatform;
    }

    public Map<String, List<CardRep>> getPlayerWeapons() {
        return playerWeapons;
    }

    public void setPlayerWeapons(Map<String, List<CardRep>> playerWeapons) {
        this.playerWeapons = playerWeapons;
    }
}
