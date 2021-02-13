package pers.tuershen.bladeplus.api.inv;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public interface ISladePlusInventory extends IAttributeInventory {

    void runStart(ItemStack material);

    Inventory getInventory();

}
