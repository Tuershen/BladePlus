package pers.tuershen.bladeplus.common;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class BladePlusButton {

    private int slot;

    private String material;

    private short mate;

    private String display;

    private List<String> lore;

    public int getSlot() {
        return slot;
    }

    public String getMaterial() {
        return material;
    }

    public short getMate() {
        return mate;
    }

    public String getDisplay() {
        return display;
    }

    public List<String> getLore() {
        return lore;
    }

    public BladePlusButton setSlot(int slot) {
        this.slot = slot;
        return this;
    }

    public BladePlusButton setMaterial(String material) {
        this.material = material;
        return this;
    }

    public BladePlusButton setMate(short mate) {
        this.mate = mate;
        return this;
    }

    public BladePlusButton setDisplay(String display) {
        this.display = display;
        return this;
    }

    public BladePlusButton setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }


}
