package pers.tuershen.bladeplus.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlSpecialAttackType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class YamlSpecialAttackType extends YamlReset implements IYamlSpecialAttackType {


    private List<Integer> blacklist = new ArrayList<>();

    private List<Integer> randoms = new ArrayList<>();

    public YamlSpecialAttackType() {
        registerYamlReset(this);
    }

    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.attackType);
        this.blacklist = fileConfiguration.getIntegerList("blacklist");
        this.randoms = fileConfiguration.getIntegerList("randoms");
    }

    @Override
    public void reload() {
        BladePlusMain.attackType.reloadConfig();
        init();
    }


    @Override
    public boolean isBanSA(int id) {
        for (int i = 0; i < blacklist.size(); i++) {
            if ((blacklist.get(i)) == id) return true;
        }
        return false;
    }

    @Override
    public int getRandomSA() {
        return  randoms.get((new Random()).nextInt(randoms.size() - 1));
    }

    @Override
    public void addSA(int id) {
        if (!isBanSA(id)) {
            this.blacklist.add(id);
            this.fileConfiguration.set("blacklist", this.blacklist);
            BladePlusMain.attackType.saveConfig();
        }
    }


}
