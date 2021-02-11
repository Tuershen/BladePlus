package pers.tuershen.bladeplus.nbt.gemstone;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public abstract class AbstractGemstone {

    protected ItemStack gemstone;

    public AbstractGemstone(ItemStack gemstone) {
        this.gemstone = gemstone;
        if (this.gemstone == null) {
            this.gemstone = new ItemStack(Material.GRASS);
        }
    }

    public abstract ItemStack setGemstoneMate();

    public static boolean hasGemstone(ItemStack gemstone) {
        if (null == gemstone) return false;
        return BladePlusMain.libraryApi.getCompound(gemstone).hasKey("gemstone");
    }

    public static int getGemstoneType(ItemStack gemstone) {
        TagCompound gemstoneMate = BladePlusMain.libraryApi.getCompound(gemstone).get("gemstone");
        return gemstoneMate.getInt("type").getInt();
    }

    public static TagCompound getGemstoneMate(ItemStack gemstone){
        return BladePlusMain.libraryApi.getCompound(gemstone).get("gemstone");
    }


}
