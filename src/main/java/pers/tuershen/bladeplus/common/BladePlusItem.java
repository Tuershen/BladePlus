package pers.tuershen.bladeplus.common;

/**
 * @auther Tuershen Create Date on 2021/2/7
 */
public class BladePlusItem {

    private String type;

    private short dur;

    public BladePlusItem(String type, short dur){
        this.type = type;
        this.dur = dur;
    }

    public BladePlusItem(String type) {
        this(type, (short) 0);
    }

    public String getType() {
        return type;
    }

    public short getDur() {
        return dur;
    }
}
