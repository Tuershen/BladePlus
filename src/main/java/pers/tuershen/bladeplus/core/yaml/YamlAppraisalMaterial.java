package pers.tuershen.bladeplus.core.yaml;

import org.bukkit.configuration.MemorySection;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalMaterial;
import pers.tuershen.bladeplus.core.common.appraisal.*;

import java.util.*;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class YamlAppraisalMaterial extends YamlReset implements IYamlAppraisalMaterial {

    private Map<String, AppraisalMaterial> appraisalMaterialMap = new HashMap<>();

    public YamlAppraisalMaterial(){
        registerYamlReset(this);
    }

    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.appraisalFile);
        MemorySection memorySection = (MemorySection) fileConfiguration.get("appraisal_material");
        memorySection.getKeys(false).forEach((key -> {
            AppraisalExp appraisalExp = new AppraisalExp()
                    .setExp("exp")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".exp.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".exp.min"));
            AppraisalFail appraisalFail = new AppraisalFail()
                    .setFail("fail")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".fail.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".fail.min"));
            AppraisalProbability appraisalProbability = new AppraisalProbability()
                    .setProbability("probability")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".probability.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".probability.min"));
            AppraisalProudSoul appraisalProudSoul = new AppraisalProudSoul()
                    .setProudSoul("proudSoul")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".proudSoul.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".proudSoul.min"));
            AppraisalRepairCounter appraisalRepairCounter = new AppraisalRepairCounter()
                    .setRepairCounter("repairCounter")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".repairCounter.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".repairCounter.min"));
            AppraisalTime appraisalTime = new AppraisalTime()
                    .setTime("time")
                    .setMax(fileConfiguration.getInt("appraisal_material."+ key +".time.max"))
                    .setMin(fileConfiguration.getInt("appraisal_material."+ key +".time.min"));
            appraisalMaterialMap.put(key, new AppraisalMaterial()
                    .setAppraisalExp(appraisalExp)
                    .setAppraisalFail(appraisalFail)
                    .setAppraisalProbability(appraisalProbability)
                    .setAppraisalProudSoul(appraisalProudSoul)
                    .setAppraisalRepairCounter(appraisalRepairCounter)
                    .setAppraisalTime(appraisalTime)
                    .setDisplay(fileConfiguration.getString("appraisal_material."+ key +".display"))
                    .setLore(fileConfiguration.getStringList("appraisal_material."+ key +".lore"))
                    .setModel(fileConfiguration.getString("appraisal_material."+ key +".model"))
                    .build());
        }));
    }

    @Override
    public void reload() {
        this.appraisalMaterialMap = new HashMap<>();
        BladePlusMain.appraisalFile.reloadConfig();
        init();
    }


    @Override
    public boolean hasAppraisalMaterial(String appraisal) {
        return this.appraisalMaterialMap.containsKey(appraisal);
    }

    @Override
    public AppraisalMaterial getAppraisalMaterial(String appraisal) {
        return this.appraisalMaterialMap.get(appraisal);
    }

    @Override
    public List<String> getAppraisalSchemes() {
        return new ArrayList<>(this.appraisalMaterialMap.keySet());
    }



}
