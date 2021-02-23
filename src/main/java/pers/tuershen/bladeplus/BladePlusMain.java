package pers.tuershen.bladeplus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.core.command.AdminCommandHandle;
import pers.tuershen.bladeplus.core.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.core.command.admin.*;
import pers.tuershen.bladeplus.core.command.player.*;
import pers.tuershen.bladeplus.core.file.*;
import pers.tuershen.bladeplus.core.listener.BladePlusListener;
import pers.tuershen.bladeplus.core.listener.BladePlusUseBladeListener;
import pers.tuershen.bladeplus.core.listener.BladePlusUseSASEListener;
import pers.tuershen.bladeplus.core.yaml.YamlSetting;
import com.tuershen.nbtlibrary.CompoundLibraryManager;
import com.tuershen.nbtlibrary.api.CompoundLibraryApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



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


    @Override
    public void onEnable() {
        //初始化实例
        bladePlusMain = this;
        libraryApi = CompoundLibraryManager.getPluginManager(this);
        initFile();
        YamlSetting yamlSetting = new YamlSetting();
        yamlSetting.init();
        yamlSlashBlade = yamlSetting.getIYamlSlashBlade();
        AdminCommandHandle<?> adminCommandHandle = new AdminCommandHandle<>(yamlSetting);
        PlayerCommandHandle<?> playerCommandHandle = new PlayerCommandHandle<>(yamlSetting);
        this.registerCommandExecutor("bladePlus", adminCommandHandle);
        this.registerCommandExecutor("bd", playerCommandHandle);
        this.registerTagExecutor("bladePlus", adminCommandHandle);
        this.registerTagExecutor("bd", playerCommandHandle);
        BladePlusRegister.initAdminCommands(adminCommandHandle, yamlSetting);
        BladePlusRegister.initPlayerCommands(playerCommandHandle, yamlSetting);
        registerEvents(yamlSetting);
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §a拔刀剑剑锻造系统加载成功!");
        printlnBladePlusLogInfo();
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
        Bukkit.getConsoleSender().sendMessage("§7[§3BladePlus§7] §6此版本建议运行在1.7.10版本服务器上");
    }




}
