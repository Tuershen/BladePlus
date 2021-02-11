package pers.tuershen.bladeplus.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class ForgingGuiSetting {

    private int slot;

    private String material;

    private short mate;

    private String display;

    private List<String> lore;

    public ForgingGuiSetting(int slot) {
        this(slot, "GRASS");
    }

    public ForgingGuiSetting(int slot, String material) {
        this(slot, material, (short) 0);
    }

    public ForgingGuiSetting(int slot, String material, short mate) {
        this(slot, material, mate, "", new ArrayList<>());
    }

    public ForgingGuiSetting(int slot, String material, short mate, String display, List<String> lore) {
        this.slot = slot;
        this.material = material;
        this.mate = mate;
        this.display = display;
        this.lore = lore;
    }


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













}
