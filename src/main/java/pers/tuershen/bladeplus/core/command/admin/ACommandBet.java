package pers.tuershen.bladeplus.core.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;
import pers.tuershen.bladeplus.core.gemstone.Bet;
import pers.tuershen.bladeplus.core.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBet extends AbstractAdminCommand<Player> {

    public ACommandBet(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        double probability = this.probabilityCheck(dConvert(args[0]));
        double magnification = this.dConvert(args[1]);
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            IGemstoneDisplay iGemstoneDisplay = this.iYamlSetting.getIYamlSladePlusGemstone().getIBetGemstone().getIGemstoneDisplay();
            ItemStack itemStack = new Bet(itemInHand, iGemstoneDisplay, probability, magnification).setGemstoneDisplay().setGemstoneMate();
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 几率为： §e" + probability + " §b%");
            player.sendMessage("§7[§3Console§7]   §a▪ 倍率为： §e" + magnification + " §b%");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"bet"};
    }

    @Override
    public int getCommandLength() {
        return 2;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("50");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
