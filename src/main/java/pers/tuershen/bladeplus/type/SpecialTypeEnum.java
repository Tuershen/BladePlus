package pers.tuershen.bladeplus.type;

import pers.tuershen.bladeplus.api.gemstone.ISpecialGemstone;
import pers.tuershen.bladeplus.util.Calculation;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public enum SpecialTypeEnum {

    KILL_COUNT(1) {
        @Override
        public int getSpecialType(int max, int min) {
            return Calculation.getRandomNumber(min, max);
        }

        @Override
        public String getAttributeName() {
            return "killCount";
        }

        @Override
        public String getSpecialDisplay() {
            return this.iSpecialGemstone.getKillCountMsg();
        }
    },
    SUMMONED_SWORD_COLOR(2) {
        @Override
        public int getSpecialType(int max, int min) {
            return new Random().nextInt(0xffffff);
        }

        @Override
        public String getAttributeName() {
            return "SummonedSwordColor";
        }

        @Override
        public String getSpecialDisplay() {
            return this.iSpecialGemstone.getSummonedSwordColor();
        }
    },
    PROD_SOUL(3) {
        @Override
        public int getSpecialType(int max, int min) {
            return Calculation.getRandomNumber(min, max);
        }

        @Override
        public String getAttributeName() {
            return "ProudSoul";
        }

        @Override
        public String getSpecialDisplay() {
            return this.iSpecialGemstone.getProudSoulMsg();
        }
    },
    BASE_ATTACK_MODIFIER(4) {
        @Override
        public int getSpecialType(int max, int min) {
            return Calculation.getRandomNumber(min, max);
        }

        @Override
        public String getAttributeName() {
            return "baseAttackModifier";
        }

        @Override
        public String getSpecialDisplay() {
            return this.iSpecialGemstone.getBaseAttackModifier();
        }
    },
    REPAIR_COUNTER(5) {
        @Override
        public int getSpecialType(int max, int min) {
            return  Calculation.getRandomNumber(min, max);
        }

        @Override
        public String getAttributeName() {
            return "RepairCounter";
        }

        @Override
        public String getSpecialDisplay() {
            return this.iSpecialGemstone.getRepairCounter();
        }
    };

    int type;

    ISpecialGemstone iSpecialGemstone;

    protected static Map<Integer, SpecialTypeEnum> specialTypeEnumMap = new HashMap<>();

    static {
        specialTypeEnumMap.put(1, SpecialTypeEnum.KILL_COUNT);
        specialTypeEnumMap.put(2, SpecialTypeEnum.SUMMONED_SWORD_COLOR);
        specialTypeEnumMap.put(3, SpecialTypeEnum.PROD_SOUL);
        specialTypeEnumMap.put(4, SpecialTypeEnum.BASE_ATTACK_MODIFIER);
        specialTypeEnumMap.put(5, SpecialTypeEnum.REPAIR_COUNTER);
    }

    SpecialTypeEnum(int type) {
        this.type = type;
    }

    public abstract int getSpecialType(int max, int min);

    public abstract String getAttributeName();

    public abstract String getSpecialDisplay();

    public static SpecialTypeEnum getRandomSpecialAttribute(ISpecialGemstone iSpecialGemstone) {
        int random = Calculation.getRandomNumber(1, 6);
        SpecialTypeEnum specialTypeEnum = specialTypeEnumMap.get(random);
        specialTypeEnum.setISpecialGemstone(iSpecialGemstone);
        return specialTypeEnum;
    }

    public void setISpecialGemstone(ISpecialGemstone iSpecialGemstone) {
        this.iSpecialGemstone = iSpecialGemstone;
    }
}
