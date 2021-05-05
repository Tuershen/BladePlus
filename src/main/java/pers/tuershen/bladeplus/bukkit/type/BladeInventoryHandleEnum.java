package pers.tuershen.bladeplus.bukkit.type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.Result;
import pers.tuershen.bladeplus.api.inv.IAppraisalInventory;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.bukkit.inv.BladePlusInventory;
import pers.tuershen.bladeplus.bukkit.inv.TextModelInventory;
import pers.tuershen.bladeplus.bukkit.nbt.NBTLookup;
import pers.tuershen.bladeplus.bukkit.nbt.NBTRead;

public enum BladeInventoryHandleEnum {
    BLADE_INVENTORY {
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            Player player = (Player)this.event.getWhoClicked();
            BladePlusInventory bladePlusInventory = (BladePlusInventory)this.event.getInventory().getHolder();
            if (rawSlot == bladePlusInventory.getButtonSlot()) {
                ItemStack material = bladePlusInventory.getInventory().getItem(bladePlusInventory.getMaterialSlot());
                if (null == material || material.getType() == Material.AIR) {
                    player.sendMessage(iYamlMsg.getMsg("error_1"));
                } else {
                    if (NBTLookup.hasMaterial(material)) {
                        bladePlusInventory.getBladeEntity().getNBTTagCompound().getCompound("Blade").getCompound("tag");
                        Result result = iYamlSetting.getIYamlBladeProgramme().equalResult(
                                NBTRead.getBladeEntityInt(bladePlusInventory.getBladeEntity(), "RepairCounter"),
                                NBTRead.getMaterialInt(material, "repairCounter"));
                        if (result.resultType() == ResultTypeEnum.SUCCESS) {
                            bladePlusInventory.runStart(material);
                            this.event.setCancelled(true);
                            return;
                        }
                        player.sendMessage(iYamlMsg.getMsg("error_18"));
                        this.event.setCancelled(true);
                        return;
                    }
                    player.sendMessage(iYamlMsg.getMsg("error_2"));
                    this.event.setCancelled(true);
                }
            }
            if (rawSlot < 18 && rawSlot != bladePlusInventory
                    .getGemstoneSlot() && rawSlot != bladePlusInventory
                    .getMaterialSlot())
                this.event.setCancelled(true);
        }
    },
    TEXT_MODEL_INVENTORY {
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            if (rawSlot >= 0 && rawSlot < 54) {
                TextModelInventory textModelInventory = (TextModelInventory)holder;
                Player player = (Player)this.event.getWhoClicked();
                ItemStack itemStack = this.event.getCurrentItem();
                if (null != itemStack &&
                        itemStack.getType() != Material.AIR)
                    if (rawSlot == 48) {
                        Inventory thePreviousPage = textModelInventory.thePreviousPage();
                        player.openInventory(thePreviousPage);
                    } else if (rawSlot == 50) {
                        Inventory next = textModelInventory.nextPage();
                        player.openInventory(next);
                    } else if (rawSlot < 45) {
                        player.setItemInHand(textModelInventory.replaceModel(itemStack, player.getItemInHand()));
                        player.closeInventory();
                        player.sendMessage(iYamlMsg.getMsg("error_11"));
                    }
            }
            this.event.setCancelled(true);
        }
    },
    APPRAISAL_INVENTORY {
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            IAppraisalInventory iAppraisalInventory = (IAppraisalInventory)holder;
            if (rawSlot < 9) {
                if (iAppraisalInventory.getButtonSlot() == rawSlot) {
                    iAppraisalInventory.checkAppraisalGemstone((Player)this.event.getWhoClicked());
                    this.event.setCancelled(true);
                }
                if (rawSlot != iAppraisalInventory.getGemstoneSlot() && rawSlot != iAppraisalInventory.getMaterialSlot())
                    this.event.setCancelled(true);
            }
        }
    },
    WARNING_INVENTORY {
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            this.event.setCancelled(true);
        }
    };

    public static BladeInventoryHandleEnum blade;

    public static BladeInventoryHandleEnum textModel;

    public static BladeInventoryHandleEnum appraisal;

    public static BladeInventoryHandleEnum warning;

    public static IYamlSetting iYamlSetting;

    public static IYamlMsg iYamlMsg;

    protected InventoryClickEvent event;

    static {
        blade = BLADE_INVENTORY;
        textModel = TEXT_MODEL_INVENTORY;
        appraisal = APPRAISAL_INVENTORY;
        warning = WARNING_INVENTORY;
    }

    public void setEvent(InventoryClickEvent event) {
        this.event = event;
    }

    public abstract <T extends InventoryHolder> void eventHandle(T paramT, int paramInt);
}
