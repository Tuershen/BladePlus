package pers.tuershen.bladeplus.core.gemstone.hanlde;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.*;
import org.bukkit.entity.Player;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.core.common.BladePlusHandle;
import pers.tuershen.bladeplus.core.common.BladePlusMaterial;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import pers.tuershen.bladeplus.core.type.GemstoneTypeEnum;
import pers.tuershen.bladeplus.core.type.ResultTypeEnum;

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
        String model = bladePlusMaterial.getModel();
        TagList<TagString> modelList = new TagList<>();
        if (model.equalsIgnoreCase("无")) return modelList;
        modelList.add(new TagString(model));
        return modelList;
    }

    public abstract void handleEvent();

    public abstract void handleGemstoneEvent(GemstoneTypeEnum gemstoneTypeEnum);

    public abstract ResultTypeEnum getResultType();


}
