package pers.tuershen.bladeplus.bukkit.gemstone.hanlde;

import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.common.BladePlusHandle;
import pers.tuershen.bladeplus.bukkit.check.BlandStandCheck;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ResultHandle   {

    protected BladePlusHandle plusHandle;

    protected IYamlSetting setting;

    protected Player player;

    public ResultHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        this.plusHandle = plusHandle;
        this.setting = setting;
        this.player = player;
    }

    public boolean baseCheckHandle() {
        BlandStandCheck blandStandCheck = new BlandStandCheck(this.plusHandle);
        return blandStandCheck.basicCheck();
    }


}
