package pers.tuershen.bladeplus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.bukkit.command.AdminCommandHandle;
import pers.tuershen.bladeplus.bukkit.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.bukkit.command.admin.*;
import pers.tuershen.bladeplus.bukkit.command.player.*;
import pers.tuershen.bladeplus.bukkit.file.*;
import pers.tuershen.bladeplus.bukkit.listener.BladePlusListener;
import pers.tuershen.bladeplus.bukkit.listener.BladePlusUseBladeListener;
import pers.tuershen.bladeplus.bukkit.listener.BladePlusUseSASEListener;
import pers.tuershen.bladeplus.bukkit.yaml.YamlSetting;
import com.tuershen.nbtlibrary.CompoundLibraryManager;
import com.tuershen.nbtlibrary.api.CompoundLibraryApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tuershen.bladeplus.modular.ModularCore;
import pers.tuershen.bladeplus.modular.ModularManage;
import pers.tuershen.bladeplus.modular.io.ReadConfiguration;

import java.io.File;
import java.util.List;


public class BladePlusMain extends JavaPlugin {

    public static CompoundLibraryApi libraryApi;

    public static BladePlusMain bladePlusMain;

    public static IYamlSlashBlade yamlSlashBlade;

    public static BladeLanguageFile languageFile;

    public static BladeGuiSettingFile guiSettingFile;

    public static BladeMaterialFile materialFile;

    public static BladeAppraisalFile appraisalFile;

    public static BladeModelFile modelFile;

    public static BladeSpecialAttackType attackType;

    private static final ModularManage<? extends pers.tuershen.bladeplus.modular.ModularCore> modularManage = new ModularManage<>();

    public static int getModularSize(){
        return modularManage.modularMap.size();
    }

    private AdminCommandHandle adminCommandHandle;

    private PlayerCommandHandle playerCommandHandle;

    private IYamlSetting yamlSetting;

    @Override
    public void onEnable() {
        //初始化实例
        bladePlusMain = this;
        libraryApi = CompoundLibraryManager.getPluginManager(this);
        initFile();
        YamlSetting yamlSetting = new YamlSetting();
        yamlSetting.init();
        yamlSlashBlade = yamlSetting.getIYamlSlashBlade();
        adminCommandHandle = new AdminCommandHandle(yamlSetting);
        playerCommandHandle = new PlayerCommandHandle(yamlSetting);
        this.registerCommandExecutor("bladePlus", adminCommandHandle);
        this.registerCommandExecutor("bd", playerCommandHandle);
        this.registerTagExecutor("bladePlus", adminCommandHandle);
        this.registerTagExecutor("bd", playerCommandHandle);
        initAdminCommands(yamlSetting);
        initPlayerCommands(yamlSetting);
        registerEvents(yamlSetting);
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §a拔刀剑剑锻造系统加载成功!");
        printlnBladePlusLogInfo();
        modularManage.loadModular(new ReadConfiguration(new File(bladePlusMain.getDataFolder(), "modular")));
        BladePlusVersion.init(this.getServer());
    }


    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §a拔刀剑剑锻造系统已卸载!");
        printlnBladePlusLogInfo();
    }

    public void initFile(){
        languageFile = new BladeLanguageFile(this.getDataFolder(), "language.yml");
        guiSettingFile = new BladeGuiSettingFile(this.getDataFolder(), "guiSetting.yml");
        materialFile = new BladeMaterialFile(this.getDataFolder(), "material.yml");
        appraisalFile = new BladeAppraisalFile(this.getDataFolder(), "appraisal.yml");
        modelFile = new BladeModelFile(this.getDataFolder(),"model.yml");
        attackType = new BladeSpecialAttackType(this.getDataFolder(), "specialAttackType.yml");
        this.bladeSaveDefaultFile();
    }

    public void registerCommandExecutor(String command, CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

    public void registerTagExecutor(String command, TabExecutor tabExecutor) {
        this.getCommand(command).setTabCompleter(tabExecutor);
    }

    public void registerEvents(IYamlSetting yamlSetting) {
        Bukkit.getPluginManager().registerEvents(new BladePlusListener(yamlSetting), this);
        Bukkit.getPluginManager().registerEvents(new BladePlusUseBladeListener(yamlSetting), this);
        Bukkit.getPluginManager().registerEvents(new BladePlusUseSASEListener(yamlSetting), this);
    }

    public void initAdminCommands(IYamlSetting iYamlSetting) {
        adminCommandHandle.registerAdminCommandHandle(new ACommandAppraisal(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaterial(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaxRepairCounter(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandModel(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandProtect(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandHelp(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandReload(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandVersion(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandLucky(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBet(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSpecial(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBladeModel(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandKillCount(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandProudSoul(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandRepairCounter(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSpecialAttackType(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSummonedSwordColor(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBaseAttackModifier(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBanSA(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandRepair(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBanSE(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandModular(iYamlSetting));
    }

    public void initPlayerCommands(IYamlSetting iYamlSetting) {
        playerCommandHandle.registerCommandHandle(new PCommandAppraisal(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandOpen(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandHelp(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandSeeBladeMaxRepairCounter(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandVersion(iYamlSetting));
    }

    public <T extends AbstractPlayerCommand<? extends CommandSender>> void registerPlayerCommandHandle(T playerCommand) {
        if (playerCommand != null) {
            playerCommandHandle.registerCommandHandle(playerCommand);
        }
    }

    public <T extends AbstractAdminCommand<? extends CommandSender>> void registerAdminCommandHandle(T adminCommand) {
        if (adminCommand != null) {
            adminCommandHandle.registerAdminCommandHandle(adminCommand);
        }
    }

    public <T extends ModularCore> void registerPlayerCommandHelp(T modular, List<String> help) {
        if (help != null) {
            if (help.size() > 0) {
                PCommandHelp.registerHelp(modular.getCode(), help);
            }
        }
    }

    public <T extends ModularCore> void registerAdminCommandHelp(T modular, List<String> help) {
        if (help != null) {
            if (help.size() > 0) {
                ACommandHelp.registerHelp(modular.getCode(), help);
            }
        }
    }

    public void addPlayerCommandResult(String result) {
        this.playerCommandHandle.addResult(result);
    }

    public void addAdminCommandResult(String result) {
        this.adminCommandHandle.addResult(result);
    }


    public void bladeSaveDefaultFile(){
        languageFile.saveDefaultFile();
        guiSettingFile.saveDefaultFile();
        materialFile.saveDefaultFile();
        appraisalFile.saveDefaultFile();
        modelFile.saveDefaultFile();
        attackType.saveDefaultFile();
        this.saveDefaultConfig();
    }

    public static <T extends AbstractBladeFile> FileConfiguration getFileConfiguration(T languageFile){
        return languageFile.getFileConfiguration();
    }

    public void printlnBladePlusLogInfo() {
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §6作者：§d兔儿神");
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §6插件反馈群：§b978420514");
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §6如果你有好的意见请务必反馈给我！");
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §6此版本建议运行在1.7.10-1.12.2版本服务器上");
    }


    public <T extends Listener> void  registerModularListener(T listener){
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    public <T extends Listener> void unregisterModularListener(T listener){
        HandlerList.unregisterAll(listener);
    }

    public void logger(String msg){
        this.getServer().getLogger().info(msg);
    }

    public IYamlSetting getYamlSetting() {
        return this.yamlSetting;
    }

}
