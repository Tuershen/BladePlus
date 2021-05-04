package pers.tuershen.bladeplus.bukkit.common;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class BladePlusAppraisalDisplay {

    private String display;

    private List<String> lore;

    public BladePlusAppraisalDisplay(String display, List<String> lore) {
        this.display = display;
        this.lore = lore;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }
}
