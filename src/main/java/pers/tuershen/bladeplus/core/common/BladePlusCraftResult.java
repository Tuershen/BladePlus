package pers.tuershen.bladeplus.core.common;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/13
 */
public class BladePlusCraftResult {

    private List<String> nbtKeys;

    private boolean hasBladePlusNBTKeys = false;


    public List<String> getNbtKeys() {
        return nbtKeys;
    }

    public boolean isHasBladePlusNBTKeys() {
        return hasBladePlusNBTKeys;
    }

    public void setHasBladePlusNBTKeys(boolean hasBladePlusNBTKeys) {
        if (this.hasBladePlusNBTKeys) return;
        this.hasBladePlusNBTKeys = hasBladePlusNBTKeys;
    }

    public void addKeys(String key) {
        this.nbtKeys.add(key);
    }


}
