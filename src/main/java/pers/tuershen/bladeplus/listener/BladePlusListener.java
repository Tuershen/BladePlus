package pers.tuershen.bladeplus.listener;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.api.inv.IAppraisalInventory;
import pers.tuershen.bladeplus.api.inv.ISladePlusInventory;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.BladePlusInventory;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTUtil;
import pers.tuershen.bladeplus.inv.AppraisalInventory;
import pers.tuershen.bladeplus.inv.TextModelInventory;
import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.entity.AbstractMinecraftEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */


public class BladePlusListener implements Listener {

    private IYamlSetting iYamlSetting;

    private IYamlMsg iYamlMsg;

    private Map<String, BladePlusInventory> forgingInventoryMap = new HashMap<>();

    //FLAMMPFEIL.SLASHBLADE_BLADESTAND 刀挂台

    public BladePlusListener(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
        this.iYamlMsg = iYamlSetting.getIYamlMsg();
    }

    /**
     * 界面交互
     *
     * @param event
     */
    @EventHandler
    public void onClickAnvilInventory(InventoryClickEvent event) {
        int rawSlot = event.getRawSlot();
        if (event.getInventory() instanceof AnvilInventory) {
            AnvilInventory inventory = (AnvilInventory) event.getInventory();
            Player player = (Player) event.getWhoClicked();
            if (rawSlot == 2) {
                ItemStack slashBlade = event.getCurrentItem();
                ItemStack bladePlusMaterial = inventory.getItem(1);
                if (NBTLookup.isSlashBlade(slashBlade) && NBTLookup.isForgingMaterial(bladePlusMaterial)) {
                    player.sendMessage(iYamlMsg.getMsg("error_9"));
                    event.setCancelled(true);
                }
            }
            //强化界面
        } else if (event.getInventory().getHolder() instanceof BladePlusInventory) {
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
        } else if (event.getInventory().getHolder() instanceof TextModelInventory) {
            if (rawSlot >= 0 && rawSlot < 54) {
                TextModelInventory textModelInventory = (TextModelInventory) event.getInventory().getHolder();
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
            //强化石鉴定
        } else if (event.getInventory().getHolder() instanceof AppraisalInventory) {
            IAppraisalInventory iAppraisalInventory = (AppraisalInventory) event.getInventory().getHolder();
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
    }

    /**
     * 打开拔刀强化界面
     *
     * @param event
     */
    @Deprecated
    @EventHandler
    public void onClickAnvilBlock(PlayerInteractEvent event) {
        final Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null && clickedBlock.getType() != Material.AIR) {
            //玩家右键铁贴
            if (clickedBlock.getType().name().equalsIgnoreCase("Anvil")) {
                final Player player = event.getPlayer();
                Entity[] entities = clickedBlock.getChunk().getEntities();
                for (Entity entity : entities) {
                    if (entity.getType().name().equalsIgnoreCase(NBTUtil.BLADE_STAND)) {
                        Location anvil = entity.getLocation();
                        anvil.setY(anvil.getY() - 1);
                        if (anvil.getBlock().equals(clickedBlock)) {
                            EntityNBTTagCompoundApi instance = AbstractMinecraftEntity.getInstance(entity);
                            NBTTagCompoundApi nbtTagCompound = instance.getNBTTagCompound();
                            if (nbtTagCompound.hasKey("Blade")) {
                                BladePlusInventory forgingInventory;
                                if (!forgingInventoryMap.containsKey(player.getName())) {
                                    forgingInventory = new BladePlusInventory(event.getPlayer(), event.getClickedBlock(), this.iYamlSetting, instance);
                                    forgingInventory.setDefaultUi();
                                    this.forgingInventoryMap.put(player.getName(), forgingInventory);
                                }
                                forgingInventory = this.forgingInventoryMap.get(player.getName());
                                forgingInventory.setPlayer(event.getPlayer()).setEntityNBTTagCompoundApi(instance);
                                event.setCancelled(true);
                                player.openInventory(forgingInventory.getInventory());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


}
