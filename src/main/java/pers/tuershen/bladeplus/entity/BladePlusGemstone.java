package pers.tuershen.bladeplus.entity;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class BladePlusGemstone {

    private BladePlusMaterial bladePlus;

    private int count;

    public BladePlusGemstone(BladePlusMaterial bladePlus, int count) {
        this.bladePlus = bladePlus;
        this.count = count;
    }

    public BladePlusMaterial getBladePlus() {
        return bladePlus;
    }

    public void setBladePlus(BladePlusMaterial bladePlus) {
        this.bladePlus = bladePlus;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
