package pers.tuershen.bladeplus.core.command.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.core.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class PCommandSeeBladeMaxRepairCounter extends AbstractPlayerCommand<Player> {

    public PCommandSeeBladeMaxRepairCounter(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand != null && itemInHand.getType() != Material.AIR) {
            int maxRepairCounter = this.iYamlSetting.getMaxRepairCounter();
            if (NBTLookup.hasMaxRepairCounter(itemInHand)) {
                maxRepairCounter = NBTRead.getMaxRepairCounter(itemInHand);
            }
            player.sendMessage("§7[§3Console§7] §7▶ §b目前最大锻造值为： §e" + maxRepairCounter );
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"see"};
    }

    @Override
    public int getCommandLength() {
        return 0;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
