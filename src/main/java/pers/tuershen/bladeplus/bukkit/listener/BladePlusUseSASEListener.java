package pers.tuershen.bladeplus.bukkit.listener;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.inv.WarningInventory;
import pers.tuershen.bladeplus.bukkit.nbt.NBTRead;
import pers.tuershen.bladeplus.bukkit.nbt.NBTWrite;
import pers.tuershen.bladeplus.bukkit.type.SEffectType;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class BladePlusUseSASEListener implements Listener {

    private IYamlSetting iYamlSetting;

    public BladePlusUseSASEListener(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
    }

    /**
     * 禁止使用拔刀的世界
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
                            String warning = this.iYamlSetting
                                    .getIYamlMsg()
                                    .getMsg("banSAmsg")
                                    .replace("%saType%", String.valueOf(id));
                            player.openInventory(new WarningInventory(warning, iYamlSetting).getInventory());
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void oPlayerUseSE(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand != null) {
            if (itemInHand.getType() != Material.AIR) {
                //是否有SB.SEffect
                if (NBTRead.hasSEffect(itemInHand)) {
                    TagCompound<TagInt> effect = NBTRead.getSEffect(itemInHand);
                    SEffectType effectType = this.iYamlSetting.getIYamlSpecialAttackType().isGetBanSE(effect);
                    if (effectType == SEffectType.VALUE) {
                        if (!playerPermissionUseSE(player, effectType.getKetType())) {
                            effect.remove(effectType.getKetType());
                            ItemStack blade = NBTWrite.removeSEffect(itemInHand, effect);
                            player.setItemInHand(blade);
                            String warning = this.iYamlSetting
                                    .getIYamlMsg()
                                    .getMsg("banSEmsg")
                                    .replace("%effectType%", effectType.getKetType());
                            player.openInventory(new WarningInventory(warning, iYamlSetting).getInventory());
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

    public boolean playerPermissionUseSE(Player player, String se) {
        return player.hasPermission("BladePlus.banSE." + se) || player.isOp();
    }

}
