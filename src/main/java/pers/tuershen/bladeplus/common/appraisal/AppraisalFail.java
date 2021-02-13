package pers.tuershen.bladeplus.common.appraisal;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalFail {

    private String fail;

    private int max;

    private int min;


    public String getFail() {
        return fail;
    }

    public AppraisalFail setFail(String fail) {
        this.fail = fail;
        return this;
    }

    public int getMax() {
        return max;
    }

    public AppraisalFail setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public AppraisalFail setMin(int min) {
        this.min = min;
        return this;
    }
}
