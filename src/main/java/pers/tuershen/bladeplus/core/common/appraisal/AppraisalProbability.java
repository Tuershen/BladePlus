package pers.tuershen.bladeplus.core.common.appraisal;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalProbability {

    private String probability ;

    private int max;

    private int min;


    public String getProbability() {
        return probability;
    }

    public AppraisalProbability setProbability(String probability) {
        this.probability = probability;
        return this;
    }

    public int getMax() {
        return max;
    }

    public AppraisalProbability setMax(int max) {
        this.max = max;
        return this;
    }

    public int getMin() {
        return min;
    }

    public AppraisalProbability setMin(int min) {
        this.min = min;
        return this;
    }
}
