package pers.tuershen.bladeplus.gemstone.hanlde;

import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.common.BladePlusHandle;
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
            sendSuccessMsg();
            SuccessHandle successHandle = new SuccessHandle(this.plusHandle, setting, player);
            successHandle.handleEvent();
        } else {
            sendFailMsg();
            FailHandle failHandle = new FailHandle(this.plusHandle, setting, player);
            failHandle.handleEvent();
        }
    }

    public int dispatchGemstone(ResultTypeEnum resultType, GemstoneTypeEnum gemstoneTypeEnum) {
        if (resultType == ResultTypeEnum.SUCCESS) {
            sendSuccessMsg();
            SuccessHandle successHandle = new SuccessHandle(this.plusHandle, setting, player);
            successHandle.handleGemstoneEvent(gemstoneTypeEnum);
            return gemstoneTypeEnum.consumeCount(ResultTypeEnum.SUCCESS);
        } else {
            sendFailMsg();
            FailHandle failHandle = new FailHandle(this.plusHandle, setting, player);
            failHandle.handleGemstoneEvent(gemstoneTypeEnum);
            return gemstoneTypeEnum.consumeCount(ResultTypeEnum.FAIL);
        }
    }

    private void sendSuccessMsg(){
        if (this.player != null) {
            player.sendMessage(setting.getIYamlMsg().getMsg("error_3")
            .replace("%repairCounter%", String.valueOf(this.plusHandle.getBladePlusMaterial().getRepairCounter()))
            .replace("%proudSoul%", String.valueOf(this.plusHandle.getBladePlusMaterial().getProudSoul()))
            );
        }
    }

    private void sendFailMsg(){
        if (this.player != null) {
            player.sendMessage(setting.getIYamlMsg().getMsg("error_5"));
        }
    }



}
