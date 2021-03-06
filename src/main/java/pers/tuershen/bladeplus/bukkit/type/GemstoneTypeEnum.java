package pers.tuershen.bladeplus.bukkit.type;

import com.tuershen.nbtlibrary.minecraft.nbt.TagBase;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.gemstone.IBetGemstone;
import pers.tuershen.bladeplus.api.gemstone.IRepairGemstone;
import pers.tuershen.bladeplus.api.gemstone.ISpecialGemstone;
import pers.tuershen.bladeplus.bukkit.common.BladePlusGemstone;
import pers.tuershen.bladeplus.bukkit.common.BladePlusMaterial;
import pers.tuershen.bladeplus.bukkit.common.CalculationResult;
import pers.tuershen.bladeplus.util.Calculation;

import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/2/9
 * 强化宝石
 */
public enum GemstoneTypeEnum {

    //保护石 强化失败不会掉属性
    PROTECT(1) {
        @Override
        public CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType) {
            if (forgingResultType == ResultTypeEnum.FAIL) {
                blade.setFail(0);
                if (this.checkPlayer()) {
                    List<String> successMsg = setting.getIYamlSladePlusGemstone().getIProtectGemstone().getSuccessMsg();
                    for (String msg : successMsg) {
                        player.sendMessage(msg);
                    }
                }
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }

        @Override
        public int consumeCount(ResultTypeEnum resultType) {
            return resultType == ResultTypeEnum.FAIL ? 1 : 0;
        }
    },
    //幸运石 增加强化成功的几率
    LUCKY(2) {
        @Override
        public CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType) {
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            double probability = blade.getProbability();
            double temp = this.checkDouble("probability") + probability;
            double gemstoneProbability = Math.min(temp, 100d);
            blade.setProbability(gemstoneProbability);
            if (this.checkPlayer()) {
                List<String> successMsg = setting.getIYamlSladePlusGemstone().getILuckyGemstone().getSuccessMsg();
                for (String msg : successMsg) {
                    player.sendMessage(msg
                            .replace("%probability%", String.valueOf(this.checkDouble("probability")))
                            .replace("%max_probability%", String.valueOf(gemstoneProbability)));
                }
            }
            return new BladePlusGemstone(blade, 1);
        }

        @Override
        public int consumeCount(ResultTypeEnum resultType) {
            return 0;
        }
    },
    //异类石 有几率随机增加拔刀的基础伤害值，杀敌数，以及随机剑气颜色
    SPECIAL(3) {
        @Override
        public CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType) {
            double probability = checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            ISpecialGemstone iSpecialGemstone = this.setting.getIYamlSladePlusGemstone().getISpecialGemstone();
            if (resultType == ResultTypeEnum.SUCCESS) {
                int max = checkInt("max");
                int min = checkInt("min");
                SpecialTypeEnum specialTypeEnum = SpecialTypeEnum.getRandomSpecialAttribute(iSpecialGemstone);
                int specialType = specialTypeEnum.getSpecialType(max, min);
                String attributeType = specialTypeEnum.getAttributeName();
                TagBase newBase = null;
                if (tag.hasKey(attributeType)) {
                    Map<String, TagBase> map = tag.getMap();
                    if (map.containsKey(attributeType)) {
                        Number data = (Number) map.get(attributeType).data();
                        newBase = specialTypeEnum.createTag(data, specialType);
                    }
                } else {
                    newBase = specialTypeEnum.createTag(0, specialType);
                }
                tag.set(attributeType, newBase);
                if (checkPlayer()) {
                    List<String> successMsg = iSpecialGemstone.getSuccessMsg();
                    for (String msg : successMsg)
                        this.player.sendMessage(msg.replace("%specialType%", specialTypeEnum.getSpecialDisplay()).replace("%value%", String.valueOf(specialType)));
                }
            } else if (checkPlayer()) {
                List<String> failMsg = iSpecialGemstone.getFailMsg();
                for (String msg : failMsg)
                    this.player.sendMessage(msg);
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }

        @Override
        public int consumeCount(ResultTypeEnum resultType) {
            return 1;
        }
    },
    //赌石 有几率翻倍增加拔刀剑的属性，但也可能翻倍扣除拔刀剑的属性
    BET(4) {
        @Override
        public CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType) {
            double probability = this.checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            IBetGemstone iBetGemstone = this.setting.getIYamlSladePlusGemstone().getIBetGemstone();
            double magnification = this.checkDouble("magnification");
            int afterRepairCounter;
            int afterProudSoul;
            if (resultType == ResultTypeEnum.SUCCESS) {
                afterRepairCounter = (int) (blade.getRepairCounter() * magnification);
                afterProudSoul = (int) (blade.getProudSoul() * magnification);
                int tempRepairCounter = blade.getRepairCounter();
                int tempProudSoul = blade.getProudSoul();
                blade.setRepairCounter(afterRepairCounter);
                blade.setProudSoul(afterProudSoul);
                if (this.checkPlayer()) {
                    List<String> successMsg = iBetGemstone.getSuccessMsg();
                    for (String msg : successMsg) {
                        player.sendMessage(msg
                                .replace("%previously_repairRepairCounter%", String.valueOf(tempRepairCounter))
                                .replace("%previously_proudSoul%", String.valueOf(tempProudSoul))
                                .replace("%after_repairRepairCounter%", String.valueOf(afterRepairCounter))
                                .replace("%after_proudSoul%", String.valueOf(afterProudSoul))
                                .replace("%magnification%", String.valueOf(magnification)));
                    }
                }
            } else {
                if (this.checkPlayer()) {
                    afterRepairCounter = blade.getRepairCounter() - ((int) (blade.getRepairCounter() * magnification));
                    afterProudSoul = blade.getProudSoul() - ((int) (blade.getProudSoul() * magnification));
                    int tempRepairCounter = blade.getRepairCounter();
                    int tempProudSoul = blade.getProudSoul();
                    blade.setRepairCounter(Math.max(afterRepairCounter, 0));
                    blade.setProudSoul(Math.max(afterProudSoul, 0));
                    List<String> failMsg = iBetGemstone.getFailMsg();
                    for (String msg : failMsg) {
                        player.sendMessage(msg
                                .replace("%previously_repairRepairCounter%", String.valueOf(tempRepairCounter))
                                .replace("%previously_proudSoul%", String.valueOf(tempProudSoul))
                                .replace("%after_repairRepairCounter%", String.valueOf(afterRepairCounter))
                                .replace("%after_proudSoul%", String.valueOf(afterProudSoul))
                                .replace("%magnification%", String.valueOf(magnification)));
                    }
                }
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }

        @Override
        public int consumeCount(ResultTypeEnum resultType) {
            return 1;
        }
    },
    //封印石
    REPAIR(5) {
        @Override
        public CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType) {
            double probability = this.checkDouble("probability");
            ResultTypeEnum resultType = Calculation.getResultType(probability);
            IRepairGemstone iRepairGemstone = this.setting.getIYamlSladePlusGemstone().getIRepairGemstone();
            if (resultType == ResultTypeEnum.SUCCESS) {
                int repair = this.checkInt("repair");
                tag = setMaxRepairCounter(tag, repair);
                if (this.checkPlayer()) {
                    List<String> successMsg = iRepairGemstone.getSuccessMsg();
                    for (String msg : successMsg) {
                        player.sendMessage(msg
                                .replace("%repair%", String.valueOf(repair))
                                .replace("%max_repair%", String.valueOf(tag.getInt("MaxRepairCounter").getInt())));
                    }
                }
            } else {
                if (this.checkPlayer()) {
                    List<String> failMsg = iRepairGemstone.getFailMsg();
                    for (String msg : failMsg) {
                        player.sendMessage(msg);
                    }
                }
            }
            return new CalculationResult(tag, blade);
        }

        @Override
        public BladePlusGemstone pretreatment(BladePlusMaterial blade) {
            return new BladePlusGemstone(blade, 0);
        }

        @Override
        public int consumeCount(ResultTypeEnum resultType) {
            return 1;
        }
    };

    private final int type;

    TagCompound<TagBase> gemstoneMate;

    IYamlSetting setting;

    Player player;

    GemstoneTypeEnum(int type) {
        this.type = type;
    }

    public abstract CalculationResult handleGemstone(TagCompound<TagBase> tag, BladePlusMaterial blade, ResultTypeEnum forgingResultType);

    public abstract BladePlusGemstone pretreatment(BladePlusMaterial blade);

    public abstract int consumeCount(ResultTypeEnum resultType);

    protected double checkDouble(String key) {
        return this.gemstoneMate.hasKey(key) ? this.gemstoneMate.getDouble(key).getDouble() : 0.0d;
    }

    protected int checkInt(String key) {
        return this.gemstoneMate.hasKey(key) ? this.gemstoneMate.getInt(key).getInt() : 0;
    }

    protected boolean checkPlayer() {
        return this.player != null;
    }


    protected TagCompound<TagBase> setMaxRepairCounter(TagCompound<TagBase> tag, int repair) {
        if (tag.hasKey("MaxRepairCounter")) {
            TagInt maxRepairCounter = tag.getInt("MaxRepairCounter");
            maxRepairCounter.setData(maxRepairCounter.getInt() + repair);
            tag.setInt("MaxRepairCounter", maxRepairCounter);
            return tag;
        }
        tag.setInt("MaxRepairCounter", new TagInt(this.setting.getMaxRepairCounter() + repair));
        return tag;
    }

    public static GemstoneTypeEnum getInstance(int type, TagCompound<TagBase> gemstoneMate, IYamlSetting setting, Player player) {
        GemstoneTypeEnum[] gemstoneTypeEnums = GemstoneTypeEnum.values();
        for (GemstoneTypeEnum gemstoneTypeEnum : gemstoneTypeEnums) {
            if (gemstoneTypeEnum.type == type) {
                gemstoneTypeEnum.setGemstoneMate(gemstoneMate);
                gemstoneTypeEnum.setSetting(setting);
                gemstoneTypeEnum.setPlayer(player);
                return gemstoneTypeEnum;
            }
        }
        GemstoneTypeEnum gemstoneTypeEnum = GemstoneTypeEnum.PROTECT;
        gemstoneTypeEnum.setGemstoneMate(gemstoneMate);
        return gemstoneTypeEnum;
    }

    private void setSetting(IYamlSetting setting) {
        this.setting = setting;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private void setGemstoneMate(TagCompound<TagBase> gemstoneMate) {
        this.gemstoneMate = gemstoneMate;
    }
}
