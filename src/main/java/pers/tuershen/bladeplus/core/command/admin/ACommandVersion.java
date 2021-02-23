package pers.tuershen.bladeplus.core.command.admin;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class ACommandVersion extends AbstractAdminCommand<CommandSender> {

    public ACommandVersion(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        sender.sendMessage("§7[§3Console§7] §aThe plugin version is 1.1.5");
        sender.sendMessage("§7[§3Console§7] §aThe author of this plugin is Tuershen(兔儿神) ");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"version"};
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
