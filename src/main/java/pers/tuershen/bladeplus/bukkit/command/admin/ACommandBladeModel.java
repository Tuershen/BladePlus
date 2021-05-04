package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.common.ForgingModel;
import pers.tuershen.bladeplus.bukkit.nbt.NBTWrite;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBladeModel extends AbstractAdminCommand<Player> {

    public ACommandBladeModel(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public  boolean onCommandHandle(Player player, String... args) {
        String text = args[0];
        String mode = args[1];
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setModel(itemInHand, new ForgingModel(text, mode));
            player.setItemInHand(itemStack);
            player.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            player.sendMessage("§7[§3Console§7]   §a▪ 皮肤材质路径为： §e" + text + "");
            player.sendMessage("§7[§3Console§7]   §a▪ 皮肤模型路径为： §e" + mode + "");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"model"};
    }

    @Override
    public int getCommandLength() {
        return 2;
    }

    @Override
    public List<String> getTabExecutorResult() {
        tabResultList.add("named/sange/black");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
