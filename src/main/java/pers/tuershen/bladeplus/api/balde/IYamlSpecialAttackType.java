package pers.tuershen.bladeplus.api.balde;

import pers.tuershen.bladeplus.api.IYamlReset;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public interface IYamlSpecialAttackType extends IYamlReset {

    boolean isBanSA(int id);

    int getRandomSA();

    void addSA(int id);
}
