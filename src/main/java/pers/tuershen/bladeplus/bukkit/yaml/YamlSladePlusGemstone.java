package pers.tuershen.bladeplus.bukkit.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSladePlusGemstone;
import pers.tuershen.bladeplus.api.gemstone.*;
import pers.tuershen.bladeplus.bukkit.common.gemstone.*;
import pers.tuershen.bladeplus.bukkit.common.gemstone.SpecialGemstone;
import pers.tuershen.bladeplus.bukkit.core.common.gemstone.*;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class YamlSladePlusGemstone extends YamlReset implements IYamlSladePlusGemstone {

    private BetGemstone betGemstone;

    private LuckyGemstone luckyGemstone;

    private ProtectGemstone protectGemstone;

    private RepairGemstone repairGemstone;

    private SpecialGemstone specialGemstone;

    public YamlSladePlusGemstone(){
        registerYamlReset(this);
    }

    @Override
    public void init() {
        FileConfiguration fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.guiSettingFile);
        initBet(fileConfiguration);
        initLucky(fileConfiguration);
        initProtect(fileConfiguration);
        initRepair(fileConfiguration);
        initSpecial(fileConfiguration);

    }

    @Override
    public void reload() {
        BladePlusMain.guiSettingFile.reloadConfig();
        this.init();
    }

    public void initBet(FileConfiguration configuration) {

        betGemstone = new BetGemstone(
                configuration.getString("bet.display"),
                configuration.getStringList("bet.lore"),
                configuration.getStringList("bet.successMsg"),
                configuration.getStringList("bet.failMsg")
        );

    }

    public void initLucky(FileConfiguration configuration) {

        this.luckyGemstone = new LuckyGemstone(
                configuration.getString("lucky.display"),
                configuration.getStringList("lucky.lore"),
                configuration.getStringList("lucky.successMsg")
        );

    }

    public void initProtect(FileConfiguration configuration) {

        this.protectGemstone = new ProtectGemstone(
                configuration.getString("protect.display"),
                configuration.getStringList("protect.lore"),
                configuration.getStringList("protect.successMsg")
        );

    }

    public void initRepair(FileConfiguration configuration) {
        this.repairGemstone = new RepairGemstone(
                configuration.getString("repair.display"),
                configuration.getStringList("repair.lore"),
                configuration.getStringList("repair.successMsg"),
                configuration.getStringList("repair.failMsg")
        );
    }

    public void initSpecial(FileConfiguration configuration) {

        this.specialGemstone = new SpecialGemstone(
                configuration.getString("special.display"),
                configuration.getStringList("special.lore"),
                configuration.getStringList("special.successMsg"))
                .setFailMsg(configuration.getStringList("special.failMsg"))
                .setBaseAttackModifierMsg(configuration.getString("special.baseAttackModifier"))
                .setKillCountMsg(configuration.getString("special.killCount"))
                .setProudSoulMsg(configuration.getString("special.proudSoul"))
                .setRepairCounter(configuration.getString("special.repairCounter"))
                .setSummonedSwordColorMsg(configuration.getString("special.summonedSwordColor"));

    }


    @Override
    public IBetGemstone getIBetGemstone() {
        return this.betGemstone;
    }

    @Override
    public ILuckyGemstone getILuckyGemstone() {
        return this.luckyGemstone;
    }

    @Override
    public IProtectGemstone getIProtectGemstone() {
        return this.protectGemstone;
    }

    @Override
    public IRepairGemstone getIRepairGemstone() {
        return this.repairGemstone;
    }

    @Override
    public ISpecialGemstone getISpecialGemstone() {
        return this.specialGemstone;
    }

}
