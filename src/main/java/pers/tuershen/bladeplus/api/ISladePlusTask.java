package pers.tuershen.bladeplus.api;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import org.bukkit.Location;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.bukkit.common.BladePlusHandle;
import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/23
 */
public interface ISladePlusTask extends ISladePlusRunTask {

    /**
     * 拔刀实体位置
     * @return
     */
    Location getBladeEntityLocation();

    /**
     * 拔刀本体
     * 包括NBT接口
     * @return
     */
    EntityNBTTagCompoundApi getBladeEntity();

    /**
     * 强化拔刀的玩家
     * @return
     */
    String getPlayerDisPlay();

    /**
     * 强化界面设置
     * @return
     */
    IYamlGuiSetting getIYamlGuiSetting();

    /**
     *
     * @param plusHandle
     * @param resultType
     * @return
     */
    boolean handleDistribution(BladePlusHandle plusHandle, ResultTypeEnum resultType);


    void caneWorld(String name);
}
