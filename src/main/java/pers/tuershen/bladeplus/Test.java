package pers.tuershen.bladeplus;


import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;
import pers.tuershen.bladeplus.util.Calculation;

import java.util.Random;
import java.util.Scanner;

/**
 * @auther Tuershen Create Date on 2021/2/14
 */
public class Test {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (true) {
            int n = 0;
            System.out.println("输入测试次数:");
            int count = input.nextInt();
            System.out.println("输入测试几率：");
            double p = input.nextDouble();
            for (int i = 1; i <= count; i++) {
                ResultTypeEnum resultType = Calculation.getResultType(p);
                if (resultType == ResultTypeEnum.SUCCESS) {
                    n++;
                }
                System.out.println("第" + i + "次测试结果为" + resultType.name());
            }
            System.out.println("一共测试了" + count + "次");
            System.out.println("其中成功次数为" + n);
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }

    }
}
