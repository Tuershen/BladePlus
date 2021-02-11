package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandMaxRepairRepairCounter extends AbstractAdminCommand {

    public ACommandMaxRepairRepairCounter(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        int repairCounter =  this.iConvert(args[0]);
        if (player.getItemInHand().getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setMaxRepairCounter(player.getItemInHand(), repairCounter);
            player.setItemInHand(itemStack);
            sender.sendMessage("§7[§3Console§7] §7▶ §a设置成功.");
            sender.sendMessage("§7[§3Console§7]   §a▪ 最大锻造值为： §e" + repairCounter + "");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"setMax"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("100000");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
