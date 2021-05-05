package pers.tuershen.bladeplus.util;

import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;

import java.util.Random;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class Calculation {


    public static int getRandomNumber(int min, int max) {
        if (min <= 0) {
            return 1;
        }
        if (min == max) {
            return max;
        }
        if (min > max) {
            int maxValue = max;
            max = min;
            min = maxValue;
        }

        return (new Random()).nextInt(max - min) + min;
    }

    public static ResultTypeEnum getResultType(double probability) {
        double random = (double) (new Random()).nextInt(99) + (new Random()).nextDouble();
        return probability > random ? ResultTypeEnum.SUCCESS : ResultTypeEnum.FAIL;
    }


}
