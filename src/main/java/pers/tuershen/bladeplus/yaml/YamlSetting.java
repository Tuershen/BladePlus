package pers.tuershen.bladeplus.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.*;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalMaterial;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;
import pers.tuershen.bladeplus.api.balde.IYamlMaterial;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.api.balde.IYamlSpecialAttackType;
import pers.tuershen.bladeplus.api.check.IYamlBanUseWorld;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.entity.ForgingUi;
import pers.tuershen.bladeplus.util.ItemUtil;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public class YamlSetting implements IYamlSetting {

    private YamlMaterial yamlMaterial;

    private YamlMessages yamlMessages;

    private YamlModel yamlModel;

    private YamlAppraisalStone yamlAppraisalStone;

    private YamlAppraisalMaterial appraisalMaterial;

    private YamlBanUseWorld banUseWorld;

    private YamlSlashBlade slashBlade;

    private YamlInventorySetting yamlInventorySetting;

    private YamlSpecialAttackType yamlSpecialAttackType;

    private String title;

    private int maxRepairCounter;

    private ForgingUi forgingUi;

    private ForgingUi forgingFail;

    private YamlReset yamlReset;

    public YamlSetting() {
        yamlMaterial = new YamlMaterial();
        yamlMessages = new YamlMessages();
        yamlModel = new YamlModel();
        yamlAppraisalStone = new YamlAppraisalStone();
        appraisalMaterial = new YamlAppraisalMaterial();
        banUseWorld = new YamlBanUseWorld();
        slashBlade = new YamlSlashBlade();
        yamlInventorySetting = new YamlInventorySetting();
        yamlReset = new YamlReset();
        yamlSpecialAttackType = new YamlSpecialAttackType();
    }

    @Override
    public void init() {
        FileConfiguration fileConfiguration = BladePlusMain.bladePlusMain.getConfig();
        this.maxRepairCounter = fileConfiguration.getInt("maxRepairCounter");
        this.forgingUi = ItemUtil.convertForgingUi(fileConfiguration.getString("forgingUi"));
        this.forgingFail = ItemUtil.convertForgingUi(fileConfiguration.getString("forgingFail"));;
        this.title = fileConfiguration.getString("title").replace('&','§');
        yamlReset.init();
    }

    @Override
    public void reload() {
        BladePlusMain.bladePlusMain.saveDefaultConfig();
        BladePlusMain.bladePlusMain.reloadConfig();
        yamlReset.reload();
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public int getMaxRepairCounter() { return this.maxRepairCounter; }

    @Override
    public ForgingUi getForgingUi() {
        return this.forgingUi;
    }

    @Override
    public ForgingUi getForgingFail() {
        return this.forgingFail;
    }

    @Override
    public IYamlGuiSetting getIYamlGuiSetting(){
        return this.yamlInventorySetting;
    }

    @Override
    public IYamlAppraisalMaterial getIAppraisalMaterial() {
        return this.appraisalMaterial;
    }

    @Override
    public IYamlAppraisalStone getIYamlAppraisalStone() {
        return this.yamlAppraisalStone;
    }

    @Override
    public IYamlSlashBlade getIYamlSlashBlade() {
        return this.slashBlade;
    }

    @Override
    public IYamlBanUseWorld getIYamlBanUseWorld() {
        return this.banUseWorld;
    }

    @Override
    public IYamlMaterial getIYamlMaterial() {
        return this.yamlMaterial;
    }

    @Override
    public IYamlModel getIYamlModel() {
        return this.yamlModel;
    }

    @Override
    public IYamlMsg getIYamlMsg() {
        return this.yamlMessages;
    }

    @Override
    public IYamlSpecialAttackType getIYamlSpecialAttackType() {
        return this.yamlSpecialAttackType;
    }


}
