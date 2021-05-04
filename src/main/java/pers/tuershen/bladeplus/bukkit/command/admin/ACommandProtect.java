package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;
import pers.tuershen.bladeplus.bukkit.gemstone.Protect;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandProtect extends AbstractAdminCommand<Player> {

    public ACommandProtect(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        ItemStack itemInHand = player.getItemInHand();
        if (null != itemInHand && itemInHand.getType() != Material.AIR) {
            IGemstoneDisplay iGemstoneDisplay = this.iYamlSetting.getIYamlSladePlusGemstone().getIProtectGemstone().getIGemstoneDisplay();
            ItemStack itemStack = new Protect(itemInHand, iGemstoneDisplay).setGemstoneDisplay().setGemstoneMate();
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §7▶ §a保护石获取成功.");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"protect"};
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
