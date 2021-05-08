package pers.tuershen.bladeplus.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.command.admin.AbstractAdminCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class AdminCommandHandle extends AbstractCommandExecutor<CommandSender> {

    private final List<AbstractAdminCommand<? extends CommandSender>> adminCommands = new ArrayList<>();

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
            this.callExecutor(sender, adminCommands, args);
            return true;
        }
        help(sender);
        return false;
    }

    public void help(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3BladePlus§7] §c▶ §b输入的指令不正确！");
        sender.sendMessage("§7[§3BladePlus§7] §c▶ §7请使用/bp help 或者 /bladePlus help查看指令帮助");
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("")) return this.tabResult();
        }
        return this.callTabExecutor(sender, adminCommands, args);
    }


    public <T extends AbstractAdminCommand<? extends CommandSender>> void registerAdminCommandHandle(T adminCommand) {
        if (adminCommand != null) {
            adminCommands.add(adminCommand);
        }
    }

    protected List<String> tabResult() {
        return this.tabResultList;
    }

    public <T extends AbstractAdminCommand<CommandSender>> void unregisterCommandHandle(T playerCommand){
        this.adminCommands.remove(playerCommand);
        BladePlusMain.bladePlusMain.logger("Success unregister AdminCommand of " + playerCommand.getClass().getSimpleName());
    }



}
