package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.entity.ForgingModel;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandBladeModel extends AbstractAdminCommand {

    public ACommandBladeModel(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        String text = args[0];
        String mode = args[1];
        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.AIR) {
            ItemStack itemStack = NBTWrite.setModel(itemInHand, new ForgingModel(text, mode));
            player.setItemInHand(itemStack);
            sender.sendMessage("§7[§3Console§7] §b▶ §7设置成功.");
            sender.sendMessage("§7[§3Console§7]   §a▪ 皮肤材质路径为： §e" + text + "");
            sender.sendMessage("§7[§3Console§7]   §a▪ 皮肤模型路径为： §e" + mode + "");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
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
