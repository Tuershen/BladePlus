package pers.tuershen.bladeplus.nbt.gemstone;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagDouble;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class Special extends AbstractGemstone {

    private double repairCounter;

    private int max;

    private int min;

    public Special(ItemStack gemstone, double repairCounter, int max, int min) {
        super(gemstone);
        this.repairCounter = repairCounter;
        this.max = max;
        this.min = min;
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(3));
        gemstone.set("probability", new TagDouble(this.repairCounter));
        gemstone.set("max", new TagInt(this.max));
        gemstone.set("min", new TagInt(this.min));
        compound.set("gemstone", gemstone);
        return BladePlusMain.libraryApi.setCompound(this.gemstone, compound);
    }
}
