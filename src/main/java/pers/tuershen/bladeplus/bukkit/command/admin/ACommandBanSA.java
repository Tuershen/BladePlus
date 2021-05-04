package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlBladeSkillType;
import pers.tuershen.bladeplus.bukkit.nbt.NBTRead;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBanSA extends AbstractAdminCommand<Player> {

    private IYamlBladeSkillType iYamlBladeSkillType;

    public ACommandBanSA(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlBladeSkillType = iYamlSetting.getIYamlSpecialAttackType();
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            if (NBTRead.hasSpecialAttackTypeId(itemInHand)) {
                int id = NBTRead.getBladeSpecialAttackTypeId(itemInHand);
                iYamlBladeSkillType.addSA(id);
                player.sendMessage("§7[§3Console§7] §7▶ §a以下SA已配置到黑名单.");
                player.sendMessage("§7[§3Console§7]   §a▪ §cSpecialAttackType = §e" + id + "");
                return true;
            }
            player.sendMessage("§7[§3Console§7] §7▶ §c手中物品没有SA.");
            return true;
        }
        return true;
    }


    @Override
    public String[] getArgs() {
        return new String[]{"bansa"};
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
