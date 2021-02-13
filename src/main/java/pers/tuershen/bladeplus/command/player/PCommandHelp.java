package pers.tuershen.bladeplus.command.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PCommandHelp extends AbstractPlayerCommand<CommandSender> {

    public PCommandHelp(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd open");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7打开皮肤管理界面");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd see");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7查看手中拔刀的最大锻造值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd jd");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7打开强化石鉴定界面");
        sender.sendMessage("§8§m--一一一一一一一一§7[§b1 §a/ §c1§7]§8§m一一一一一一一一--");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"help"};
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
        return CommandExecutorType.ALL;
    }
}
