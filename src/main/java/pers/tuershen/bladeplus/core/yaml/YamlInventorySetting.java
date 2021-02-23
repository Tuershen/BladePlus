package pers.tuershen.bladeplus.core.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.core.common.*;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class YamlInventorySetting extends YamlReset implements IYamlGuiSetting {


    private BladePlusInventoryAttribute bladePlusInventoryAttribute;

    private BladePlusInventoryAttribute bladePlusAppraisalInventoryAttribute;

    private BladeSpeedOfProgressAttribute bladeSpeedOfProgressAttribute;

    private BladePlusAppraisalDisplay bladePlusAppraisalDisplay;


    public YamlInventorySetting() {
        registerYamlReset(this);
    }


    public void init() {
        FileConfiguration fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.guiSettingFile);
        initBladePlusInventoryAttribute(fileConfiguration);
        initAppraisalInventoryAttribute(fileConfiguration);
        initBladeSpeedOfProgressAttribute(fileConfiguration);
        initBladePlusAppraisalDisplay(fileConfiguration);
    }


    public void initBladePlusInventoryAttribute(FileConfiguration fileConfiguration) {
        bladePlusInventoryAttribute = new BladePlusInventoryAttribute();
        bladePlusInventoryAttribute.setTitle(fileConfiguration.getString("blade.title"));
        bladePlusInventoryAttribute.setFirstItem(new BladePlusItem(
                fileConfiguration.getString("blade.first.material"),
                (short) fileConfiguration.getInt("blade.first.mate")));
        bladePlusInventoryAttribute.setFailItem(new BladePlusItem(
                fileConfiguration.getString("blade.fail.material"),
                (short) fileConfiguration.getInt("blade.fail.mate")));
        bladePlusInventoryAttribute.setButton(new BladePlusButton()
                .setSlot(fileConfiguration.getInt("blade.button.slot"))
                .setMaterial(fileConfiguration.getString("blade.button.material"))
                .setDisplay(fileConfiguration.getString("blade.button.display"))
                .setLore(fileConfiguration.getStringList("blade.button.lore"))
                .setMate((short) fileConfiguration.getInt("blade.button.mate")));
        bladePlusInventoryAttribute.setGemstoneSlot(fileConfiguration.getInt("blade.gemstone.slot"));
        bladePlusInventoryAttribute.setMaterialSlot(fileConfiguration.getInt("blade.material.slot"));
    }


    public void initAppraisalInventoryAttribute(FileConfiguration fileConfiguration) {
        bladePlusAppraisalInventoryAttribute = new BladePlusInventoryAttribute();
        bladePlusAppraisalInventoryAttribute.setTitle(fileConfiguration.getString("appraisal.title"));
        bladePlusAppraisalInventoryAttribute.setFirstItem(new BladePlusItem(
                fileConfiguration.getString("appraisal.first.material"),
                (short) fileConfiguration.getInt("appraisal.first.mate")));
        bladePlusAppraisalInventoryAttribute.setFailItem(new BladePlusItem(
                fileConfiguration.getString("appraisal.fail.material"),
                (short) fileConfiguration.getInt("appraisal.fail.mate")));
        bladePlusAppraisalInventoryAttribute.setButton(new BladePlusButton()
                .setSlot(fileConfiguration.getInt("appraisal.button.slot"))
                .setMaterial(fileConfiguration.getString("appraisal.button.material"))
                .setDisplay(fileConfiguration.getString("appraisal.button.display"))
                .setLore(fileConfiguration.getStringList("appraisal.button.lore"))
                .setMate((short) fileConfiguration.getInt("appraisal.button.mate")));
        bladePlusAppraisalInventoryAttribute.setGemstoneSlot(fileConfiguration.getInt("appraisal.gemstone.slot"));
        bladePlusAppraisalInventoryAttribute.setMaterialSlot(fileConfiguration.getInt("appraisal.material.slot"));
    }

    public void initBladeSpeedOfProgressAttribute(FileConfiguration fileConfiguration) {
        bladeSpeedOfProgressAttribute = new BladeSpeedOfProgressAttribute();
        bladeSpeedOfProgressAttribute
                .setMaterial(fileConfiguration.getString("speedOfProgress.material"))
                .setDisplay(fileConfiguration.getString("speedOfProgress.display"))
                .setMate((short) fileConfiguration.getInt("speedOfProgress.mate"));
    }

    public void initBladePlusAppraisalDisplay(FileConfiguration fileConfiguration){
        this.bladePlusAppraisalDisplay = new BladePlusAppraisalDisplay(
                fileConfiguration.getString("appraisalDisplay.display"),
                fileConfiguration.getStringList("appraisalDisplay.lore")
        );

    }



    public void reload() {
        BladePlusMain.guiSettingFile.reloadConfig();
        this.init();
    }


    @Override
    public BladePlusInventoryAttribute getBladePlusInventoryAttribute() {
        return this.bladePlusInventoryAttribute;
    }

    @Override
    public BladePlusInventoryAttribute getBladePlusAppraisalInventoryAttribute() {
        return this.bladePlusAppraisalInventoryAttribute;
    }

    @Override
    public BladeSpeedOfProgressAttribute getBladeSpeedOfProgressAttribute() {
        return this.bladeSpeedOfProgressAttribute;
    }

    @Override
    public BladePlusAppraisalDisplay getBladePlusAppraisalDisplay() {
        return this.bladePlusAppraisalDisplay;
    }


}
