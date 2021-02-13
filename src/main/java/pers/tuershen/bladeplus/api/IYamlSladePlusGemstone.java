package pers.tuershen.bladeplus.api;

import pers.tuershen.bladeplus.api.gemstone.*;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public interface IYamlSladePlusGemstone extends IYamlReset {

    IBetGemstone getIBetGemstone();

    ILuckyGemstone getILuckyGemstone();

    IProtectGemstone getIProtectGemstone();

    IRepairGemstone getIRepairGemstone();

    ISpecialGemstone getISpecialGemstone();




}
