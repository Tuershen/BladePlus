package pers.tuershen.bladeplus.common;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class BladePlusInventoryAttribute {

    private String title;

    private BladePlusItem firstItem;

    private BladePlusItem failItem;

    private int materialSlot;

    private BladePlusButton button;

    private int gemstoneSlot;

    public String getTitle() {
        return title;
    }

    public BladePlusItem getFirstItem() {
        return firstItem;
    }

    public BladePlusItem getFailItem() {
        return failItem;
    }

    public int getMaterialSlot() {
        return materialSlot;
    }

    public BladePlusButton getButton() {
        return button;
    }

    public int getGemstoneSlot() {
        return gemstoneSlot;
    }


    public BladePlusInventoryAttribute setTitle(String title) {
        this.title = title;
        return this;
    }

    public BladePlusInventoryAttribute setFirstItem(BladePlusItem firstItem) {
        this.firstItem = firstItem;
        return this;
    }

    public BladePlusInventoryAttribute setFailItem(BladePlusItem failItem) {
        this.failItem = failItem;
        return this;
    }

    public BladePlusInventoryAttribute setMaterialSlot(int materialSlot) {
        this.materialSlot = materialSlot;
        return this;
    }

    public BladePlusInventoryAttribute setButton(BladePlusButton button) {
        this.button = button;
        return this;
    }

    public BladePlusInventoryAttribute setGemstoneSlot(int gemstoneSlot) {
        this.gemstoneSlot = gemstoneSlot;
        return this;
    }
}
