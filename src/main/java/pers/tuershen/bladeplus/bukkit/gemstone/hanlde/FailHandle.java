package pers.tuershen.bladeplus.bukkit.gemstone.hanlde;

import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.bukkit.common.BladePlusHandle;
import pers.tuershen.bladeplus.bukkit.common.CalculationResult;
import pers.tuershen.bladeplus.bukkit.type.GemstoneTypeEnum;
import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class FailHandle extends AbstractHandle {

    public FailHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        super(plusHandle, setting, player);
    }

    @Override
    public void handleEvent() {
        int repairCounter = 0;
        if (tag.hasKey("RepairCounter")) {
            repairCounter = tag.getInt("RepairCounter").getInt() - plusHandle.getBladePlusMaterial().getFail();
        }
        tag.set("RepairCounter", new TagInt(Math.max(repairCounter, 0)));
        this.saveBlade();
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
        return ResultTypeEnum.FAIL;
    }


}
