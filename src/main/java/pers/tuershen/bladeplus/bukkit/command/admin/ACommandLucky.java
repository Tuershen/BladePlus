package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;
import pers.tuershen.bladeplus.bukkit.gemstone.Lucky;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandLucky extends AbstractAdminCommand<Player> {

    public ACommandLucky(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        double probability = this.probabilityCheck(dConvert(args[0]));
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            IGemstoneDisplay iGemstoneDisplay = this.iYamlSetting.getIYamlSladePlusGemstone().getILuckyGemstone().getIGemstoneDisplay();
            ItemStack itemStack = new Lucky(itemInHand, iGemstoneDisplay, probability).setGemstoneDisplay().setGemstoneMate();
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 幸运石几率为： §e" + probability + " §b%");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"lucky"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("80");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
