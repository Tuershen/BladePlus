package pers.tuershen.bladeplus.modular;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.modular.ModularInjection;
import pers.tuershen.bladeplus.bukkit.command.admin.AbstractAdminCommand;
import pers.tuershen.bladeplus.bukkit.command.player.AbstractPlayerCommand;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class ModularCore {

    public Set<Listener> modularList = new HashSet<>();

    private final List<AbstractAdminCommand<? extends CommandSender>> adminCommands = new ArrayList<>();

    private final List<AbstractPlayerCommand<? extends CommandSender>> playerCommands = new ArrayList<>();


    private final URL fileUrl = BladePlusMain.class.getProtectionDomain().getCodeSource().getLocation();

    public void printModularInfo(String... info){
        BladePlusMain.bladePlusMain.getServer().getConsoleSender().sendMessage(info);
    }

    public <T extends Listener> void registerListener(T listener){
        modularList.add(listener);
        BladePlusMain.bladePlusMain.registerModularListener(listener);
    }

    public BladePlusMain getBladePlusMain() {
        return BladePlusMain.bladePlusMain;
    }

    public void unregisterListener(){
        this.modularList.forEach((l -> BladePlusMain.bladePlusMain.unregisterModularListener(l)));
        modularList = new HashSet<>();
    }

    public <T extends AbstractPlayerCommand<? extends CommandSender>> void registerPlayerCommandHandle(T playerCommand) {
        if (playerCommand != null) {
            BladePlusMain.bladePlusMain.registerPlayerCommandHandle(playerCommand);
            playerCommands.add(playerCommand);
        }
    }

    public <T extends AbstractAdminCommand<? extends CommandSender>> void registerAdminCommandHandle(T adminCommand) {
        if (adminCommand != null) {
            BladePlusMain.bladePlusMain.registerAdminCommandHandle(adminCommand);
            adminCommands.add(adminCommand);
        }
    }

    public void addPlayerCommandResult(String result) {
        BladePlusMain.bladePlusMain.addPlayerCommandResult(result);
    }

    public void addAdminCommandResult(String result) {
        BladePlusMain.bladePlusMain.addAdminCommandResult(result);
    }

    public void registerPlayerCommandHelp(List<String> help) {
        BladePlusMain.bladePlusMain.registerPlayerCommandHelp(this,  help);
    }

    public void registerAdminCommandHelp(List<String> help) {
        BladePlusMain.bladePlusMain.registerAdminCommandHelp(this,  help);
    }

    public void unregisterPlayerCommand(){

    }

    public void unregisterAdminCommand(){

    }

    public void saveDefaultFile(File file) {
        if (!file.exists()) {
            BladePlusMain.bladePlusMain.saveResource("file/config/" + file.getName(), false);
        }
    }

    public String getPath() {
        String[] path = this.fileUrl.getPath().split("/");
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < path.length - 1; i++)
            buffer.append(path[i]).append("\\");
        return buffer.toString();
    }

    public FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration(file);
    }

    public abstract String getCode();

    public abstract void onEnable();

    public abstract void onDisable();

    public void modularInfo() {
        boolean annotationPresent = this.getClass().isAnnotationPresent(ModularInjection.class);
        if (annotationPresent) {
            ModularInjection injection = this.getClass().getAnnotation(ModularInjection.class);
            String[] info = injection.info();
            String version = injection.version();
            String name = injection.name();
            this.printModularInfo("§7[§3"+ name +"§7] §6BladePlus附属模块"+ name);
            for (String s : info) {
                this.printModularInfo("§7[§3" + name + "§7] §6" + s);
            }
            this.printModularInfo("§7[§3"+ name +"§7] §a版本: §b"+ version);
            this.printModularInfo("§7[§3"+ name +"§7] §a游戏事件成功注册");
            this.printModularInfo("§7[§3"+ name +"§7] §b注入完毕，模块正常运行...");
        }
    }

}
