package pers.tuershen.bladeplus.core.inv;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public abstract class AbstractInventory {

    protected List<Inventory> inventoryList = new ArrayList<>();

    protected int page;

    protected int nowPage;

    protected Inventory inventory;


    /**
     * 上一页
     * @return
     */
    public Inventory thePreviousPage() {
        if ((this.nowPage - 1) < 0){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(--this.nowPage);
    }

    /**
     * 下一页
     * @return 界面
     */
    public Inventory nextPage() {
        if ((this.nowPage + 1) >= this.inventoryList.size()){
            return this.inventoryList.get(this.nowPage);
        }
        return this.inventoryList.get(++this.nowPage);
    }

    public static Inventory setDefaultInventory(InventoryHolder inventoryHolder){
        Inventory inventory = BladePlusMain.bladePlusMain.getServer().createInventory(inventoryHolder, 54, "皮肤库");
        for (int i = 45; i < 54 ; i++) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        inventory.setItem(48, ItemUtil.createItem("§b上一页"));
        inventory.setItem(50, ItemUtil.createItem("§b下一页"));
        return inventory;
    }




}
