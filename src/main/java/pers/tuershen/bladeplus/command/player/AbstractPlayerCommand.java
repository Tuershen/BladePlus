package pers.tuershen.bladeplus.command.player;

import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.command.AbstractCommand;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public abstract class AbstractPlayerCommand extends AbstractCommand {

    public AbstractPlayerCommand(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public String getPermission() {
        return "BladePlus.use";
    }

}
