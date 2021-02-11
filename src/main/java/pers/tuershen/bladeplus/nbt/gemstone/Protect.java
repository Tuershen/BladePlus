package pers.tuershen.bladeplus.nbt.gemstone;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class Protect extends AbstractGemstone {

    public Protect(ItemStack gemstone) {
        super(gemstone);
    }

    /**
     * 设置保护石
     * 直接写入一个NBT标签用来识别保护石
     * @return 保护石
     */
    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(1));
        compound.set("gemstone", gemstone);
        return BladePlusMain.libraryApi.setCompound(this.gemstone, compound);
    }




}
