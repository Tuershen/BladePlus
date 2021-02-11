package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;
import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandSummonedSwordColor extends AbstractAdminCommand {

    public ACommandSummonedSwordColor(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        ItemStack itemInHand = player.getItemInHand();
        int random = new Random().nextInt(0xffffff);
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setInt(itemInHand, "SummonedSwordColor", random);
            player.setItemInHand(itemStack);
            sender.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            sender.sendMessage("§7[§3Console§7]   §a▪ 颜色RGB为： §e" + random+"");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"summonedSwordColor"};
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
