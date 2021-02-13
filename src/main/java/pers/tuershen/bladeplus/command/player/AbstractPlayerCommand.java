package pers.tuershen.bladeplus.command.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.command.AbstractCommand;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public abstract class AbstractPlayerCommand<T extends CommandSender> extends AbstractCommand<T> {

    public AbstractPlayerCommand(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public String getPermission() {
        return "BladePlus.use";
    }

}
