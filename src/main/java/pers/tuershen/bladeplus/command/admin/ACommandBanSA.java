package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlSpecialAttackType;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBanSA extends AbstractAdminCommand {

    private IYamlSpecialAttackType iYamlSpecialAttackType;

    public ACommandBanSA(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlSpecialAttackType = iYamlSetting.getIYamlSpecialAttackType();
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            if (NBTRead.hasSpecialAttackTypeId(itemInHand)) {
                int id = NBTRead.getBladeSpecialAttackTypeId(itemInHand);
                iYamlSpecialAttackType.addSA(id);
                sender.sendMessage("§7[§3Console§7] §7▶ §a以下SA已配置到黑名单.");
                sender.sendMessage("§7[§3Console§7]   §a▪ §cSpecialAttackType = §e" + id + "");
                return true;
            }
            sender.sendMessage("§7[§3Console§7] §7▶ §a手中物品没有SA.");
            return true;
        }
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"bansa"};
    }

    @Override
    public int getCommandLength() {
        return 0;
    }

    @Override
    public List<String> getTabExecutorResult() {
        this.tabResultList.add("3");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
