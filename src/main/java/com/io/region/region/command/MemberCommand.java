package com.io.region.region.command;

import com.io.region.region.api.Member;
import com.io.region.region.api.Regions;
import com.io.region.region.implement.MemberImp;
import com.io.region.region.implement.RegionImp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MemberCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Member member = new MemberImp();
        Regions regions = new RegionImp();
        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage("member command");
        } else {
            if (p.isOp()) { // 아직 오피제한
                switch (args[0]) {
                    case "add": {
                        Player player = Bukkit.getPlayer(args[1]);
                        String region = args[2];
                        if (regions.hasRegion(region)) {
                            member.addMember(player, region);
                            p.sendMessage("플레이어 " + "[" + player + "]" + " 를" + "[" + region + "]" + " 구역의 구성원으로 추가했습니다.");
                        } else {
                            p.sendMessage("[" + region + "]" + " 구역은 존재하지 않습니다.");
                        }
                    }
                    case "remove": {
                        Player player = Bukkit.getPlayer(args[1]);
                        String region = args[2];
                        if (regions.hasRegion(region)) {
                            member.removeMember(player, region);
                            p.sendMessage("플레이어 " + "[" + player + "]" + " 를" + "[" + region + "]" + " 구역의 구성원에서 제거했습니다.");
                        } else {
                            p.sendMessage("[" + region + "]" + " 구역은 존재하지 않습니다.");
                        }
                    }
                }
            } else {
                p.sendMessage("당신은 오피가 아닙니다.");
            }
        }
        return false;
    }
}
