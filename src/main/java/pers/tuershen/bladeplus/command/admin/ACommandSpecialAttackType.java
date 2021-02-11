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
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandSpecialAttackType extends AbstractAdminCommand {

    public ACommandSpecialAttackType(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        int specialAttackType = this.iConvert(args[0]);
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setInt(itemInHand, "SpecialAttackType", specialAttackType);
            player.setItemInHand(itemStack);
            sender.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            sender.sendMessage("§7[§3Console§7]   §a▪ 技能id为： §e" + specialAttackType+"");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"specialAttackType"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        tabResultList.add("3");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
