package pers.tuershen.bladeplus.core.inv;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.common.BladePlusItem;

/**
 * @auther Tuershen Create Date on 2021/2/9
 * 警告界面
 */
public class WarningInventory implements InventoryHolder {

    private Inventory warning;

    public WarningInventory(String title, IYamlSetting iYamlSetting){
        BladePlusItem firstItem = iYamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute().getFirstItem();
        ItemStack first = new ItemStack(Material.valueOf(firstItem.getType()), 1, firstItem.getDur());
        this.warning = BladePlusMain.bladePlusMain.getServer().createInventory(this, 9, title);
        for (int i = 0; i < 9; i++)
            this.warning.setItem(i, first);
    }

    @Override
    public Inventory getInventory() {
        return this.warning;
    }
}
