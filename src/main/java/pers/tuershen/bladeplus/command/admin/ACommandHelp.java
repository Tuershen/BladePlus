package pers.tuershen.bladeplus.command.admin;

import org.bukkit.command.CommandSender;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandHelp extends AbstractAdminCommand<CommandSender> {

    public ACommandHelp(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(CommandSender sender, String... args) {
        int page;
        if (args[0] == null || args[0].equalsIgnoreCase("")) {
            page = 1;
        }else {
            page = Integer.parseInt(args[0]);
        }
        switch (page) {
            case 1:
                this.page1(sender);
                return true;
            case 2:
                this.page2(sender);
                return true;
            default:
                this.page3(sender);
                return true;
        }
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
        this.tabResultList.add("1");
        this.tabResultList.add("2");
        this.tabResultList.add("3");
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.ALL;
    }

    public void page1(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus setItem <name>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中物品属性");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus protect");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中物品为鉴定石");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus setModel <名称>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀剑的皮肤");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus setMax <数值>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀剑的最高锻造值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus reload");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7重新插件");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus help");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7插件指令帮助");
        sender.sendMessage("§8§m--一一一一一一一一§7[§b1 §a/ §c3§7]§8§m一一一一一一一一--");
    }

    public void page2(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus lucky <几率>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中物品为幸运石以及成功几率，增加强化成功几率");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus special <几率> <最小值> <最大值>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置异类石");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7有几率随机增加拔刀的基础伤害值，杀敌数，以及随机剑气颜色");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus bet <几率> <倍数>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §d赌狗石");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7有几率翻倍增加拔刀剑的属性");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7但也可能翻倍扣除拔刀剑的属性");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus repair <几率> <锻造数>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7有几率增加拔刀的最大锻造数限制");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus protect");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中物品为保护石，可以免除一次锻造失败惩罚");
        sender.sendMessage("§8§m--一一一一一一一一§7[§b2 §a/ §c3§7]§8§m一一一一一一一一--");
    }

    public void page3(CommandSender sender) {
        sender.sendMessage("§8§m--一一一一一一一一一一一一一一一一一一一--");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus repairCounter <锻造值>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀的锻造值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus proudSoul <荣耀值>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀的荣耀值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus killCount <杀敌数>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中物品为鉴定石");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus specialAttackType <技能id>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀剑的技能");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus baseAttackModifier <基础伤害值>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀剑的基础伤害值");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus summonedSwordColor");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7随机生成拔刀剑的剑气颜色");
        sender.sendMessage("§7[§3Console§7] §b▶ /bladePlus model <模型路径> <材质路径>");
        sender.sendMessage("§7[§3Console§7]   §a▪ §7设置手中拔刀剑的皮肤与模型");
        sender.sendMessage("§8§m--一一一一一一一一§7[§b3 §a/ §c3§7]§8§m一一一一一一一一--");
    }


}
