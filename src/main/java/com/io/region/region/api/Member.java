package com.io.region.region.api;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public interface Member {

    Map<Player, String> members = new HashMap<>(); // 각 플레이어 마다 한 구역의 멤버만 갖습니다.

    void addMember(Player p, String region);

    boolean removeMember(Player p, String region);

    boolean isMember(Player p, String region);

    String getMemberWhatRegion(Player p);
}
