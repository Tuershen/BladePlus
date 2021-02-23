package pers.tuershen.bladeplus.api;

import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalMaterial;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;
import pers.tuershen.bladeplus.api.balde.IYamlBladeSkillType;
import pers.tuershen.bladeplus.api.balde.IYamlMaterial;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;
import pers.tuershen.bladeplus.api.check.IYamlBanUseWorld;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public interface IYamlSetting extends IYamlReset {

    int getMaxRepairCounter();

    /**
     * 强化界面，鉴定界面...等设置
     *
     * @return
     */
    IYamlGuiSetting getIYamlGuiSetting();


    /**
     * 鉴定材料
     *
     * @return
     */
    IYamlAppraisalMaterial getIAppraisalMaterial();

    /**
     * 鉴定方案
     *
     * @return
     */
    IYamlAppraisalStone getIYamlAppraisalStone();

    /**
     * 拔刀识别
     * @return
     */
    IYamlSlashBlade getIYamlSlashBlade();

    /**
     * 禁止使用的世界
     * @return
     */
    IYamlBanUseWorld getIYamlBanUseWorld();


    /**
     * 强化材料
     *
     * @return
     */
    IYamlMaterial getIYamlMaterial();


    /**
     * 拔刀皮肤库
     * @return
     */
    IYamlModel getIYamlModel();

    /**
     * 提示语言
     * @return
     */
    IYamlMsg getIYamlMsg();

    /**
     * 拔刀禁止使用的技能
     * @return
     */
    IYamlBladeSkillType getIYamlSpecialAttackType();


    /**
     * 各种宝石
     * @return
     */
    IYamlSladePlusGemstone getIYamlSladePlusGemstone();

}
