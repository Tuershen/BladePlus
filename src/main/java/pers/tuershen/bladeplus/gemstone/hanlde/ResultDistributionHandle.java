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
        System.out.println("强化结果11"+ resultType.name());
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

    public void dispatchGemstone(ResultTypeEnum resultType, GemstoneTypeEnum gemstoneTypeEnum) {

        System.out.println("强化结果"+ resultType.name());


        if (resultType == ResultTypeEnum.SUCCESS) {
            sendSuccessMsg();
            SuccessHandle successHandle = new SuccessHandle(this.plusHandle, setting, player);
            successHandle.handleGemstoneEvent(gemstoneTypeEnum);
        } else {
            sendFailMsg();
            FailHandle failHandle = new FailHandle(this.plusHandle, setting, player);
            failHandle.handleGemstoneEvent(gemstoneTypeEnum);
        }
    }

    private void sendSuccessMsg(){
        if (this.player != null) {
            player.sendMessage(setting.getIYamlMsg().getMsg("error_3"));
        }
    }

    private void sendFailMsg(){
        if (this.player != null) {
            player.sendMessage(setting.getIYamlMsg().getMsg("error_5"));
        }
    }



}