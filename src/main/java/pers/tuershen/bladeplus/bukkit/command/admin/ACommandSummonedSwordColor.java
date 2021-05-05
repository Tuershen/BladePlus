package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.nbt.NBTWrite;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;
import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandSummonedSwordColor extends AbstractAdminCommand<Player> {

    public ACommandSummonedSwordColor(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        ItemStack itemInHand = player.getItemInHand();
        int random = new Random().nextInt(0xffffff);
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setInt(itemInHand, "SummonedSwordColor", random);
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 颜色RGB为： §e" + random + "");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"summonedSwordColor"};
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
