package pers.tuershen.bladeplus.core.listener;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.core.inv.BladePlusInventory;
import pers.tuershen.bladeplus.core.type.BladeInventoryHandleEnum;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.util.EntityCheck;

public class BladePlusListener implements Listener {
    private IYamlSetting iYamlSetting;

    private Map<String, BladePlusInventory> forgingInventoryMap = new HashMap<>();

    public BladePlusListener(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
        IYamlMsg iYamlMsg = iYamlSetting.getIYamlMsg();
        BladeInventoryHandleEnum.iYamlSetting = this.iYamlSetting;
        BladeInventoryHandleEnum.iYamlMsg = iYamlMsg;
    }

    @EventHandler
    public void onClickAnvilInventory(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        int rawSlot = event.getRawSlot();
        if (event.getInventory() instanceof AnvilInventory) {
            AnvilInventory inventory = (AnvilInventory)event.getInventory();
            Player player = (Player)event.getWhoClicked();
            if (rawSlot == 2) {
                ItemStack slashBlade = event.getCurrentItem();
                ItemStack bladePlusMaterial = inventory.getItem(1);
                if (NBTLookup.isSlashBlade(slashBlade) && NBTLookup.isForgingMaterial(bladePlusMaterial)) {
                    player.sendMessage(this.iYamlSetting.getIYamlMsg().getMsg("error_9"));
                    event.setCancelled(true);
                }
            }
        } else if (holder instanceof BladePlusInventory) {
            BladeInventoryHandleEnum blade = BladeInventoryHandleEnum.blade;
            blade.setEvent(event);
            blade.eventHandle(holder, rawSlot);
        } else if (holder instanceof pers.tuershen.bladeplus.core.inv.TextModelInventory) {
            BladeInventoryHandleEnum textModel = BladeInventoryHandleEnum.textModel;
            textModel.setEvent(event);
            textModel.eventHandle(holder, rawSlot);
        } else if (holder instanceof pers.tuershen.bladeplus.core.inv.AppraisalInventory) {
            BladeInventoryHandleEnum appraisal = BladeInventoryHandleEnum.appraisal;
            appraisal.setEvent(event);
            appraisal.eventHandle(holder, rawSlot);
        } else if (holder instanceof pers.tuershen.bladeplus.core.inv.WarningInventory) {
            BladeInventoryHandleEnum warning = BladeInventoryHandleEnum.warning;
            warning.setEvent(event);
            warning.eventHandle(holder, rawSlot);
        }
    }

    @EventHandler
    public void onClickAnvilBlock(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null && clickedBlock.getType() != Material.AIR)
            if (clickedBlock.getType().name().equalsIgnoreCase("Anvil")) {
                Player player = event.getPlayer();
                EntityCheck entityCheck = new EntityCheck(player, clickedBlock);
                if (entityCheck.check()) {
                    if (!this.forgingInventoryMap.containsKey(player.getName())) {
                        BladePlusInventory bladePlusInventory = new BladePlusInventory(event.getPlayer(), event.getClickedBlock(), this.iYamlSetting, entityCheck.getBladeEntity());
                        bladePlusInventory.setDefaultUi();
                        this.forgingInventoryMap.put(player.getName(), bladePlusInventory);
                    }
                    BladePlusInventory blade = this.forgingInventoryMap.get(player.getName());
                    blade.setPlayer(event.getPlayer())
                            .setBladeEntity(entityCheck.getBladeEntity())
                            .setBlock(event.getClickedBlock());
                    event.setCancelled(true);
                    player.openInventory(blade.getInventory());
                }
            }
    }
}
