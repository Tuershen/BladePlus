package pers.tuershen.bladeplus.common.hanlde;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.entity.BladePlusHandle;
import pers.tuershen.bladeplus.type.GemstoneTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class FailHandle extends AbstractHandle {

    public FailHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        super(plusHandle, setting, player);
    }

    @Override
    public void handleEvent() {
        NBTTagCompoundApi nbtTagCompound = plusHandle.getEntityNBTTagCompoundApi().getNBTTagCompound();
        TagCompound blade = nbtTagCompound.get("Blade");
        TagCompound tag = blade.getCompound("tag");
        int repairCounter = 0;
        if (tag.hasKey("RepairCounter")) {
            repairCounter = tag.getInt("RepairCounter").getInt() - plusHandle.getBladePlusMaterial().getFail();
        }
        tag.set("RepairCounter", new TagInt(Math.max(repairCounter, 0)));
        blade.set("tag", tag);
        nbtTagCompound.set("Blade", blade);
        plusHandle.getEntityNBTTagCompoundApi().saveNBTTag(nbtTagCompound);
    }

    @Override
    public void handleGemstoneEvent(GemstoneTypeEnum gemstoneTypeEnum) {

    }


}
