package pers.tuershen.bladeplus.core.gemstone.hanlde;

import com.tuershen.nbtlibrary.minecraft.nbt.TagBase;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.check.RepairCounterCheck;
import pers.tuershen.bladeplus.api.IProgramme;
import pers.tuershen.bladeplus.core.common.BladePlusHandle;
import pers.tuershen.bladeplus.core.common.CalculationResult;
import pers.tuershen.bladeplus.core.type.GemstoneTypeEnum;
import pers.tuershen.bladeplus.core.type.ResultTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class SuccessHandle extends AbstractHandle {

    public SuccessHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        super(plusHandle, setting, player);
    }

    @Override
    public void handleEvent() {
        int repairCounter = this.bladePlusMaterial.getRepairCounter();
        int maxRepairCounter = this.setting.getMaxRepairCounter();
        RepairCounterCheck repairCounterCheck = new RepairCounterCheck(this.tag, repairCounter, maxRepairCounter);
        IProgramme iProgramme = repairCounterCheck.getRepairCounter();
        if (iProgramme.isAchieveLimit())
            sendMessage(this.setting.getIYamlMsg().getMsg("error_7"));
        this.tag.set("RepairCounter", new TagInt(iProgramme.getRepairCounter()));
        this.tag.set("ProudSoul", getProudSoul(this.bladePlusMaterial.getProudSoul()));
        this.tag.set("ForgingModel", getBladeModel());
        saveBlade();
    }

    @Override
    public void handleGemstoneEvent(GemstoneTypeEnum gemstoneTypeEnum) {
        CalculationResult result = gemstoneTypeEnum.handleGemstone(this.tag, this.bladePlusMaterial, this.getResultType());
        this.tag = result.getTag();
        this.bladePlusMaterial = result.getBladePlusMaterial();
        handleEvent();
    }

    @Override
    public ResultTypeEnum getResultType() {
        return ResultTypeEnum.SUCCESS;
    }


}
