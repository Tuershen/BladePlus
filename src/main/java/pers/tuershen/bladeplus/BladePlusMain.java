package pers.tuershen.bladeplus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.command.AdminCommandHandle;
import pers.tuershen.bladeplus.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.file.*;
import pers.tuershen.bladeplus.listener.BladePlusListener;
import pers.tuershen.bladeplus.listener.BladePlusUseBladeListener;
import pers.tuershen.bladeplus.listener.BladePlusUseSAListener;
import pers.tuershen.bladeplus.yaml.YamlSetting;
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
        languageFile = new BladeLanguageFile(this.getDataFolder(), "language.yml");
        guiSettingFile = new BladeGuiSettingFile(this.getDataFolder(), "guiSetting.yml");
        materialFile = new BladeMaterialFile(this.getDataFolder(), "material.yml");
        appraisalFile = new BladeAppraisalFile(this.getDataFolder(), "appraisal.yml");
        modelFile = new BladeModelFile(this.getDataFolder(),"model.yml");
        attackType = new BladeSpecialAttackType(this.getDataFolder(), "specialAttackType.yml");
        this.bladeSaveDefaultFile();
        YamlSetting yamlSetting = new YamlSetting();
        yamlSetting.init();
        yamlSlashBlade = yamlSetting.getIYamlSlashBlade();
        AdminCommandHandle adminCommandHandle = new AdminCommandHandle(yamlSetting);
        PlayerCommandHandle playerCommandHandle = new PlayerCommandHandle(yamlSetting);
        this.registerCommandExecutor("bladePlus", adminCommandHandle);
        this.registerCommandExecutor("bd", playerCommandHandle);
        this.registerTagExecutor("bladePlus", adminCommandHandle);
        this.registerTagExecutor("bd", playerCommandHandle);
        this.registerCommands(yamlSetting);
        Bukkit.getPluginManager().registerEvents(new BladePlusListener(yamlSetting), this);
        Bukkit.getPluginManager().registerEvents(new BladePlusUseBladeListener(yamlSetting), this);
        Bukkit.getPluginManager().registerEvents(new BladePlusUseSAListener(yamlSetting), this);
        getLogger().info("[BladePlus] 拔刀剑剑锻造系统加载成功!");
    }


    @Override
    public void onDisable() {

        getLogger().info("已卸载!");
    }

    public void registerCommandExecutor(String command, CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

    public void registerTagExecutor(String command, TabExecutor tabExecutor) {
        this.getCommand(command).setTabCompleter(tabExecutor);
    }

    public void registerCommands(IYamlSetting setting) {
        BladePlusInitialization.initAdminCommands(setting);
        BladePlusInitialization.initPlayerCommands(setting);
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



}
