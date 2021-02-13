package pers.tuershen.bladeplus.listener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.WarningInventory;
import pers.tuershen.bladeplus.nbt.NBTLookup;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class BladePlusUseBladeListener implements Listener {


    private IYamlSetting iYamlSetting;

    public BladePlusUseBladeListener(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
    }

    /**
     * 禁止使用拔刀的世界
     *
     * @param event
     */
    @EventHandler
    public void onBanUseWorld(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand != null) {
            if (itemInHand.getType() != Material.AIR) {
                if (NBTLookup.isSlashBlade(itemInHand)) {
                    World world = player.getWorld();
                    if (this.iYamlSetting.getIYamlBanUseWorld().isBanWorld(world)) {
                        if (!player.isOp()) {
                            int heldItemSlot = player.getInventory().getHeldItemSlot();
                            String warning = "该世界禁止使用拔刀剑";
                            if (heldItemSlot == 0) {
                                player.getInventory().setHeldItemSlot(1);
                                player.openInventory(new WarningInventory(warning).getInventory());
                            } else if (heldItemSlot == 8) {
                                player.getInventory().setHeldItemSlot(7);
                                player.openInventory(new WarningInventory(warning).getInventory());
                            } else {
                                player.getInventory().setHeldItemSlot(heldItemSlot + 1);
                                player.openInventory(new WarningInventory(warning).getInventory());
                            }
                            event.setCancelled(true);
                            player.sendMessage(this.iYamlSetting.getIYamlMsg().getMsg("error_18"));
                        }
                    }
                }
            }
        }
    }





}
