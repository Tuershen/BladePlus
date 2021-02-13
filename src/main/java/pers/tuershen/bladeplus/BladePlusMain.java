package pers.tuershen.bladeplus;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.command.AdminCommandHandle;
import pers.tuershen.bladeplus.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.command.admin.*;
import pers.tuershen.bladeplus.command.player.PCommandAppraisal;
import pers.tuershen.bladeplus.command.player.PCommandHelp;
import pers.tuershen.bladeplus.command.player.PCommandOpen;
import pers.tuershen.bladeplus.command.player.PCommandSeeBladeMaxRepairCounter;
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
        initAdminCommands(adminCommandHandle, yamlSetting);
        initPlayerCommands(playerCommandHandle, yamlSetting);
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


    public void initPlayerCommands(PlayerCommandHandle playerCommandHandle, IYamlSetting iYamlSetting){
        playerCommandHandle.registerCommandHandle(new PCommandAppraisal(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandOpen(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandHelp(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandSeeBladeMaxRepairCounter(iYamlSetting));

    }


    public void initAdminCommands(AdminCommandHandle adminCommandHandle, IYamlSetting iYamlSetting) {
        adminCommandHandle.registerAdminCommandHandle(new ACommandAppraisal(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaterial(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaxRepairRepairCounter(iYamlSetting));
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

    }



}
