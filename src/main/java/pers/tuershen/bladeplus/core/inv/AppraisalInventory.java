package pers.tuershen.bladeplus.core.inv;


import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;
import pers.tuershen.bladeplus.api.inv.IAppraisalInventory;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.core.common.BladePlusItem;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.util.ItemUtil;

/**
 * @auther Tuershen Create Date on 2021/1/8
 * 强化材料鉴定界面
 */
public class AppraisalInventory extends AbstractBladePlusInventory implements IAppraisalInventory {

    private Inventory AppraisalInventory;

    private IYamlMsg iYamlMsg;

    private IYamlAppraisalStone appraisalStone;

    public AppraisalInventory(IYamlSetting setting) {
        super(setting);
        this.iYamlMsg = setting.getIYamlMsg();
        this.appraisalStone = setting.getIYamlAppraisalStone();
        this.plusInventoryAttribute = setting.getIYamlGuiSetting().getBladePlusAppraisalInventoryAttribute();
        this.AppraisalInventory = setDefaultAppraisal(this);
    }

    public Inventory setDefaultAppraisal(InventoryHolder inventoryHolder) {
        Inventory appraisal = BladePlusMain.bladePlusMain.getServer().createInventory(inventoryHolder, 9, plusInventoryAttribute.getTitle());
        for (int i = 1; i < 9; i++) {
            appraisal.setItem(i, setDefaultItemStack());
        }
        BladePlusItem firstItem = plusInventoryAttribute.getFirstItem();
        ItemStack first = new ItemStack(Material.valueOf(firstItem.getType()), 1, firstItem.getDur());
        appraisal.setItem(0, first);
        appraisal.setItem(plusInventoryAttribute.getGemstoneSlot(), new ItemStack(Material.AIR));
        appraisal.setItem(plusInventoryAttribute.getButton().getSlot(), ItemUtil.convertItemStack(plusInventoryAttribute.getButton()));
        appraisal.setItem(plusInventoryAttribute.getMaterialSlot(), new ItemStack(Material.AIR));
        return appraisal;
    }

    public boolean checkAppraisalGemstone(Player player) {
        ItemStack gemstone = this.AppraisalInventory.getItem(plusInventoryAttribute.getGemstoneSlot());
        if (gemstone != null && gemstone.getType() != Material.AIR) {
            if (NBTLookup.hasAppraisalMaterial(gemstone)) {
                ItemStack material = this.AppraisalInventory.getItem(plusInventoryAttribute.getMaterialSlot());
                return checkAppraisalMaterial(player, gemstone, material);
            }
            player.sendMessage(this.iYamlMsg.getMsg("error_13"));
            return false;
        }
        player.sendMessage(this.iYamlMsg.getMsg("error_12"));
        return false;
    }

    private boolean checkAppraisalMaterial(Player player, ItemStack gemstone, ItemStack material) {
        if (material != null && material.getType() != Material.AIR) {
            if (this.appraisalStone.hasAppraisalStone(material.getType().name())) {
                return checkAppraisalMaterialCount(player, gemstone, material);
            }
        }
        player.sendMessage(this.iYamlMsg.getMsg("error_14"));
        return false;
    }

    private boolean checkAppraisalMaterialCount(Player player, ItemStack gemstone, ItemStack material) {
        if (material.getAmount() == 1) {
            appraisalHandle(gemstone, material);
            player.sendMessage(this.iYamlMsg.getMsg("error_17"));
            return true;
        }
        player.sendMessage(this.iYamlMsg.getMsg("error_16"));
        return false;
    }


    public void appraisalHandle(ItemStack appraisalMaterial, ItemStack appraisalItem) {
        ItemStack itemStack = NBTWrite.appraisalToMaterial(
                appraisalMaterial,
                appraisalItem,
                this.iYamlSetting.getIYamlGuiSetting().getBladePlusAppraisalDisplay());
        int gemstoneSlot = plusInventoryAttribute.getGemstoneSlot();
        int materialSlot = plusInventoryAttribute.getMaterialSlot();
        if (appraisalMaterial.getAmount() == 1) {
            this.AppraisalInventory.setItem(gemstoneSlot, new ItemStack(Material.AIR));
            this.AppraisalInventory.setItem(materialSlot, itemStack);
            return;
        }
        appraisalMaterial.setAmount(appraisalMaterial.getAmount() - 1);
        this.AppraisalInventory.setItem(gemstoneSlot, appraisalMaterial);
        this.AppraisalInventory.setItem(materialSlot, itemStack);
    }


    @Override
    public Inventory getInventory() {
        return AppraisalInventory;
    }

    @Override
    public int getGemstoneSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusAppraisalInventoryAttribute().getGemstoneSlot();
    }

    @Override
    public int getMaterialSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusAppraisalInventoryAttribute().getMaterialSlot();
    }

    @Override
    public int getButtonSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusAppraisalInventoryAttribute().getButton().getSlot();
    }
}
