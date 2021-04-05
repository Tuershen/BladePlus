package pers.tuershen.bladeplus.core.listener;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.inv.WarningInventory;
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
        if (itemInHand != null &&
                itemInHand.getType() != Material.AIR &&
                NBTLookup.isSlashBlade(itemInHand)) {
            World world = player.getWorld();
            if (this.iYamlSetting.getIYamlBanUseWorld().isBanWorld(world) &&
                    !player.isOp()) {
                int heldItemSlot = player.getInventory().getHeldItemSlot();
                String warning = this.iYamlSetting.getIYamlMsg().getMsg("banUseWorldMsg");
                player.getInventory().setHeldItemSlot((heldItemSlot == 0) ? 1 : ((heldItemSlot == 8) ? 7 : (heldItemSlot + 1)));
                player.openInventory((new WarningInventory(warning, this.iYamlSetting)).getInventory());
                event.setCancelled(true);
            }
        }
    }
}
