package pers.tuershen.bladeplus.common;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class CalculationResult {

    private TagCompound tag;

    private BladePlusMaterial bladePlusMaterial;

    public CalculationResult(TagCompound tag, BladePlusMaterial bladePlusMaterial) {
        this.tag = tag;
        this.bladePlusMaterial = bladePlusMaterial;
    }

    public TagCompound getTag() {
        return tag;
    }

    public void setTag(TagCompound tag) {
        this.tag = tag;
    }

    public BladePlusMaterial getBladePlusMaterial() {
        return bladePlusMaterial;
    }

    public void setBladePlusMaterial(BladePlusMaterial bladePlusMaterial) {
        this.bladePlusMaterial = bladePlusMaterial;
    }
}
