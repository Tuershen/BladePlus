package pers.tuershen.bladeplus.core.yaml;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlBladeSkillType;
import pers.tuershen.bladeplus.core.type.SEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class YamlBladeSkillType extends YamlReset implements IYamlBladeSkillType {


    private List<Integer> saBlacklist = new ArrayList<>();

    private List<Integer> saRandoms = new ArrayList<>();

    private List<String> seBlackList = new ArrayList<>();

    public YamlBladeSkillType() {
        registerYamlReset(this);
    }

    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.attackType);
        this.saBlacklist = fileConfiguration.getIntegerList("SABlacklist");
        this.saRandoms = fileConfiguration.getIntegerList("SARandoms");
        this.seBlackList = fileConfiguration.getStringList("SEBlacklist");
    }

    @Override
    public void reload() {
        BladePlusMain.attackType.reloadConfig();
        init();
    }


    @Override
    public boolean isBanSA(int id) {
        for (Integer integer : saBlacklist) {
            if (integer == id) return true;
        }
        return false;
    }

    public SEffectType isGetBanSE(TagCompound<TagInt> effect) {
        Map<String, TagInt> effectMap = effect.getMap();
        for (Map.Entry<String, TagInt> entry : effectMap.entrySet()) {
            for (String type : this.seBlackList) {
                if (entry.getKey().equalsIgnoreCase(type))
                    return SEffectType.VALUE.sEffectType(entry.getKey());
            }
        }
        return SEffectType.ERROR;
    }

    @Override
    public int getRandomSA() {
        return saRandoms.get((new Random()).nextInt(saRandoms.size() - 1));
    }

    @Override
    public void addSA(int id) {
        if (!isBanSA(id)) {
            this.saBlacklist.add(id);
            this.fileConfiguration.set("SABlacklist", this.saBlacklist);
            BladePlusMain.attackType.saveConfig();
        }
    }

    @Override
    public void addSEffects(List<String> effects) {
        for (String effect : effects) {
            if (isExist(effect)) continue;
            this.seBlackList.add(effect);
        }
        this.fileConfiguration.set("SEBlacklist", this.saBlacklist);
        BladePlusMain.attackType.saveConfig();
    }

    private boolean isExist(String se) {
        for (String type : this.seBlackList) {
            if (type.equalsIgnoreCase(se)) return true;
        }
        return false;
    }


}
