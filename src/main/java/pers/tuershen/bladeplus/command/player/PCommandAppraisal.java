package pers.tuershen.bladeplus.command.player;


import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.AppraisalInventory;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PCommandAppraisal extends AbstractPlayerCommand<Player> {

    private Map<String, AppraisalInventory> appraisalInventoryMap = new HashMap<>();

    public PCommandAppraisal(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public boolean onCommandHandle(Player player, String... args) {
        AppraisalInventory appraisalInventory;
        if (!this.appraisalInventoryMap.containsKey(player.getName())) {
            appraisalInventory = new AppraisalInventory(this.iYamlSetting);
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
