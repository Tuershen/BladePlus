package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.entity.ForgingModel;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandModel extends AbstractAdminCommand {

    private IYamlModel iYamlModel;

    public ACommandModel(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlModel = iYamlSetting.getIYamlModel();
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        String model = args[0];
        if (iYamlModel.hasModel(model)) {
            ForgingModel forgingModel = iYamlModel.getModel(model);
            ItemStack itemStack = player.getItemInHand();
            if (itemStack.getType() != Material.AIR) {
                player.setItemInHand(NBTWrite.setModel(itemStack, forgingModel));
                sender.sendMessage("§7[§3Console§7] §7▶ §a更改成功.");
                return true;
            }
            sender.sendMessage("§7[§3Console§7] §7▶ §c请手持物品.");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c你输入的皮肤路径不存在.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"setModel"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.iYamlModel.getModelKeys();
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
