package pers.tuershen.bladeplus.command.admin;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.command.AbstractCommand;

/**
 * @auther Tuershen Create Date on 2021/1/7
 */
public abstract class AbstractAdminCommand<T extends CommandSender> extends AbstractCommand<T> {

    public AbstractAdminCommand(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public String getPermission() {
        return "BladePlus.admin";
    }

    public double dConvert(String args) {
        if (args == null) return 0.0;
        return Double.parseDouble(args);
    }

    public int iConvert(String args) {
        if (args == null) return 1;
        return Integer.parseInt(args);
    }

    public double probabilityCheck(double probability){
        return probability > 100.0d ? 100.0d : probability < 0 ? 0.0d : probability;
    }



}
