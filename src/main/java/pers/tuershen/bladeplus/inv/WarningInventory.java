package pers.tuershen.bladeplus.inv;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pers.tuershen.bladeplus.BladePlusMain;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class WarningInventory implements InventoryHolder {

    private Inventory warning;

    public WarningInventory(String title){
        this.warning = BladePlusMain.bladePlusMain.getServer().createInventory(null, 9, title);
    }

    @Override
    public Inventory getInventory() {
        return this.warning;
    }
}
