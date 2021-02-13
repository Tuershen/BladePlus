package pers.tuershen.bladeplus.command.admin;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandReload extends AbstractAdminCommand<CommandSender> {

    public ACommandReload(IYamlSetting iYamlSetting) {
        super(iYamlSetting);

    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        this.iYamlSetting.reload();
        sender.sendMessage("§7[§3Console§7] §7▶ §a重载成功.");
        return false;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"reload"};
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
