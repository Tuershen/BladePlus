package pers.tuershen.bladeplus.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.ArrayList;
import java.util.List;


/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public abstract class AbstractCommandExecutor implements TabExecutor {

    protected IYamlSetting iYamlSetting;

    protected List<String> tabResultList = new ArrayList<>();

    public AbstractCommandExecutor(IYamlSetting iYamlSetting) {
        this.iYamlSetting = iYamlSetting;
    }

    public CommandExecutorType getExecutorType(CommandSender sender) {
        if (sender instanceof Player) return CommandExecutorType.PLAYER;
        return CommandExecutorType.CONSOLE;
    }

    public <E extends String> boolean screenExecutor(E[] args, E[] cmdArgs) {
        for (int i = 0; i < cmdArgs.length; i++) {
            if (!cmdArgs[i].equalsIgnoreCase(args[i])) return false;
        }
        return true;
    }


    public boolean screenExecutorType(CommandSender sender, CommandExecutorType executorType) {
        CommandExecutorType screenExecutorType = this.getExecutorType(sender);
        if (executorType == screenExecutorType) return true;
        return executorType == CommandExecutorType.ALL;
    }

    public <T extends AbstractCommand> void callExecutor(CommandSender sender, List<T> executors, String... args) {
        for (T executor : executors) {
            if (screenExecutorType(sender, executor.getCommandExecutorType())) {
                if (sender.hasPermission(executor.getPermission())) {
                    String[] cmdArgs = executor.getArgs();
                    int cl = cmdArgs.length;
                    int al = args.length;
                    if (al >= cl) {
                        if (screenExecutor(args, cmdArgs)) {
                            String[] buildArgs = new String[executor.getCommandLength()];
                            int bl = buildArgs.length;
                            for (int i = 0; i < al - cl && i != bl; i++) {
                                buildArgs[i] = args[i + cl];
                            }
                            executor.onCommandHandle(sender, buildArgs);
                        }
                    }
                }
            }
        }
    }


    public <T extends AbstractCommand> List<String> callTabExecutor(CommandSender sender, List<T> executors, String... args) {
        List<String> tabList = new ArrayList<>();
        for (T executor : executors) {
            if (screenExecutorType(sender, executor.getCommandExecutorType())) {
                if (sender.hasPermission(executor.getPermission())) {
                    int index = args.length - 1;
                    String param = args[index];
                    if (param.equalsIgnoreCase("")) {
                        index = index == 0 ? 0 : index - 1;
                        if (executor.getArgs().length >= args.length - 1) {
                            if (executor.getArgs()[index].equalsIgnoreCase(args[index])) {
                                return executor.getTabExecutorResult();
                            }
                        }
                    } else {
                        if (executor.getArgs().length >= args.length) {
                            if (executor.getArgs()[index].contains(param)) {
                                tabList.add(executor.getArgs()[index]);
                            }
                        }
                    }
                }
            }
        }
        return tabList;
    }

    protected abstract List<String> tabResult();


}
