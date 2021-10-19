package com.io.region.region.plugin;

import com.io.region.region.command.AreaCommand;
import com.io.region.region.command.MemberCommand;
import com.io.region.region.command.RegionCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class RegionPlugin extends JavaPlugin {

    private void logMessage(String msg) {
        Logger logger = getServer().getLogger();
        logger.info(msg);
    }

    private void setupCommand() {
        Objects.requireNonNull(getCommand("region")).setExecutor(new RegionCommand());
        Objects.requireNonNull(getCommand("area")).setExecutor(new AreaCommand());
        Objects.requireNonNull(getCommand("member")).setExecutor(new MemberCommand());
    }

    private void setupEvent() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onEnable() {
        logMessage("플러그인 활성화");
        setupCommand();
        setupEvent();
    }

    @Override
    public void onDisable() {
        logMessage("플러그인 비활성화");
    }
}
