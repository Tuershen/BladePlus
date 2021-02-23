package pers.tuershen.bladeplus.core.listener;

import org.bukkit.inventory.InventoryHolder;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.inv.BladePlusInventory;
import pers.tuershen.bladeplus.core.inv.WarningInventory;
import pers.tuershen.bladeplus.core.type.BladeInventoryHandleEnum;
import pers.tuershen.bladeplus.core.inv.AppraisalInventory;
import pers.tuershen.bladeplus.core.inv.TextModelInventory;
import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.entity.AbstractMinecraftEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import pers.tuershen.bladeplus.util.EntityCheck;

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
        BladeInventoryHandleEnum.iYamlSetting = this.iYamlSetting;
        BladeInventoryHandleEnum.iYamlMsg = this.iYamlMsg;
    }

    /**
     * 界面交互
     *
     * @param event
     */
    @EventHandler
    public void onClickAnvilInventory(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        int rawSlot = event.getRawSlot();
        if (holder instanceof AnvilInventory) {
            BladeInventoryHandleEnum anvil = BladeInventoryHandleEnum.anvil;
            anvil.setEvent(event);
            anvil.eventHandle(holder, rawSlot);
        } else if (holder instanceof BladePlusInventory) {
            BladeInventoryHandleEnum blade = BladeInventoryHandleEnum.blade;
            blade.setEvent(event);
            blade.eventHandle(holder, rawSlot);
        } else if (holder instanceof TextModelInventory) {
            BladeInventoryHandleEnum textModel = BladeInventoryHandleEnum.textModel;
            textModel.setEvent(event);
            textModel.eventHandle(holder, rawSlot);
        } else if (holder instanceof AppraisalInventory) {
            BladeInventoryHandleEnum appraisal = BladeInventoryHandleEnum.appraisal;
            appraisal.setEvent(event);
            appraisal.eventHandle(holder, rawSlot);
        } else if (holder instanceof WarningInventory) {
            BladeInventoryHandleEnum warning = BladeInventoryHandleEnum.warning;
            warning.setEvent(event);
            warning.eventHandle(holder, rawSlot);
        }
    }

    /**
     * 打开拔刀强化界面
     *
     * @param event
     */
    @EventHandler
    public void onClickAnvilBlock(PlayerInteractEvent event) {
        final Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null && clickedBlock.getType() != Material.AIR) {
            //玩家右键铁贴
            if (clickedBlock.getType().name().equalsIgnoreCase("Anvil")) {
                final Player player = event.getPlayer();
                EntityCheck entityCheck = new EntityCheck(player, clickedBlock);
                if (entityCheck.check()) {
                    BladePlusInventory blade;
                    if (!forgingInventoryMap.containsKey(player.getName())) {
                        blade = new BladePlusInventory(event.getPlayer(),
                                event.getClickedBlock(),
                                this.iYamlSetting,
                                entityCheck.getBladeEntity());
                        blade.setDefaultUi();
                        this.forgingInventoryMap.put(player.getName(), blade);
                    }
                    blade = this.forgingInventoryMap.get(player.getName());
                    blade.setPlayer(event.getPlayer())
                            .setBladeEntity(entityCheck.getBladeEntity())
                            .setBlock(event.getClickedBlock());
                    event.setCancelled(true);
                    player.openInventory(blade.getInventory());
                }
            }
        }
    }
}

