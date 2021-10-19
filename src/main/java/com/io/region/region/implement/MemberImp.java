package com.io.region.region.implement;

import com.io.region.region.api.Member;
import org.bukkit.entity.Player;

public class MemberImp implements Member {

    @Override
    public void addMember(Player p, String region) {
        members.put(p, region);
    }

    @Override
    public boolean isMember(Player p, String region) {
        return members.get(p).equals(region);
    }

    @Override
    public String getMemberWhatRegion(Player p) {
        return members.get(p);
    }

    @Override
    public boolean removeMember(Player p, String region) {
        if (isMember(p, region)) {
            return members.remove(p, region);
        } else {
            return false;
        }
    }
}
