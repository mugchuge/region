package com.io.region.region.command;

import com.io.region.region.api.Regions;
import com.io.region.region.implement.RegionImp;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class RegionCommand implements CommandExecutor {

    public WorldEditPlugin getWorldEdit() {
        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
        else return null;
    }

    //추후 tabCompleter 업데이트 예정
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        Regions regions = new RegionImp();
        if (args.length == 0) {
            p.sendMessage("region command");
        } else {
            if (p.isOp()) { // 오피 제한
                switch (args[0]) {
                    case "add": {
                        String name = args[1];
                        Region region = null;
                        try {
                            region = getWorldEdit().getSession(p).getSelection((World) p.getWorld());
                        } catch (IncompleteRegionException e) {
                            p.sendMessage("구역을 지정하지 않았습니다.");
                        }
                        assert region != null;
                        BlockVector3 min = region.getMinimumPoint();
                        BlockVector3 max = region.getMaximumPoint();
                        double[] minBox = {min.getX(), min.getY(), min.getZ()};
                        double[] maxBox = {max.getX(), max.getY(), max.getZ()};
                        regions.registerNewRegion(name, minBox, maxBox);
                        p.sendMessage("[" + name + "]" + " 구역을 추가했습니다.");
                        break;
                    }
                    case "remove": {
                        String name = args[1];
                        regions.removeRegion(name);
                        p.sendMessage("지정한 구역 " + "[" + name + "]" + " 을 삭제했습니다.");
                    }
                    case "relocate": {
                        String name = args[1];
                        if (regions.hasRegion(name)) {
                            Region region = null;
                            try {
                                region = getWorldEdit().getSession(p).getSelection((World) p.getWorld());
                            } catch (IncompleteRegionException e) {
                                p.sendMessage("구역을 지정하지 않았습니다.");
                            }
                            assert region != null;
                            BlockVector3 min = region.getMinimumPoint();
                            BlockVector3 max = region.getMaximumPoint();
                            double[] newMinBox = {min.getX(), min.getY(), min.getZ()};
                            double[] newMaxBox = {max.getX(), max.getY(), max.getZ()};
                            regions.relocateRegion(name, newMinBox, newMaxBox);
                            p.sendMessage("[" + name + "]" + " 구역을 추가했습니다.");
                        } else {
                            p.sendMessage("구역을 찾지 못했습니다.");
                        }
                        break;
                    }
                    case "setOwner": {
                        String name = args[1];
                        if (regions.hasRegion(name)) {
                            Player player = Bukkit.getPlayer(args[2]);
                            regions.giveOwnerInRegion(name, player);
                            p.sendMessage("[" + player.getName() + "]" + " 에게 " + "[" + name + "]" + "구역의 매니저 권한을 줬습니다.");
                        } else {
                            p.sendMessage("구역을 찾지 못했습니다.");
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
