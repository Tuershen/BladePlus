package pers.tuershen.bladeplus.command.admin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalMaterial;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.appraisal.AppraisalMaterial;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class ACommandAppraisal extends AbstractAdminCommand {

    private IYamlAppraisalMaterial iAppraisal;

    public ACommandAppraisal(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        iAppraisal = iYamlSetting.getIAppraisalMaterial();
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        String appraisal = args[0];
        if (this.iAppraisal.hasAppraisalMaterial(appraisal)) {
            ItemStack itemStack = player.getItemInHand();
            if (itemStack.getType() != Material.AIR) {
                AppraisalMaterial appraisalMaterial = this.iAppraisal.getAppraisalMaterial(appraisal);
                player.setItemInHand(NBTWrite.wireAppraisalToMaterial(itemStack, appraisalMaterial));
                sender.sendMessage("§7[§3Console§7] §7▶ §a获取成功.");
                return true;
            }
            sender.sendMessage("§7[§3Console§7] §c▶ §c请手持物品.");
            return true;
        }
        sender.sendMessage("§7[§3Console§7] §7▶ §c你输入的鉴定石方案不存在.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"appraisal"};
    }

    @Override
    public int getCommandLength() {
        return 1;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.iAppraisal.getAppraisalSchemes();
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
