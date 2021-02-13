package pers.tuershen.bladeplus.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.IProtectGemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class ProtectGemstone extends Gemstone implements IProtectGemstone {

    public ProtectGemstone(String display, List<String> lore, List<String> successMsg) {
        super(display, lore, successMsg);
    }
}
