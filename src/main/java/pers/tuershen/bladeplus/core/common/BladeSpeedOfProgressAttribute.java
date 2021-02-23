package pers.tuershen.bladeplus.core.common;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class BladeSpeedOfProgressAttribute {

    private String material;

    private short mate;

    private String display;

    public String getMaterial() {
        return material;
    }

    public short getMate() {
        return mate;
    }

    public String getDisplay() {
        return display;
    }

    public BladeSpeedOfProgressAttribute setMaterial(String material) {
        this.material = material;
        return this;
    }

    public BladeSpeedOfProgressAttribute setMate(short mate) {
        this.mate = mate;
        return this;
    }

    public BladeSpeedOfProgressAttribute setDisplay(String display) {
        this.display = display;
        return this;
    }
}
