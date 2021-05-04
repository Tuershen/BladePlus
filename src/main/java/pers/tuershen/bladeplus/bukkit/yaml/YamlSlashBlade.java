package pers.tuershen.bladeplus.bukkit.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlSlashBlade;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/31
 */
public class YamlSlashBlade extends YamlReset implements IYamlSlashBlade  {

    private List<String> slashBladeList = new ArrayList<>();

    public YamlSlashBlade() {
        registerYamlReset(this);
    }

    public void init() {
        fileConfiguration = BladePlusMain.bladePlusMain.getConfig();
        this.slashBladeList = fileConfiguration.getStringList("slashBlade");
    }

    public void reload(){
        BladePlusMain.bladePlusMain.reloadConfig();
        this.slashBladeList = new ArrayList<>();
        this.init();
    }

    public boolean isSlashBlade(String slashBlade){
        for (int i = 0; i < slashBladeList.size(); i++) {
            if (slashBlade.contains(slashBladeList.get(i))) return true;
         }
        return false;
    }

}
