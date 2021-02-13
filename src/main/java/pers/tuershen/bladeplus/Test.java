package pers.tuershen.bladeplus;

import pers.tuershen.bladeplus.util.Calculation;

/**
 * @auther Tuershen Create Date on 2021/2/14
 */
public class Test {

    public static void main(String[] args) {
        for (int i = 0; i < 100 ; i++) {
            System.out.println(Calculation.getRandomNumber(1, 5));
        }

    }

}
