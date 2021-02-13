package pers.tuershen.bladeplus.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.WarningInventory;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.nbt.NBTWrite;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class BladePlusUseSAListener implements Listener {

    private IYamlSetting iYamlSetting;

    public BladePlusUseSAListener(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
    }

    /**
     * 禁止使用拔刀的世界
     *
     * @param event
     */
    @EventHandler
    public void onPlayerUseSA(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand != null) {
            if (itemInHand.getType() != Material.AIR) {
                if (NBTRead.hasSpecialAttackTypeId(itemInHand)) {
                    int id = NBTRead.getBladeSpecialAttackTypeId(itemInHand);
                    if (this.iYamlSetting.getIYamlSpecialAttackType().isBanSA(id)) {
                        if (!playerPermissionUseSA(player, id)) {
                            ItemStack blade = NBTWrite.setSpecialAttackTypeId(itemInHand, this.iYamlSetting.getIYamlSpecialAttackType().getRandomSA());
                            player.setItemInHand(blade);
                            String warning = "你使用的SA存在BUG，已为你随机替换SA";
                            player.openInventory(new WarningInventory(warning).getInventory());
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }



    public boolean playerPermissionUseSA(Player player, int sa) {
        return player.hasPermission("BladePlus.banSA." + sa) || player.isOp();
    }

}
