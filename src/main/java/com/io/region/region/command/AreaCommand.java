package com.io.region.region.command;

import com.io.region.region.api.*;
import com.io.region.region.implement.AreaImp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AreaCommand implements CommandExecutor, Permission, Protection {

    //추후 tabCompleter 업데이트 예정
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Area area = new AreaImp();
        if (args.length == 0) {
            p.sendMessage("area command");
        } else {
            if (p.isOp()) { // 아직은 오피 제한
                switch (args[0]) {
                    case "permission": {
                        switch (args[1]) {
                            case "add": {
                                String region = args[2];
                                Player player = Bukkit.getPlayer(args[3]);
                                String perm = args[4];
                                switch (perm) {
                                    case "exit": {
                                        area.addPermission(player, region, exit);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [exit] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "entrance": {
                                        area.addPermission(player, region, entrance);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [entrance] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "item_pick_up": {
                                        area.addPermission(player, region, item_pick_up);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [item_pick_up] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "item_drop": {
                                        area.addPermission(player, region, item_drop);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [item_drop] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "block_place": {
                                        area.addPermission(player, region, block_place);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_place] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "block_break": {
                                        area.addPermission(player, region, block_break);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_break] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "block_ignite": {
                                        area.addPermission(player, region, block_ignite);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_ignite] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "hanging_place": {
                                        area.addPermission(player, region, hanging_place);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [hanging_place] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "hanging_break": {
                                        area.addPermission(player, region, hanging_break);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [hanging_break] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "bucket_fill": {
                                        area.addPermission(player, region, bucket_fill);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bucket_fill] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "bucket_empty": {
                                        area.addPermission(player, region, bucket_empty);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bucket_empty] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "projectile_launch": {
                                        area.addPermission(player, region, projectile_launch);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [projectile_launch] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "interact": {
                                        area.addPermission(player, region, interact);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [interact] 권한을 주었습니다.");
                                        break;
                                    }
                                    case "bed_leave": {
                                        area.addPermission(player, region, bed_leave);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bed_leave] 권한을 주었습니다.");
                                        break;
                                    }
                                    default: {
                                        p.sendMessage("권한을 찾지 못했습니다.");
                                        break;
                                    }
                                }
                                break;
                            }
                            case "remove": {
                                String region = args[2];
                                Player player = Bukkit.getPlayer(args[3]);
                                String perm = args[4];
                                switch (perm) {
                                    case "exit": {
                                        area.removePermission(player, region, exit);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [exit] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "entrance": {
                                        area.removePermission(player, region, entrance);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [entrance] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "item_pick_up": {
                                        area.removePermission(player, region, item_pick_up);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [item_pick_up] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "item_drop": {
                                        area.removePermission(player, region, item_drop);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [item_drop] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "block_place": {
                                        area.removePermission(player, region, block_place);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_place] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "block_break": {
                                        area.removePermission(player, region, block_break);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_break] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "block_ignite": {
                                        area.removePermission(player, region, block_ignite);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [block_ignite] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "hanging_place": {
                                        area.removePermission(player, region, hanging_place);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [hanging_place] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "hanging_break": {
                                        area.removePermission(player, region, hanging_break);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [hanging_break] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "bucket_fill": {
                                        area.removePermission(player, region, bucket_fill);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bucket_fill] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "bucket_empty": {
                                        area.removePermission(player, region, bucket_empty);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bucket_empty] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "projectile_launch": {
                                        area.removePermission(player, region, projectile_launch);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [projectile_launch] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "interact": {
                                        area.removePermission(player, region, interact);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [interact] 권한을 제거했습니다.");
                                        break;
                                    }
                                    case "bed_leave": {
                                        area.removePermission(player, region, bed_leave);
                                        p.sendMessage("[" + region + "]" + " 구역의 " + "[" + player + "]" + " 구성원에게 [bed_leave] 권한을 제거했습니다.");
                                        break;
                                    }
                                    default: {
                                        p.sendMessage("권한을 찾지 못했습니다.");
                                        break;
                                    }
                                }
                                break;
                            }
                            default: {
                                p.sendMessage("그딴 argument 는 없어요~~");
                                break;
                            }
                        }
                        break;
                    }
                    case "protection": {
                        switch (args[1]) {
                            case "add": {
                                String region = args[2];
                                String pro = args[3];
                                switch (pro) {
                                    case "damage": {
                                        area.addProtection(region, damage);
                                        p.sendMessage("[" + region + "]" + " 구역에 [damage] 보호를 추가했습니다.");
                                        break;
                                    }
                                    case "potion": {
                                        area.addProtection(region, potion);
                                        p.sendMessage("[" + region + "]" + " 구역에 [potion] 보호를 추가했습니다.");
                                        break;
                                    }
                                    case "explosion": {
                                        area.addProtection(region, explosion);
                                        p.sendMessage("[" + region + "]" + " 구역에 [explosion] 보호를 추가했습니다.");
                                        break;
                                    }
                                    case "piston": {
                                        area.addProtection(region, piston);
                                        p.sendMessage("[" + region + "]" + " 구역에 [piston] 보호를 추가했습니다.");
                                        break;
                                    }
                                    case "red_stone": {
                                        area.addProtection(region, red_stone);
                                        p.sendMessage("[" + region + "]" + " 구역에 [red_stone] 보호를 추가했습니다.");
                                        break;
                                    }
                                    case "fire": {
                                        area.addProtection(region, fire);
                                        p.sendMessage("[" + region + "]" + " 구역에 [fire] 보호를 추가했습니다.");
                                        break;
                                    }
                                    default: {
                                        p.sendMessage("보호를 찾지 못했습니다.");
                                        break;
                                    }
                                }
                                break;
                            }
                            case "remove": {
                                String region = args[2];
                                String pro = args[3];
                                switch (pro) {
                                    case "damage": {
                                        area.removeProtection(region, damage);
                                        p.sendMessage("[" + region + "]" + " 구역에 [damage] 보호를 제거했습니다.");
                                        break;
                                    }
                                    case "potion": {
                                        area.removeProtection(region, potion);
                                        p.sendMessage("[" + region + "]" + " 구역에 [potion] 보호를 제거했습니다.");
                                        break;
                                    }
                                    case "explosion": {
                                        area.removeProtection(region, explosion);
                                        p.sendMessage("[" + region + "]" + " 구역에 [explosion] 보호를 제거했습니다.");
                                        break;
                                    }
                                    case "piston": {
                                        area.removeProtection(region, piston);
                                        p.sendMessage("[" + region + "]" + " 구역에 [piston] 보호를 제거했습니다.");
                                        break;
                                    }
                                    case "red_stone": {
                                        area.removeProtection(region, red_stone);
                                        p.sendMessage("[" + region + "]" + " 구역에 [red_stone] 보호를 제거했습니다.");
                                        break;
                                    }
                                    case "fire": {
                                        area.removeProtection(region, fire);
                                        p.sendMessage("[" + region + "]" + " 구역에 [fire] 보호를 제거했습니다.");
                                        break;
                                    }
                                    default: {
                                        p.sendMessage("보호를 찾지 못했습니다.");
                                        break;
                                    }
                                }
                                break;
                            }
                            default: {
                                p.sendMessage("그딴 argument 는 없어요~~");
                                break;
                            }
                        }
                        break;
                    }
                    default: {
                        p.sendMessage("그딴 argument 는 없어요~~");
                        break;
                    }
                }
            } else {
                p.sendMessage("당신은 오피가 아닙니다.");
            }
        }
        return false;
    }
}
