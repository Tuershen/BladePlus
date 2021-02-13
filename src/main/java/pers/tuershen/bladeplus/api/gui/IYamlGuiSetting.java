package pers.tuershen.bladeplus.api.gui;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.common.BladePlusAppraisalDisplay;
import pers.tuershen.bladeplus.common.BladePlusInventoryAttribute;
import pers.tuershen.bladeplus.common.BladeSpeedOfProgressAttribute;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public interface IYamlGuiSetting extends IYamlReset {


    BladePlusInventoryAttribute getBladePlusInventoryAttribute();

    BladePlusInventoryAttribute getBladePlusAppraisalInventoryAttribute();

    BladeSpeedOfProgressAttribute getBladeSpeedOfProgressAttribute();

    BladePlusAppraisalDisplay getBladePlusAppraisalDisplay();

}
