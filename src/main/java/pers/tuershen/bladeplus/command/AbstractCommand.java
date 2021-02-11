package pers.tuershen.bladeplus.command;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public abstract class AbstractCommand {

    protected IYamlSetting iYamlSetting;

    protected List<String> tabResultList = new ArrayList<>();

    public AbstractCommand(IYamlSetting iYamlSetting){
        this.iYamlSetting = iYamlSetting;
    }

    public abstract <T extends CommandSender> boolean onCommandHandle(T sender, String... args);

    public abstract String[] getArgs();

    public abstract String getPermission();

    public abstract int getCommandLength();

    public abstract List<String> getTabExecutorResult();

    public abstract CommandExecutorType getCommandExecutorType();


}
