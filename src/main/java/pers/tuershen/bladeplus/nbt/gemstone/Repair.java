package pers.tuershen.bladeplus.nbt.gemstone;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagDouble;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public class Repair extends AbstractGemstone {

    private int maxRepair;

    private double probability;

    public Repair(ItemStack gemstone, int maxRepair, double probability) {
        super(gemstone);
        this.maxRepair = maxRepair;
        this.probability = probability;
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(5));
        gemstone.set("repair", new TagInt(this.maxRepair));
        gemstone.set("probability", new TagDouble(this.probability));
        compound.set("gemstone", gemstone);
        return BladePlusMain.libraryApi.setCompound(this.gemstone, compound);
    }
}
