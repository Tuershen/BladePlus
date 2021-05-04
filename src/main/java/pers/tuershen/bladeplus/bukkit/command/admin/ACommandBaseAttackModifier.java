package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.nbt.NBTWrite;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBaseAttackModifier extends AbstractAdminCommand<Player> {

    public ACommandBaseAttackModifier(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        float baseAttackModifier = this.fConvert(args[0]);
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setFloat(itemInHand, "baseAttackModifier", baseAttackModifier);
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 基础伤害值为： §e" + baseAttackModifier + "");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"baseAttackModifier"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("1000");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
