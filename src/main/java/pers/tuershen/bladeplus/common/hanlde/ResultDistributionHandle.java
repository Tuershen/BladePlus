package pers.tuershen.bladeplus.common.hanlde;

import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.entity.BladePlusHandle;
import pers.tuershen.bladeplus.type.GemstoneTypeEnum;
import pers.tuershen.bladeplus.type.ResultTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ResultDistributionHandle extends ResultHandle {

    public ResultDistributionHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        super(plusHandle, setting, player);
    }

    public void dispatchDefault(ResultTypeEnum resultType) {
        if (resultType == ResultTypeEnum.SUCCESS) {
            SuccessHandle successHandle = new SuccessHandle(this.plusHandle, setting, player);
            successHandle.handleEvent();
        } else {
            FailHandle failHandle = new FailHandle(this.plusHandle, setting, player);
            failHandle.handleEvent();
        }
    }

    public void dispatchGemstone(ResultTypeEnum resultType, GemstoneTypeEnum gemstoneTypeEnum) {
        if (resultType == ResultTypeEnum.SUCCESS) {
            SuccessHandle successHandle = new SuccessHandle(this.plusHandle, setting, player);
            successHandle.handleGemstoneEvent(gemstoneTypeEnum);
        } else {
            FailHandle failHandle = new FailHandle(this.plusHandle, setting, player);
            failHandle.handleGemstoneEvent(gemstoneTypeEnum);
        }
    }


}
