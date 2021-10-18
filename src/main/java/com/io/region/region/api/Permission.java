package com.io.region.region.api;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public interface Permission {

    Map<Player, String> exit = new HashMap<>();
    Map<Player, String> entrance = new HashMap<>();
    Map<Player, String> item_pick_up = new HashMap<>();
    Map<Player, String> item_drop = new HashMap<>();
    Map<Player, String> block_place = new HashMap<>();
    Map<Player, String> block_break = new HashMap<>();
    Map<Player, String> block_ignite = new HashMap<>();
    Map<Player, String> hanging_place = new HashMap<>();
    Map<Player, String> hanging_break = new HashMap<>();
    Map<Player, String> bucket_fill = new HashMap<>();
    Map<Player, String> bucket_empty = new HashMap<>();
    Map<Player, String> projectile_launch = new HashMap<>();
    Map<Player, String> interact = new HashMap<>();
    Map<Player, String> bed_leave = new HashMap<>();
}
