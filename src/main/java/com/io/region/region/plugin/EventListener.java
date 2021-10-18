package com.io.region.region.plugin;

import com.io.region.region.api.*;
import com.io.region.region.implement.AreaImp;
import com.io.region.region.implement.MemberImp;
import com.io.region.region.implement.RegionImp;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Collection;
import java.util.List;

public class EventListener implements Listener, Permission, Protection {

    Area area = new AreaImp();
    Member member = new MemberImp();
    Regions regions = new RegionImp();

    // (조금씩 이벤트 업뎃 예정)

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(getRegion, pLoc)) {
                if (!regions.contains(getRegion, pLoc)) {
                    e.setCancelled(false);
                }
            } else if (!regions.contains(getRegion, pLoc)) {
                if (regions.contains(getRegion, pLoc)) {
                    e.setCancelled(false);
                }
            }
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                if (!regions.contains(region, pLoc)) {
                    e.setCancelled(!area.hasPermission(p, region, exit));
                }
            } else if (!regions.contains(region, pLoc)) {
                if (regions.contains(region, pLoc)) {
                    e.setCancelled(!area.hasPermission(p, region, entrance));
                }
            }
        }
    }

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, item_pick_up));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, item_drop));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockCanBuild(BlockCanBuildEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (p != null) {
            if (regions.isOwner(getRegion, p) || p.isOp()) {
                e.setBuildable(false);
            } else {
                String region = member.getMemberWhatRegion(p);
                double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                if (regions.contains(region, pLoc)) {
                    if (!area.hasPermission(p, region, block_place)) {
                        e.setBuildable(false);
                    }
                } else {
                    e.setBuildable(false);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, block_place));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, block_break));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (p != null) {
            if (regions.isOwner(getRegion, p) || p.isOp()) {
                e.setCancelled(false);
            } else {
                String region = member.getMemberWhatRegion(p);
                double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                if (regions.contains(region, pLoc)) {
                    e.setCancelled(!area.hasPermission(p, region, block_ignite) || area.hasProtection(region, fire));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onHangingPlace(HangingPlaceEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (p != null) {
            if (regions.isOwner(getRegion, p) || p.isOp()) {
                e.setCancelled(false);
            } else {
                String region = member.getMemberWhatRegion(p);
                double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                if (regions.contains(region, pLoc)) {
                    e.setCancelled(!area.hasPermission(p, region, hanging_place));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onHangingBreak(HangingBreakEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            String entityGet = String.valueOf(entity);
            Player p = Bukkit.getPlayer(entityGet);
            String getRegion = regions.getRegion(p);
            if (p != null) {
                if (regions.isOwner(getRegion, p) || p.isOp()) {
                    e.setCancelled(false);
                } else {
                    String region = member.getMemberWhatRegion(p);
                    double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                    if (regions.contains(region, pLoc)) {
                        e.setCancelled(!area.hasPermission(p, region, hanging_break));
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        } else if (entity instanceof Projectile) {
            ProjectileSource shooter = ((Projectile) entity).getShooter();
            if (shooter instanceof Player) {
                String shooterGet = String.valueOf(shooter);
                Player p = Bukkit.getPlayer(shooterGet);
                String getRegion = regions.getRegion(p);
                if (p != null) {
                    if (regions.isOwner(getRegion, p) || p.isOp()) {
                        e.setCancelled(false);
                    } else {
                        String region = member.getMemberWhatRegion(p);
                        double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                        if (regions.contains(region, pLoc)) {
                            e.setCancelled(!area.hasPermission(p, region, hanging_break));
                        } else {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, bucket_fill));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, bucket_empty));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent e) {
        Projectile entity = e.getEntity();
        ProjectileSource shooter = entity.getShooter();
        if (shooter instanceof Player) {
            String shooterGet = String.valueOf(shooter);
            Player p = Bukkit.getPlayer(shooterGet);
            String getRegion = regions.getRegion(p);
            if (p != null) {
                if (regions.isOwner(getRegion, p) || p.isOp()) {
                    e.setCancelled(false);
                } else {
                    String region = member.getMemberWhatRegion(p);
                    double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                    if (regions.contains(region, pLoc)) {
                        e.setCancelled(!area.hasPermission(p, region, projectile_launch));
                    } else {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(!area.hasPermission(p, region, interact));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                Entity entity = e.getRightClicked();
                double[] eLoc = {entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ()};
                if (regions.contains(region, eLoc)) {
                    area.hasPermission(p, region, interact);
                }
                e.setCancelled(!area.hasPermission(p, region, interact));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBedLeave(PlayerBedLeaveEvent e) {
        Player p = e.getPlayer();
        String getRegion = regions.getRegion(p);
        if (regions.isOwner(getRegion, p) || p.isOp()) {
            e.setCancelled(false);
        } else {
            String region = member.getMemberWhatRegion(p);
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                if (!regions.contains(region, pLoc)) {
                    e.setCancelled(!area.hasPermission(p, region, bed_leave) && !area.hasPermission(p, region, exit));
                } else {
                    e.setCancelled(!area.hasPermission(p, region, bed_leave));
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof Player) {
            String entityGet = String.valueOf(entity);
            Player p = Bukkit.getPlayer(entityGet);
            String region = member.getMemberWhatRegion(p);
            assert p != null;
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(area.hasProtection(region, damage));
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (damager instanceof Player) {
            String damagerGet = String.valueOf(damager);
            Player p = Bukkit.getPlayer(damagerGet);
            String region = member.getMemberWhatRegion(p);
            assert p != null;
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                e.setCancelled(area.hasProtection(region, damage));
            } else {
                e.setCancelled(true);
            }
        } else if (damager instanceof Projectile) {
            ProjectileSource shooter = ((Projectile) damager).getShooter();
            if (shooter instanceof Player) {
                String shooterGet = String.valueOf(shooter);
                Player p = Bukkit.getPlayer(shooterGet);
                String region = member.getMemberWhatRegion(p);
                assert p != null;
                double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                if (regions.contains(region, pLoc)) {
                    e.setCancelled(area.hasProtection(region, damage));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent e) {
        Entity entity = e.getEntity();
        Block b = e.getDamager();
        if (entity instanceof Player) {
            String entityGet = String.valueOf(entity);
            Player p = Bukkit.getPlayer(entityGet);
            String region = member.getMemberWhatRegion(p);
            assert p != null;
            double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
            if (regions.contains(region, pLoc)) {
                assert b != null;
                double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
                if (regions.contains(region, bLoc)) {
                    e.setCancelled(area.hasProtection(region, damage));
                }
            } else {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent e) {
        Collection<LivingEntity> livingEntities = e.getAffectedEntities();
        for (LivingEntity livingEntity : livingEntities) {
            if (livingEntity instanceof Player) {
                String livingEntityGet = String.valueOf(livingEntity);
                Player p = Bukkit.getPlayer(livingEntityGet);
                String region = member.getMemberWhatRegion(p);
                assert p != null;
                double[] pLoc = {p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ()};
                if (regions.contains(region, pLoc)) {
                    if (area.hasProtection(region, potion)) {
                        e.setIntensity(livingEntity, 0.0);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onLingeringPotionSplash(LingeringPotionSplashEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            AreaEffectCloud areaEffectCloud = e.getAreaEffectCloud();
            double[] aecLoc = {areaEffectCloud.getLocation().getX(), areaEffectCloud.getLocation().getY(), areaEffectCloud.getLocation().getZ()};
            if (regions.contains(region, aecLoc)) {
                e.setCancelled(area.hasProtection(region, potion));
            }
        }
    }

    @EventHandler
    public void onExplosionByBlock(BlockExplodeEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            Block b = e.getBlock();
            double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
            if (regions.contains(region, bLoc)) {
                e.setCancelled(area.hasProtection(region, explosion));
            }
        }
    }

    @EventHandler
    public void onExplosionByEntity(EntityExplodeEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            Entity entity = e.getEntity();
            double[] eLoc = {entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ()};
            if (regions.contains(region, eLoc)) {
                e.setCancelled(area.hasProtection(region, explosion));
            }
        }
    }

    @EventHandler
    public void onPistonExtend(BlockPistonExtendEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            BlockFace face = e.getDirection();
            Block b = e.getBlock();
            Block head = b.getRelative(face);
            double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
            if (regions.contains(region, bLoc)) {
                double[] hLoc = {head.getLocation().getX(), head.getLocation().getY(), head.getLocation().getZ()};
                if (!regions.contains(region, hLoc)) {
                    e.setCancelled(true);
                } else {
                    e.setCancelled(area.hasProtection(region, piston));
                }
            }
        }
    }

    @EventHandler
    public void onPistonRetract(BlockPistonRetractEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            Block b = e.getBlock();
            double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
            if (regions.contains(region, bLoc)) {
                List<Block> blocks = e.getBlocks();
                for (Block b2 : blocks) {
                    double[] b2Loc = {b2.getLocation().getX(), b2.getLocation().getY(), b2.getLocation().getZ()};
                    if (regions.contains(region, b2Loc)) {
                        e.setCancelled(true);
                    } else {
                        e.setCancelled(area.hasProtection(region, piston));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onRedStone(BlockRedstoneEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            Block b = e.getBlock();
            double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
            if (regions.contains(region, bLoc)) {
                if (area.hasProtection(region, red_stone)) e.setNewCurrent(0);
            }
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player p : players) {
            String region = member.getMemberWhatRegion(p);
            Block b = e.getIgnitingBlock();
            assert b != null;
            double[] bLoc = {b.getLocation().getX(), b.getLocation().getY(), b.getLocation().getZ()};
            if (regions.contains(region, bLoc)) {
                e.setCancelled(area.hasProtection(region, fire));
            }
        }
    }
}
