package pers.tuershen.bladeplus.common.appraisal;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class AppraisalMaterial {

    private String display;

    private List<String> lore;

    private String model;

    private AppraisalExp appraisalExp;

    private AppraisalFail appraisalFail;

    private AppraisalProbability appraisalProbability;

    private AppraisalProudSoul appraisalProudSoul;

    private AppraisalRepairCounter appraisalRepairCounter;

    private AppraisalTime appraisalTime;

    public AppraisalExp getAppraisalExp() {
        return appraisalExp;
    }

    public AppraisalMaterial setAppraisalExp(AppraisalExp appraisalExp) {
        this.appraisalExp = appraisalExp;
        return this;
    }

    public AppraisalFail getAppraisalFail() {
        return appraisalFail;
    }

    public AppraisalMaterial setAppraisalFail(AppraisalFail appraisalFail) {
        this.appraisalFail = appraisalFail;
        return this;
    }

    public AppraisalProbability getAppraisalProbability() {
        return appraisalProbability;
    }

    public AppraisalMaterial setAppraisalProbability(AppraisalProbability appraisalProbability) {
        this.appraisalProbability = appraisalProbability;
        return this;
    }

    public AppraisalProudSoul getAppraisalProudSoul() {
        return appraisalProudSoul;
    }

    public AppraisalMaterial setAppraisalProudSoul(AppraisalProudSoul appraisalProudSoul) {
        this.appraisalProudSoul = appraisalProudSoul;
        return this;
    }

    public AppraisalRepairCounter getAppraisalRepairCounter() {
        return appraisalRepairCounter;
    }

    public AppraisalMaterial setAppraisalRepairCounter(AppraisalRepairCounter appraisalRepairCounter) {
        this.appraisalRepairCounter = appraisalRepairCounter;
        return this;
    }

    public AppraisalTime getAppraisalTime() {
        return appraisalTime;
    }

    public AppraisalMaterial setAppraisalTime(AppraisalTime appraisalTime) {
        this.appraisalTime = appraisalTime;
        return this;
    }

    public String getModel() {
        return model;
    }

    public AppraisalMaterial setModel(String model) {
        this.model = model;
        return this;
    }

    public List<String> getLore() {
        return lore;
    }

    public AppraisalMaterial setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public String getDisplay() {
        return display;
    }

    public AppraisalMaterial setDisplay(String display) {
        this.display = display;
        return this;
    }

    public AppraisalMaterial build(){
        return this;
    }



}
