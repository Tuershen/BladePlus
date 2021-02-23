package pers.tuershen.bladeplus.core.type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.inv.IAppraisalInventory;
import pers.tuershen.bladeplus.api.inv.ISladePlusInventory;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.core.inv.BladePlusInventory;
import pers.tuershen.bladeplus.core.inv.TextModelInventory;
import pers.tuershen.bladeplus.nbt.NBTLookup;

/**
 * @auther Tuershen Create Date on 2021/2/22
 */
public enum BladeInventoryHandleEnum {

    ANVIL_INVENTORY {
        @Override
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            AnvilInventory inventory = (AnvilInventory) holder;
            Player player = (Player) event.getWhoClicked();
            if (rawSlot == 2) {
                ItemStack slashBlade = event.getCurrentItem();
                ItemStack bladePlusMaterial = inventory.getItem(1);
                if (NBTLookup.isSlashBlade(slashBlade) && NBTLookup.isForgingMaterial(bladePlusMaterial)) {
                    player.sendMessage(iYamlMsg.getMsg("error_9"));
                    event.setCancelled(true);
                }
            }
        }
    },

    BLADE_INVENTORY {
        @Override
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            Player player = (Player) event.getWhoClicked();
            ISladePlusInventory bladePlus = (BladePlusInventory) event.getInventory().getHolder();
            if (rawSlot == bladePlus.getButtonSlot()) {
                ItemStack material = bladePlus.getInventory().getItem(bladePlus.getMaterialSlot());
                if (null == material || material.getType() == Material.AIR) {
                    //材料为空气
                    player.sendMessage(iYamlMsg.getMsg("error_1"));
                } else if (NBTLookup.hasMaterial(material)) {
                    bladePlus.runStart(material);
                    event.setCancelled(true);
                    return;
                } else {
                    //不是强化材料
                    player.sendMessage(iYamlMsg.getMsg("error_2"));
                }
            }
            if (rawSlot < 18
                    && rawSlot != bladePlus.getGemstoneSlot()
                    && rawSlot != bladePlus.getMaterialSlot()) {
                event.setCancelled(true);
            }
        }
    },

    TEXT_MODEL_INVENTORY {
        @Override
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            if (rawSlot >= 0 && rawSlot < 54) {
                TextModelInventory textModelInventory = (TextModelInventory) holder;
                Player player = (Player) event.getWhoClicked();
                ItemStack itemStack = event.getCurrentItem();
                if (null != itemStack) {
                    if (itemStack.getType() != Material.AIR) {
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
                }
            }
            event.setCancelled(true);
        }
    },

    APPRAISAL_INVENTORY {
        @Override
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            IAppraisalInventory iAppraisalInventory = (IAppraisalInventory) holder;
            if (rawSlot < 9) {
                if (iAppraisalInventory.getButtonSlot() == rawSlot) {
                    iAppraisalInventory.checkAppraisalGemstone((Player) event.getWhoClicked());
                    event.setCancelled(true);
                }
                if (rawSlot != iAppraisalInventory.getGemstoneSlot() && rawSlot != iAppraisalInventory.getMaterialSlot()) {
                    event.setCancelled(true);
                }
            }
        }
    },

    WARNING_INVENTORY {
        @Override
        public <T extends InventoryHolder> void eventHandle(T holder, int rawSlot) {
            event.setCancelled(true);
        }
    };

    public static BladeInventoryHandleEnum anvil = BladeInventoryHandleEnum.ANVIL_INVENTORY;

    public static BladeInventoryHandleEnum blade = BladeInventoryHandleEnum.BLADE_INVENTORY;

    public static BladeInventoryHandleEnum textModel = BladeInventoryHandleEnum.TEXT_MODEL_INVENTORY;

    public static BladeInventoryHandleEnum appraisal = BladeInventoryHandleEnum.APPRAISAL_INVENTORY;

    public static BladeInventoryHandleEnum warning = BladeInventoryHandleEnum.WARNING_INVENTORY;

    public static IYamlSetting iYamlSetting;

    public static IYamlMsg iYamlMsg;

    protected InventoryClickEvent event;

    public void setEvent(InventoryClickEvent event) {
        this.event = event;
    }

    public abstract <T extends InventoryHolder> void eventHandle(T holder, int rawSlot);


}
