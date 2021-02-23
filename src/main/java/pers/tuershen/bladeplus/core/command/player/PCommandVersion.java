package pers.tuershen.bladeplus.core.command.player;

import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/14
 */
public class PCommandVersion extends AbstractPlayerCommand<Player> {

    public PCommandVersion(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        player.sendMessage("§7[§3Console§7] §aThe plugin version is 1.1.5");
        player.sendMessage("§7[§3Console§7] §aThe author of this plugin is Tuershen(兔儿神) ");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"version"};
    }

    @Override
    public int getCommandLength() {
        return 0;
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
