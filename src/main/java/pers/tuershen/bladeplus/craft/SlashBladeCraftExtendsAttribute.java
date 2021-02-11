package pers.tuershen.bladeplus.craft;

import org.bukkit.inventory.ItemStack;

/**
 * @auther Tuershen Create Date on 2021/2/9
 *
 * 拔刀合成继承forging属性
 * 属性包括皮肤，最大锻造值
 *
 */
public class SlashBladeCraftExtendsAttribute {


    public boolean hasModel() {

        return false;
    }

    public boolean hasMaxRepairCount() {
        return false;
    }


    /**
     * 属性继承
     * @return 返回继承属性后的拔刀剑
     */
    public ItemStack extendsForgingAttribute() {

        return null;
    }







}
