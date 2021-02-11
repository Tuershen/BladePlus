package pers.tuershen.bladeplus.inv;


import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalInventory implements InventoryHolder {

    private Inventory AppraisalInventory;

    private IYamlMsg iYamlMsg;

    private IYamlAppraisalStone appraisalStone;

    public AppraisalInventory(IYamlMsg iYamlMsg, IYamlAppraisalStone appraisalStone) {
        this.AppraisalInventory = setDefaultAppraisal(this);
        this.iYamlMsg = iYamlMsg;
        this.appraisalStone = appraisalStone;
    }

    public static Inventory setDefaultAppraisal(InventoryHolder inventoryHolder) {
        Inventory appraisal = BladePlusMain.bladePlusMain.getServer().createInventory(inventoryHolder, 18, "强化石鉴定");
        for (int i = 0; i < 18; i++) {
            if (i >= 9) {
                appraisal.setItem(i, setAppraisal(Material.FIRE, (short) 0, "§5虚火本源"));
                continue;
            }
            appraisal.setItem(i, setAppraisal(Material.STAINED_GLASS_PANE, (short) 15, "§3左边放入鉴定石§7——§a右边放入鉴定材料"));
        }
        appraisal.setItem(2, new ItemStack(Material.AIR));
        appraisal.setItem(4, setAppraisal(Material.ANVIL, (short) 0, "§7-§e点击鉴定§7-"));
        appraisal.setItem(6, new ItemStack(Material.AIR));
        return appraisal;
    }


    public void appraisalMaterial(Player player) {
        ItemStack appraisalMaterial = this.AppraisalInventory.getItem(2);
        if (appraisalMaterial != null) {
            //是否是鉴定石
            if (NBTLookup.hasAppraisalMaterial(appraisalMaterial)) {
                ItemStack appraisalItem = this.AppraisalInventory.getItem(6);
                if (appraisalItem != null) {
                    if (appraisalItem.getType() != Material.AIR) {
                        if (this.appraisalStone.hasAppraisalStone(appraisalItem.getType().name())) {
                            if (appraisalItem.getAmount() == 1) {
                                appraisalHandle(appraisalMaterial, appraisalItem);
                                player.sendMessage(this.iYamlMsg.getMsg("error_17"));
                                return;
                            }
                            player.sendMessage(this.iYamlMsg.getMsg("error_16"));
                            return;
                        }
                        player.sendMessage
                                (this.iYamlMsg.getMsg("error_15"));
                        return;
                    }
                    player.sendMessage(this.iYamlMsg.getMsg("error_15"));
                    return;
                }
                player.sendMessage(this.iYamlMsg.getMsg("error_14"));
                return;
            }
            player.sendMessage(this.iYamlMsg.getMsg("error_13"));
            return;
        }
        player.sendMessage(this.iYamlMsg.getMsg("error_12"));
    }


    public void appraisalHandle(ItemStack appraisalMaterial, ItemStack appraisalItem) {
        ItemStack itemStack = NBTWrite.appraisalToMaterial(appraisalMaterial, appraisalItem);
        if (appraisalMaterial.getAmount() == 1) {
            this.AppraisalInventory.setItem(2, new ItemStack(Material.AIR));
            this.AppraisalInventory.setItem(6, itemStack);
            return;
        }
        appraisalMaterial.setAmount(appraisalMaterial.getAmount() - 1);
        this.AppraisalInventory.setItem(2, appraisalMaterial);
        this.AppraisalInventory.setItem(6, itemStack);
    }

    public static ItemStack setAppraisal(Material material, short dur, String display) {
        ItemStack itemStack = new ItemStack(material, 1, dur);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        return AppraisalInventory;
    }
}
