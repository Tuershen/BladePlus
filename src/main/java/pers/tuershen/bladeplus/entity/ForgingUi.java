package pers.tuershen.bladeplus.entity;

/**
 * @auther Tuershen Create Date on 2021/2/7
 */
public class ForgingUi {

    private String type;

    private short dur;

    public ForgingUi(String type, short dur){
        this.type = type;
        this.dur = dur;
    }

    public ForgingUi(String type) {
        this(type, (short) 0);
    }

    public String getType() {
        return type;
    }

    public short getDur() {
        return dur;
    }
}
