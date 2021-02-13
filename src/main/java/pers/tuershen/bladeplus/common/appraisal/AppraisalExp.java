package pers.tuershen.bladeplus.common.appraisal;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalExp {

    private String exp;

    private int max;

    private int min;

    public String getExp() {
        return exp;
    }

    public AppraisalExp setExp(String exp) {
        this.exp = exp;
        return this;
    }

    public int getMax() {
        return max;
    }

    public AppraisalExp setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public AppraisalExp setMin(int min) {
        this.min = min;
        return this;
    }
}
