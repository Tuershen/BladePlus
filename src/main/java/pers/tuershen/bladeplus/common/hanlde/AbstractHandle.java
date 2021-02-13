package pers.tuershen.bladeplus.common.hanlde;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import com.tuershen.nbtlibrary.minecraft.nbt.TagList;
import com.tuershen.nbtlibrary.minecraft.nbt.TagString;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.common.BladePlusHandle;
import pers.tuershen.bladeplus.common.BladePlusMaterial;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.type.GemstoneTypeEnum;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public abstract class AbstractHandle extends ResultHandle {

    protected TagCompound blade;

    protected TagCompound tag;

    protected EntityNBTTagCompoundApi tagCompoundApi;

    protected NBTTagCompoundApi compoundApi;

    protected BladePlusMaterial bladePlusMaterial;

    public AbstractHandle(BladePlusHandle plusHandle, IYamlSetting setting, Player player) {
        super(plusHandle, setting, player);
        this.tagCompoundApi = plusHandle.getEntityNBTTagCompoundApi();
        this.compoundApi = this.tagCompoundApi.getNBTTagCompound();
        this.blade = tagCompoundApi.getNBTTagCompound().get("Blade");
        this.tag = blade.getCompound("tag");
        this.bladePlusMaterial = plusHandle.getBladePlusMaterial();
    }

    protected void sendMessage(String msg) {
        if (this.player != null) {
            player.sendMessage(msg);
        }
    }

    protected void saveBlade() {
        this.blade.set("tag", this.tag);
        this.compoundApi.set("Blade", this.blade);
        this.plusHandle.getEntityNBTTagCompoundApi().saveNBTTag(this.compoundApi);
    }


    public TagInt getProudSoul(int cumulativeProudSoul) {
        if(this.tag.hasKey("ProudSoul")){
            return new TagInt(this.tag.getInt("ProudSoul").getInt() + cumulativeProudSoul);
        }
        return new TagInt(cumulativeProudSoul);
    }

    public TagList<TagString> getBladeModel(){
        if (this.tag.hasKey("ForgingModel")){
            TagList<TagString> bladeModel = this.tag.getList("ForgingModel");
            bladeModel.setData(NBTWrite.putModel(bladeModel.getData(), bladePlusMaterial.getModel()));
            return bladeModel;
        }
        return new TagList<>();
    }

    public abstract void handleEvent();

    public abstract void handleGemstoneEvent(GemstoneTypeEnum gemstoneTypeEnum);


}
