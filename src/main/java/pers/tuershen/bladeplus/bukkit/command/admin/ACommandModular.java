package pers.tuershen.bladeplus.bukkit.command.admin;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.List;

public class ACommandModular extends AbstractAdminCommand<CommandSender> {

    public ACommandModular(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.tabResultList.add("info");
        this.tabResultList.add("reload");
        this.tabResultList.add("remove");
    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        sender.sendMessage("§8§m一一一一一一一§7[§b已安装的模块§7]§8§m一一一一一一一");
        sender.sendMessage("§a模块数量： §e"+  BladePlusMain.getModularSize());
        sender.sendMessage("§8§m一一一一一一一§7[§b具体信息§7]§8§m一一一一一一一");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"modular", "info"};
    }

    @Override
    public int getCommandLength() {
        return 1;
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
