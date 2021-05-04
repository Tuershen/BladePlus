package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.balde.IYamlBladeSkillType;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;
import pers.tuershen.bladeplus.bukkit.nbt.NBTRead;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/23
 */
public class ACommandBanSE extends AbstractAdminCommand<Player> {

    private IYamlBladeSkillType iYamlBladeSkillType;

    public ACommandBanSE(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlBladeSkillType = iYamlSetting.getIYamlSpecialAttackType();
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            if (NBTRead.hasSEffect(itemInHand)) {
                List<String> keys = NBTRead.getSEffectList(itemInHand);
                if (keys.size() > 0) {
                    this.iYamlBladeSkillType.addSEffects(keys);
                    player.sendMessage("§7[§3Console§7] §7▶ §a以下SE已配置到黑名单.");
                    for (String key : keys) {
                        player.sendMessage("§7[§3Console§7]   §a▪ §cSB.SEffect = §e" + key + "");
                    }
                    return true;
                }
            }
            player.sendMessage("§7[§3Console§7] §7▶ §c手中物品没有SE.");
            return true;
        }
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"banse"};
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
