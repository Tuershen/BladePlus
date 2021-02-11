package pers.tuershen.bladeplus.type;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.entity.BladePlusGemstone;
import pers.tuershen.bladeplus.entity.BladePlusMaterial;
import pers.tuershen.bladeplus.entity.CalculationResult;
import pers.tuershen.bladeplus.util.Calculation;

/**
 * @auther Tuershen Create Date on 2021/2/9
 * 强化宝石
 */
public enum GemstoneTypeEnum {

    //保护石 强化失败不会掉属性
    PROTECT(1) {
        @Override
        public CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade) {
            blade.setFail(0);
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }
    },
    //幸运石 增加强化成功的几率
    LUCKY(2) {
        @Override
        public CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade) {
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            double probability = blade.getProbability();
            double temp = this.checkDouble("probability") + probability;
            double gemstoneProbability = Math.min(temp, 100d);
            blade.setProbability(gemstoneProbability);
            return new BladePlusGemstone(blade, 1);
        }
    },
    //异类石 有几率随机增加拔刀的基础伤害值，杀敌数，以及随机剑气颜色
    SPECIAL(3) {
        @Override
        public CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade) {
            double probability = this.checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            if (resultType == ResultTypeEnum.SUCCESS) {
                int max = this.checkInt("max");
                int min = this.checkInt("min");
                SpecialTypeEnum specialTypeEnum = SpecialTypeEnum.getRandomSpecialAttribute();
                int specialType = specialTypeEnum.getSpecialType(max, min);
                tag.set(specialTypeEnum.getAttributeName(), new TagInt(specialType));
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }
    },
    //赌狗石 有几率翻倍增加拔刀剑的属性，但也可能翻倍扣除拔刀剑的属性
    BET(4) {
        @Override
        public CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade) {
            double probability = this.checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            if (resultType == ResultTypeEnum.SUCCESS) {
                int magnification = this.checkInt("magnification");
                blade.setRepairCounter(blade.getRepairCounter() * magnification);
                blade.setProudSoul(blade.getProudSoul() * magnification);
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }
    },
    //
    REPAIR(5){
        @Override
        public CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade) {
            double probability = this.checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            if (resultType == ResultTypeEnum.SUCCESS) {
                int repair = this.checkInt("repair");
                tag = setMaxRepairCounter(tag, repair);
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }
    };

    private int type;

    TagCompound gemstoneMate;

    IYamlSetting setting;

    GemstoneTypeEnum(int type) {
        this.type = type;
    }

    public abstract CalculationResult handleGemstone(TagCompound tag, BladePlusMaterial blade);

    public abstract BladePlusGemstone pretreatment(BladePlusMaterial blade);

    public double checkDouble(String key) {
        return this.gemstoneMate.hasKey(key) ? this.gemstoneMate.getDouble(key).getDouble() : 0.0d;
    }

    public int checkInt(String key) {
        return this.gemstoneMate.hasKey(key) ? this.gemstoneMate.getInt(key).getInt() : 0;
    }

    public TagCompound setMaxRepairCounter(TagCompound tag, int repair) {
        if(tag.hasKey("MaxRepairCounter")){
            TagInt maxRepairCounter = tag.getInt("MaxRepairCounter");
            maxRepairCounter.setData(maxRepairCounter.getInt() + repair);
            tag.setInt("MaxRepairCounter", maxRepairCounter);
            return tag;
        }
        tag.setInt("MaxRepairCounter", new TagInt(this.setting.getMaxRepairCounter() + repair));
        return tag;
    }

    public static GemstoneTypeEnum getInstance(int type, TagCompound gemstoneMate, IYamlSetting setting) {
        GemstoneTypeEnum[] gemstoneTypeEnums = GemstoneTypeEnum.values();
        for (GemstoneTypeEnum gemstoneTypeEnum : gemstoneTypeEnums) {
            if (gemstoneTypeEnum.type == type) {
                gemstoneTypeEnum.setGemstoneMate(gemstoneMate);
                gemstoneTypeEnum.setSetting(setting);
                return gemstoneTypeEnum;
            }
        }
        GemstoneTypeEnum gemstoneTypeEnum = GemstoneTypeEnum.PROTECT;
        gemstoneTypeEnum.setGemstoneMate(gemstoneMate);
        return gemstoneTypeEnum;
    }

    public void setSetting(IYamlSetting setting) {
        this.setting = setting;
    }


    public void setGemstoneMate(TagCompound gemstoneMate) {
        this.gemstoneMate = gemstoneMate;
    }
}
