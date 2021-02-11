package pers.tuershen.bladeplus.api;

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

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public interface IYamlSetting extends IYamlReset {

    String title();

    int getMaxRepairCounter();

    ForgingUi getForgingUi();

    ForgingUi getForgingFail();

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
     * @return
     */
    IYamlSlashBlade getIYamlSlashBlade();

    /**
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
     * @return
     */
    IYamlModel getIYamlModel();

    /**
     *
     * @return
     */
    IYamlMsg getIYamlMsg();

    /**
     *
     * @return
     */
    IYamlSpecialAttackType getIYamlSpecialAttackType();

}
