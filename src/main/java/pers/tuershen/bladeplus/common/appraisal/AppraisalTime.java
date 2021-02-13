package pers.tuershen.bladeplus.common.appraisal;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalTime {

    private String time;

    private int max;

    private int min;

    public String getTime() {
        return time;
    }

    public AppraisalTime setTime(String time) {
        this.time = time;
        return this;
    }

    public int getMax() {
        return max;
    }

    public AppraisalTime setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public AppraisalTime setMin(int min) {
        this.min = min;
        return this;
    }
}
