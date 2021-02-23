package pers.tuershen.bladeplus.core.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.balde.IYamlMaterial;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.common.BladePlusMaterial;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.core.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandMaterial extends AbstractAdminCommand<Player> {

    private IYamlMaterial iYamlMaterial;

    public ACommandMaterial(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlMaterial = iYamlSetting.getIYamlMaterial();
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        String itemKey = args[0];
        if (this.iYamlMaterial.hasBladePlusMaterial(itemKey)) {
            ItemStack itemInHand = player.getItemInHand();
            if (null != itemInHand) {
                if (itemInHand.getType() != Material.AIR) {
                    BladePlusMaterial bladePlusMaterial = this.iYamlMaterial.getBladePlusMaterial(itemKey);
                    ItemStack itemStack = NBTWrite.writerNBT(itemInHand, bladePlusMaterial);
                    player.setItemInHand(itemStack);
                    player.sendMessage("§7[§3Console§7] §7▶ §a获取成功.");
                    return true;
                }
            }
            player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c你输入的材料不存在.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"setItem"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.iYamlMaterial.getMaterialKeys();
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }


}
