package pers.tuershen.bladeplus.core.common;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public class BladePlusMaterial {

    private String display;

    private List<String> lore;

    private int exp;

    private int repairCounter;

    private int proudSoul;

    private String model;

    private double time;

    private double probability;

    public BladePlusMaterial setFail(int fail) {
        this.fail = fail;
        return this;
    }

    private int fail;


    public String getDisplay() {
        return display;
    }

    public List<String> getLore() {
        return lore;
    }

    public BladePlusMaterial setDisplay(String display) {
        this.display = display;
        return this;
    }

    public int getExp() {
        return exp;
    }

    public int getRepairCounter() {
        return repairCounter;
    }

    public BladePlusMaterial setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public int getProudSoul() {
        return proudSoul;
    }


    public BladePlusMaterial setExp(int exp) {
        this.exp = exp;
        return this;
    }


    public String getModel() {
        return model;
    }

    public double getProbability() {
        return probability;
    }

    public BladePlusMaterial setRepairCounter(int repairCounter) {
        this.repairCounter = repairCounter;
        return this;
    }

    public BladePlusMaterial setProudSoul(int proudSoul) {
        this.proudSoul = proudSoul;
        return this;
    }

    public BladePlusMaterial setModel(String model) {
        this.model = model;
        return this;
    }

    public double getTime() {
        return time;
    }

    public BladePlusMaterial setTime(double time) {
        this.time = time;
        return this;
    }

    public BladePlusMaterial setProbability(double probability) {
        this.probability = probability;
        return this;
    }


    public int getFail() {
        return fail;
    }


    @Override
    public String toString() {
        return "BladePlusMaterial{" +
                "display='" + display + '\'' +
                ", lore=" + lore +
                ", exp=" + exp +
                ", repairCounter=" + repairCounter +
                ", proudSoul=" + proudSoul +
                ", model='" + model + '\'' +
                ", time=" + time +
                ", probability=" + probability +
                ", fail=" + fail +
                '}';
    }
}
