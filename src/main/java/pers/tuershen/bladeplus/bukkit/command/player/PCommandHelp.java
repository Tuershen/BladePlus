package pers.tuershen.bladeplus.bukkit.command.player;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.type.CommandExecutorType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PCommandHelp extends AbstractPlayerCommand<CommandSender> {

    private static final Map<String, List<String>> modularMap = new HashMap<>();

    private static final int HELP_COUNT = 1;

    public PCommandHelp(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.tabResultList.add("1");
    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        int page;
        if (args[0] == null || args[0].equalsIgnoreCase("")) {
            page = 1;
        }else {
            page = Integer.parseInt(args[0]);
        }
        if (page == 1) {
            this.page1(sender);
            return true;
        }
        if (page > HELP_COUNT) {
            if (page <= modularMap.size() + HELP_COUNT) {
                Object hasKey = modularMap.keySet().toArray()[page];
                List<String> helpInfo = modularMap.get(hasKey.toString());
                this.pageModular(sender, helpInfo);
                return true;
            }
        }
        sender.sendMessage("§7[§3Console§7] §c▶ 页数不存在！");
        return true;
    }

    public void page1(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd open");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7打开皮肤管理界面");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd see");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7查看手中拔刀的最大锻造值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bd jd");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7打开强化石鉴定界面");
        sender.sendMessage("§8§m--一一一一一一一一§7[§b1 §a/ §c1§7]§8§m一一一一一一一一--");
    }

    @Override
    public String[] getArgs() {
        return new String[]{"help"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.tabResultList;
    }

    public void pageModular(CommandSender sender, List<String> helpInfo){
        helpInfo.forEach(sender::sendMessage);
    }

    public static void registerHelp(String code, List<String> helpInfo){
        modularMap.put(code, helpInfo);
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.ALL;
    }
}
