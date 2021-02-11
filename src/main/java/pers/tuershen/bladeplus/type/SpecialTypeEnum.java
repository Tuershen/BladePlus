package pers.tuershen.bladeplus.type;

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
    },
    SUMMONED_SWORD_COLOR(2) {
        @Override
        public int getSpecialType(int max, int min) {
            return new Random().nextInt(0xffffffff) + 1;
        }

        @Override
        public String getAttributeName() {
            return "SummonedSwordColor";
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
    };

    int type;

    protected static Map<Integer, SpecialTypeEnum> specialTypeEnumMap = new HashMap<>();

    static {
        specialTypeEnumMap.put(1, SpecialTypeEnum.KILL_COUNT);
        specialTypeEnumMap.put(2, SpecialTypeEnum.SUMMONED_SWORD_COLOR);
        specialTypeEnumMap.put(3, SpecialTypeEnum.PROD_SOUL);
        specialTypeEnumMap.put(4, SpecialTypeEnum.BASE_ATTACK_MODIFIER);
    }

    SpecialTypeEnum(int type) {
        this.type = type;
    }

    public abstract int getSpecialType(int max, int min);

    public abstract String getAttributeName();

    public static SpecialTypeEnum getRandomSpecialAttribute() {
        int random = Calculation.getRandomNumber(1, 4);
        return specialTypeEnumMap.get(random);
    }


}
