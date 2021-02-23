package pers.tuershen.bladeplus.core.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.ISpecialGemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 * 异类石语言
 */
public class SpecialGemstone extends Gemstone implements ISpecialGemstone {

    private List<String> failMsg;

    private String killCountMsg;

    private String proudSoulMsg;

    private String baseAttackModifierMsg;

    private String summonedSwordColorMsg;

    private String repairCounter;

    public SpecialGemstone(String display, List<String> lore, List<String> successMsg) {
        super(display, lore, successMsg);
    }

    public List<String> getFailMsg() {
        return failMsg;
    }

    public SpecialGemstone setFailMsg(List<String> failMsg) {
        this.failMsg = failMsg;
        return this;
    }

    public SpecialGemstone setKillCountMsg(String killCountMsg) {
        this.killCountMsg = killCountMsg;
        return this;
    }

    public SpecialGemstone setProudSoulMsg(String proudSoulMsg) {
        this.proudSoulMsg = proudSoulMsg;
        return this;
    }

    public SpecialGemstone setBaseAttackModifierMsg(String baseAttackModifierMsg) {
        this.baseAttackModifierMsg = baseAttackModifierMsg;
        return this;
    }

    public SpecialGemstone setSummonedSwordColorMsg(String summonedSwordColorMsg) {
        this.summonedSwordColorMsg = summonedSwordColorMsg;
        return this;
    }

    public SpecialGemstone setRepairCounter(String repairCounter) {
        this.repairCounter = repairCounter;
        return this;
    }

    public String getKillCountMsg() {
        return killCountMsg;
    }

    public String getProudSoulMsg() {
        return proudSoulMsg;
    }

    @Override
    public String getBaseAttackModifier() {
        return this.baseAttackModifierMsg;
    }

    @Override
    public String getSummonedSwordColor() {
        return this.summonedSwordColorMsg;
    }

    public String getRepairCounter() {
        return this.repairCounter;
    }
}
