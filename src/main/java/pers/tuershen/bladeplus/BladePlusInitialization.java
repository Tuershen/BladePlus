package pers.tuershen.bladeplus;

import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.command.AdminCommandHandle;
import pers.tuershen.bladeplus.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.command.admin.*;
import pers.tuershen.bladeplus.command.player.PCommandAppraisal;
import pers.tuershen.bladeplus.command.player.PCommandHelp;
import pers.tuershen.bladeplus.command.player.PCommandOpen;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class BladePlusInitialization {

    public static void initPlayerCommands(IYamlSetting iYamlSetting){
        PlayerCommandHandle.registerCommandHandle(new PCommandAppraisal(iYamlSetting));
        PlayerCommandHandle.registerCommandHandle(new PCommandOpen(iYamlSetting));
        PlayerCommandHandle.registerCommandHandle(new PCommandHelp(iYamlSetting));
    }


    public static void initAdminCommands(IYamlSetting iYamlSetting) {
        AdminCommandHandle.registerAdminCommandHandle(new ACommandAppraisal(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandMaterial(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandMaxRepairRepairCounter(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandModel(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandProtect(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandHelp(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandReload(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandVersion(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandLucky(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandBet(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandSpecial(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandBladeModel(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandKillCount(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandProudSoul(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandRepairCounter(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandSpecialAttackType(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandSummonedSwordColor(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandBaseAttackModifier(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandBanSA(iYamlSetting));
        AdminCommandHandle.registerAdminCommandHandle(new ACommandRepair(iYamlSetting));

    }






}
