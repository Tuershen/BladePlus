package pers.tuershen.bladeplus.command.player;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.AppraisalInventory;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PCommandAppraisal extends AbstractPlayerCommand {

    private Map<String, AppraisalInventory> appraisalInventoryMap = new HashMap<>();

    private IYamlMsg iYamlMsg;

    private IYamlAppraisalStone appraisalStone;

    public PCommandAppraisal(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
        this.iYamlMsg = iYamlSetting.getIYamlMsg();
        this.appraisalStone = iYamlSetting.getIYamlAppraisalStone();
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        AppraisalInventory appraisalInventory;
        if (!this.appraisalInventoryMap.containsKey(player.getName())) {
            appraisalInventory = new AppraisalInventory(this.iYamlMsg, this.appraisalStone);
            this.appraisalInventoryMap.put(player.getName(), appraisalInventory);
        }
        appraisalInventory = this.appraisalInventoryMap.get(player.getName());
        player.openInventory(appraisalInventory.getInventory());
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[] {"jd"};
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
        return CommandExecutorType.PLAYER;
    }
}
