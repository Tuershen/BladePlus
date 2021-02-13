package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;
import pers.tuershen.bladeplus.gemstone.Repair;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class ACommandRepair extends AbstractAdminCommand<Player> {

    public ACommandRepair(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        double probability = this.probabilityCheck(dConvert(args[0]));
        int repair = this.iConvert(args[1]);
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getType() != Material.AIR) {
            IGemstoneDisplay iGemstoneDisplay = this.iYamlSetting.getIYamlSladePlusGemstone().getIRepairGemstone().getIGemstoneDisplay();
            ItemStack repairGemstone = new Repair(itemStack, iGemstoneDisplay, repair, probability).setGemstoneDisplay().setGemstoneMate();
            player.setItemInHand(repairGemstone);
            player.sendMessage("§7[§3Console§7] §7▶ §a获取成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 增加几率为： §e" + probability + " §b%");
            player.sendMessage("§7[§3Console§7]   §a▪ 增加锻造数： §e" + repair + "");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §c▶ §c请手持物品.");
        return true;
    }


    @Override
    public String[] getArgs() {
        return new String[]{"repair"};
    }

    @Override
    public int getCommandLength() {
        return 2;
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
