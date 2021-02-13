package pers.tuershen.bladeplus.common.appraisal;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalRepairCounter {

    private String repairCounter;

    private int max;

    private int min;


    public String getRepairCounter() {
        return repairCounter;
    }

    public AppraisalRepairCounter setRepairCounter(String repairCounter) {
        this.repairCounter = repairCounter;
        return this;
    }

    public int getMax() {
        return max;
    }

    public AppraisalRepairCounter setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public AppraisalRepairCounter setMin(int min) {
        this.min = min;
        return this;
    }
}
