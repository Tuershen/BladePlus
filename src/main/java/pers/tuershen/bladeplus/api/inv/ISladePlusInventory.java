package pers.tuershen.bladeplus.api.inv;

import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.ISladePlusRunTask;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public interface ISladePlusInventory extends IAttributeInventory, ISladePlusRunTask {

    void runStart(ItemStack material);


}
