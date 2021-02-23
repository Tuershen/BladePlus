package pers.tuershen.bladeplus;

import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.command.AdminCommandHandle;
import pers.tuershen.bladeplus.core.command.PlayerCommandHandle;
import pers.tuershen.bladeplus.core.command.admin.*;
import pers.tuershen.bladeplus.core.command.player.*;

/**
 * @auther Tuershen Create Date on 2021/2/22
 */
public class BladePlusRegister {


    public static void initPlayerCommands(PlayerCommandHandle playerCommandHandle, IYamlSetting iYamlSetting) {
        playerCommandHandle.registerCommandHandle(new PCommandAppraisal(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandOpen(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandHelp(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandSeeBladeMaxRepairCounter(iYamlSetting));
        playerCommandHandle.registerCommandHandle(new PCommandVersion(iYamlSetting));
    }


    public static void initAdminCommands(AdminCommandHandle adminCommandHandle, IYamlSetting iYamlSetting) {
        adminCommandHandle.registerAdminCommandHandle(new ACommandAppraisal(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaterial(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandMaxRepairCounter(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandModel(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandProtect(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandHelp(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandReload(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandVersion(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandLucky(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBet(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSpecial(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBladeModel(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandKillCount(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandProudSoul(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandRepairCounter(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSpecialAttackType(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandSummonedSwordColor(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBaseAttackModifier(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBanSA(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandRepair(iYamlSetting));
        adminCommandHandle.registerAdminCommandHandle(new ACommandBanSE(iYamlSetting));

    }


}
