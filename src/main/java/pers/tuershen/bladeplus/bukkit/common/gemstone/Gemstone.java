package pers.tuershen.bladeplus.bukkit.common.gemstone;

import pers.tuershen.bladeplus.api.gemstone.IGemstone;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class Gemstone implements IGemstone, IGemstoneDisplay {

    protected String display;

    protected List<String> lore;

    protected List<String> successMsg;

    public Gemstone(String display, List<String> lore, List<String> successMsg) {
        this.display = display;
        this.lore = lore;
        this.successMsg = successMsg;
    }

    public String getDisplay() {
        return display;
    }

    public List<String> getLore() {
        return new ArrayList<>(this.lore);
    }

    public List<String> getSuccessMsg() {
        return successMsg;
    }

    @Override
    public IGemstoneDisplay getIGemstoneDisplay() {
        return this;
    }

}
