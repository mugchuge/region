package com.io.region.region.api;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface Regions {

    Set<String> regions = new HashSet<>();
    Map<String, double[]> regionMinBox = new HashMap<>();
    Map<String, double[]> regionMaxBox = new HashMap<>();
    Map<String, Player> regionOwner = new HashMap<>();

    void registerNewRegion(String region, double[] minBox, double[] maxBox);

    void removeRegion(String region);

    void relocateRegion(String region, double[] newMinBox, double[] newMaxBox);

    boolean hasRegion(String region);

    void giveOwnerInRegion(String region, Player owner);

    boolean isOwner(String region, Player owner);

    String getRegion(Player owner);

    boolean contains(String region, double[] loc);
}
