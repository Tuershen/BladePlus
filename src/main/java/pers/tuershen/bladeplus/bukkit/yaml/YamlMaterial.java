package pers.tuershen.bladeplus.bukkit.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlMaterial;
import org.bukkit.configuration.MemorySection;
import pers.tuershen.bladeplus.bukkit.common.BladePlusMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public class YamlMaterial extends YamlReset implements IYamlMaterial {

    private Map<String, BladePlusMaterial> materialMap = new HashMap<>();

    public YamlMaterial() {
        registerYamlReset(this);
    }


    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.materialFile);
        MemorySection memorySection = (MemorySection) fileConfiguration.get("material");
        memorySection.getKeys(false).forEach((key -> materialMap.put(key, new BladePlusMaterial()
                .setDisplay(fileConfiguration.getString("material."+ key +".display"))
                .setLore(fileConfiguration.getStringList("material."+key+".lore"))
                .setExp(fileConfiguration.getInt("material."+key+".exp"))
                .setRepairCounter(fileConfiguration.getInt("material."+key+".repairCounter"))
                .setProudSoul(fileConfiguration.getInt("material."+key+".proudSoul"))
                .setTime(fileConfiguration.getDouble("material."+key+".time"))
                .setModel(fileConfiguration.getString("material."+key+".model"))
                .setProbability(fileConfiguration.getDouble("material."+key+".probability"))
                .setFail(fileConfiguration.getInt("material."+key+".fail"))
        )));
    }

    @Override
    public void reload() {
        this.materialMap = new HashMap<>();
        BladePlusMain.materialFile.reloadConfig();
        init();
    }

    @Override
    public BladePlusMaterial getBladePlusMaterial(String key) {
        return this.materialMap.get(key);
    }

    @Override
    public boolean hasBladePlusMaterial(String key) {
        return this.materialMap.containsKey(key);
    }

    @Override
    public List<String> getMaterialKeys(){
        return new ArrayList<>(this.materialMap.keySet());
    }




}
