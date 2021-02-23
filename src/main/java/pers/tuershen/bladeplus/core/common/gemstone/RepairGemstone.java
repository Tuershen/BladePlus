package pers.tuershen.bladeplus.core.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.IRepairGemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class RepairGemstone extends Gemstone implements IRepairGemstone {

    private List<String> failMsg;

    public RepairGemstone(String display, List<String> lore, List<String> successMsg, List<String> failMsg) {
        super(display, lore, successMsg);
        this.failMsg = failMsg;
    }

    public List<String> getFailMsg() {
        return failMsg;
    }
}
