package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.nbt.gemstone.Repair;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class ACommandRepair extends AbstractAdminCommand {

    public ACommandRepair(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        int repair = this.iConvert(args[0]);
        double probability = this.dConvert(args[1]);
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getType() != Material.AIR) {
            ItemStack repairGemstone = new Repair(itemStack, repair, probability).setGemstoneMate();
            player.setItemInHand(repairGemstone);
            sender.sendMessage("§7[§3Console§7] §7▶ §a获取成功.");
            sender.sendMessage("§7[§3Console§7]   §a▪ 增加几率为： §e" + probability+"");
            sender.sendMessage("§7[§3Console§7]   §a▪ 增加锻造数： §e" + repair+"");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §c▶ §c请手持物品.");
        return true;
    }


    @Override
    public String[] getArgs() {
        return new String[]{"repair"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("10");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
