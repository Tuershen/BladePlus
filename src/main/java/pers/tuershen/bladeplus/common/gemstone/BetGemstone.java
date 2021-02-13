package pers.tuershen.bladeplus.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.IBetGemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class BetGemstone extends Gemstone implements IBetGemstone {

    protected List<String> failMsg;

    public BetGemstone(String display, List<String> lore, List<String> successMsg, List<String> failMsg) {
        super(display, lore, successMsg);
        this.failMsg = failMsg;
    }

    public List<String> getFailMsg() {
        return failMsg;
    }
}
