package pers.tuershen.bladeplus.api.gui;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.entity.ForgingGuiSetting;
import org.bukkit.inventory.ItemStack;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public interface IYamlGuiSetting extends IYamlReset {

    /**
     * 获取强化石的槽位
     * @return 强化石的槽位
     */
    int getMaterialSlot();

    /**
     * 获取强化按钮的信息
     * 包括id，子id，显示位置， display标签
     * @return 强化按钮
     */
    ForgingGuiSetting getForgingGuiSetting();

    /**
     * 获取保护石，幸运石等的显示位置
     * @return 显示位置
     */
    int getGemstoneSlot();


    /**
     * %SpeedOfProgress%
     * %time%
     * @return 强化进度
     */
    String getSpeedOfProgress();


    /**
     * 进度条材质
     * @return
     */
    ItemStack getSpeedOfProgressMaterial();


}
