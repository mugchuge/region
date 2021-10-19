package com.io.region.region.implement;

import com.io.region.region.api.Area;
import com.io.region.region.api.Member;
import com.io.region.region.api.Regions;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Set;

public class AreaImp implements Area {

    @Override
    public void addPermission(Player p, String region, Map<Player, String> perm) {
        Regions regions = new RegionImp();
        if (regions.hasRegion(region)) {
            Member member = new MemberImp();
            if (member.isMember(p, region)) {
                perm.put(p, region);
            }
        }
    }

    @Override
    public boolean hasPermission(Player p, String region, Map<Player, String> perm) {
        return perm.get(p).equals(region);
    }

    @Override
    public boolean removePermission(Player p, String region, Map<Player, String> perm) {
        if (hasPermission(p, region, perm)) {
            return perm.remove(p, region);
        } else {
            return false;
        }
    }

    @Override
    public void addProtection(String region, Set<String> pro) {
        Regions regions = new RegionImp();
        if (regions.hasRegion(region)) {
            pro.add(region);
        }
    }

    @Override
    public boolean hasProtection(String region, Set<String> pro) {
        return pro.contains(region);
    }

    @Override
    public boolean removeProtection(String region, Set<String> pro) {
        if (hasProtection(region, pro)) {
            return pro.remove(region);
        } else {
            return false;
        }
    }
}
