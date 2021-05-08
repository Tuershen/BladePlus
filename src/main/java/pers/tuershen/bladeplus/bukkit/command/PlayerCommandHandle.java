package pers.tuershen.bladeplus.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.command.player.AbstractPlayerCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PlayerCommandHandle extends AbstractCommandExecutor<CommandSender> {

    private final List<AbstractPlayerCommand<? extends CommandSender>> playerCommands = new ArrayList<>();

    public PlayerCommandHandle(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.tabResultList.add("help");
        this.tabResultList.add("open");
        this.tabResultList.add("see");
        this.tabResultList.add("jd");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            this.callExecutor(sender, playerCommands, args);
            return true;
        }
        help(sender);
        return false;
    }

    public void help(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §c▶ §b输入的指令不正确！");
        sender.sendMessage("§7[§3Console§7] §c▶ §7请使用/bd help查看指令帮助");
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("")) return this.tabResult();
        }
        return this.callTabExecutor(sender, playerCommands, args);
    }

    public <T extends AbstractPlayerCommand<? extends CommandSender>> void registerCommandHandle(T playerCommand) {
        if (playerCommand != null) {
            this.playerCommands.add(playerCommand);
        }
    }

    public <T extends AbstractPlayerCommand<? extends CommandSender>> void unregisterCommandHandle(T playerCommand){
        this.playerCommands.remove(playerCommand);
        BladePlusMain.bladePlusMain.logger("Success unregister PlayerCommand of " + playerCommand.getClass().getSimpleName());
    }

    @Override
    protected List<String> tabResult() {
        return this.tabResultList;
    }





}
