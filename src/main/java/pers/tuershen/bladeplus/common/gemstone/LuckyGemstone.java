package pers.tuershen.bladeplus.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.ILuckyGemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class LuckyGemstone extends Gemstone implements ILuckyGemstone {

    public LuckyGemstone(String display, List<String> lore, List<String> successMsg) {
        super(display, lore, successMsg);
    }
}
