package pers.tuershen.bladeplus.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.appraisal.IYamlAppraisalStone;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public class YamlAppraisalStone  extends YamlReset implements IYamlAppraisalStone {

    private List<String> modelMap = new ArrayList<>();

    public YamlAppraisalStone(){
        registerYamlReset(this);
    }

    @Override
    public void init() {
        FileConfiguration fileConfiguration = BladePlusMain.bladePlusMain.getConfig();
        this.modelMap = fileConfiguration.getStringList("appraisal");
    }

    @Override
    public void reload() {
        BladePlusMain.bladePlusMain.reloadConfig();
        this.modelMap = new ArrayList<>();
        this.init();


    }

    @Override
    public boolean hasAppraisalStone(String itemType) {
        for (int i = 0; i < this.modelMap.size(); i++) {
            if (this.modelMap.get(i).equalsIgnoreCase(itemType)) return true;
        }
        return false;
    }
}
