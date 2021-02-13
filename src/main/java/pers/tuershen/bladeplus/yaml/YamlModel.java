package pers.tuershen.bladeplus.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.common.ForgingModel;
import org.bukkit.configuration.MemorySection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public class YamlModel extends YamlReset implements IYamlModel {

    private Map<String, ForgingModel> modelMap = new HashMap<>();

    public YamlModel() {
        registerYamlReset(this);
    }

    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.modelFile);
        MemorySection memorySection = (MemorySection) fileConfiguration.get("model");
        memorySection.getKeys(false).forEach((key -> modelMap.put(key, new ForgingModel()
                .setModelPath(fileConfiguration.getString("model." + key + ".modelPath"))
                .setTextPath(fileConfiguration.getString("model." + key + ".textPath")))));
    }

    @Override
    public void reload() {
        BladePlusMain.modelFile.reloadConfig();
        this.modelMap = new HashMap<>();
        this.init();

    }

    @Override
    public boolean hasModel(String key) {
        return this.modelMap.containsKey(key);
    }

    @Override
    public ForgingModel getModel(String key) {
        return this.modelMap.get(key);
    }

    @Override
    public String getModelKey(String modelKey) {
        for (String key : modelMap.keySet()) {
            if (key.equalsIgnoreCase(modelKey)) return key;
        }
        return "无";
    }

    @Override
    public List<String> getModelKeys() {
        return new ArrayList<>(this.modelMap.keySet());
    }


}
