package pers.tuershen.bladeplus.api.gemstone;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public interface ISpecialGemstone extends IGemstone {

    String getKillCountMsg();

    String getProudSoulMsg();

    String getBaseAttackModifier();

    String getSummonedSwordColor();

    List<String> getFailMsg();


}
