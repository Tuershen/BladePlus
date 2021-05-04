package pers.tuershen.bladeplus.bukkit.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.command.player.AbstractPlayerCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PlayerCommandHandle<C extends CommandSender> extends AbstractCommandExecutor<C> {

    private List<AbstractPlayerCommand<C>> playerCommands = new ArrayList<>();

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
            this.callExecutor((C)sender, playerCommands, args);
            return true;
        }
        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("")) return this.tabResult();
        }
        return this.callTabExecutor((C)sender, playerCommands, args);
    }

    public <T extends AbstractPlayerCommand<C>> void registerCommandHandle(T playerCommand) {
        if (playerCommand != null) {
            this.playerCommands.add(playerCommand);
        }
    }

    @Override
    protected List<String> tabResult() {
        return this.tabResultList;
    }

}
