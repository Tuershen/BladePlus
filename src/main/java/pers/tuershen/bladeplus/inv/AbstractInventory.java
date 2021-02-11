package pers.tuershen.bladeplus.inv;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.bladeplus.BladePlusMain;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public abstract class AbstractInventory {

    //页面集合
    protected List<Inventory> inventoryList = new ArrayList<>();

    //页面数量
    protected int page;

    //当前页面下标
    protected int nowPage;

    //
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


    public int getPage(){
        return this.nowPage;
    }


    public static Inventory setDefaultInventory(InventoryHolder inventoryHolder){
        Inventory inventory = BladePlusMain.bladePlusMain.getServer().createInventory(inventoryHolder, 54, "皮肤库");
        for (int i = 45; i < 54 ; i++) {
            inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        inventory.setItem(48, thePrevious());
        inventory.setItem(50, next());
        return inventory;
    }

    public static ItemStack next() {
        ItemStack itemStack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§b下一页");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack thePrevious() {
        ItemStack itemStack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§b上一页");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
