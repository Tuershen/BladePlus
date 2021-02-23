package pers.tuershen.bladeplus.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.command.admin.AbstractAdminCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class AdminCommandHandle<C extends CommandSender> extends AbstractCommandExecutor<C> {

    private List<AbstractAdminCommand<C>> adminCommands = new ArrayList<>();

    public AdminCommandHandle(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.tabResultList.add("setItem");
        this.tabResultList.add("appraisal");
        this.tabResultList.add("setModel");
        this.tabResultList.add("setMax");
        this.tabResultList.add("lucky");
        this.tabResultList.add("special");
        this.tabResultList.add("bet");
        this.tabResultList.add("banse");
        this.tabResultList.add("protect");
        this.tabResultList.add("repairCounter");
        this.tabResultList.add("proudPoul");
        this.tabResultList.add("specialAttackType");
        this.tabResultList.add("baseAttackModifier");
        this.tabResultList.add("SummonedSwordColor");
        this.tabResultList.add("model");
        this.tabResultList.add("bansa");
        this.tabResultList.add("repair");
        this.tabResultList.add("reload");
        this.tabResultList.add("help");
        this.tabResultList.add("version");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            this.callExecutor((C)sender, adminCommands, args);
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("")) return this.tabResult();
        }
        return this.callTabExecutor((C)sender, adminCommands, args);
    }


    public <T extends AbstractAdminCommand<C>> void registerAdminCommandHandle(T adminCommand) {
        if (adminCommand != null) {
            adminCommands.add(adminCommand);
        }
    }

    protected List<String> tabResult() {
        return this.tabResultList;
    }


}
