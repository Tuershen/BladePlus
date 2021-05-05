package pers.tuershen.bladeplus.modular;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.bukkit.command.AdminCommandHandle;
import pers.tuershen.bladeplus.bukkit.command.PlayerCommandHandle;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class ModularCore {

    public List<Listener> modularList = new ArrayList<>();

    private List<PlayerCommandHandle<? extends CommandSender>> playerCommandHandles = new ArrayList<>();

    private List<AdminCommandHandle<? extends CommandSender>> adminCommandHandles = new ArrayList<>();

    private final URL fileUrl = BladePlusMain.class.getProtectionDomain().getCodeSource().getLocation();

    public void printModularInfo(String... info){
        BladePlusMain.bladePlusMain.getServer().getConsoleSender().sendMessage(info);
    }

    public <T extends Listener> void registerListener(T listener){
        modularList.add(listener);
        BladePlusMain.bladePlusMain.registerModularListener(listener);
    }

    public void unregisterListener(){
        this.modularList.forEach((l -> BladePlusMain.bladePlusMain.unregisterModularListener(l)));
        modularList = new ArrayList<>();
    }

    public <C extends CommandSender, T extends PlayerCommandHandle<C>> void registerPlayerCommand(T command){
        playerCommandHandles.add(command);
    }

    public <C extends CommandSender, T extends AdminCommandHandle<C>> void registerAdminCommand(T command){
        adminCommandHandles.add(command);
    }


    public void saveDefaultFile(File file) {
        if (!file.exists()) {
            BladePlusMain.bladePlusMain.saveResource("file/config/" + file.getName(), false);
        }
    }

    public String getPath() {
        String[] path = this.fileUrl.getPath().split("/");
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < path.length - 1; i++)
            buffer.append(path[i] + "\\");
        return buffer.toString();
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public abstract String getCode();

    public abstract void onEnable();

    public abstract void onDisable();



}
