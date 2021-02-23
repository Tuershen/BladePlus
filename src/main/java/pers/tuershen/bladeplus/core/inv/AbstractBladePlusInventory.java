package pers.tuershen.bladeplus.core.inv;

import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.inv.IAttributeInventory;
import pers.tuershen.bladeplus.core.common.BladePlusInventoryAttribute;
import pers.tuershen.bladeplus.core.common.BladePlusItem;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public abstract class AbstractBladePlusInventory implements InventoryHolder, IAttributeInventory {

    protected BladePlusInventoryAttribute plusInventoryAttribute;

    protected IYamlSetting iYamlSetting;

    public AbstractBladePlusInventory(IYamlSetting iYamlSetting){
        this.iYamlSetting = iYamlSetting;
    }


    protected ItemStack setDefaultItemStack() {
        BladePlusItem failItem =  plusInventoryAttribute.getFailItem();
        return new ItemStack(Material.valueOf(failItem.getType()), 1, failItem.getDur());
    }


}
