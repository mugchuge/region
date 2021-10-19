package com.io.region.region.api;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;

public interface Area {

    void addPermission(Player p, String region, Map<Player, String> perm);

    boolean removePermission(Player p, String region, Map<Player, String> perm);

    boolean hasPermission(Player p, String region, Map<Player, String> perm);

    void addProtection(String region, Set<String> pro);

    boolean removeProtection(String region, Set<String> pro);

    boolean hasProtection(String region, Set<String> pro);
}
