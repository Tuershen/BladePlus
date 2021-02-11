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
public class Bet extends AbstractGemstone {

    private double repairCounter;

    private int bet;

    public Bet(ItemStack gemstone, double repairCounter, int bet) {
        super(gemstone);
        this.repairCounter = repairCounter;
        this.bet = bet;
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(4));
        gemstone.set("probability", new TagDouble(this.repairCounter));
        gemstone.set("magnification", new TagInt(this.bet));
        compound.set("gemstone", gemstone);
        return BladePlusMain.libraryApi.setCompound(this.gemstone, compound);
    }
}
