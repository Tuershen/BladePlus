package pers.tuershen.bladeplus.bukkit.gemstone;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public abstract class AbstractGemstone<T extends IGemstoneDisplay> {

    protected ItemStack gemstone;

    protected T gemstoneDisplay;

    public AbstractGemstone(ItemStack gemstone, T gemstoneDisplay) {
        this.gemstone = gemstone;
        this.gemstoneDisplay = gemstoneDisplay;
        if (this.gemstone == null) {
            this.gemstone = new ItemStack(Material.GRASS);
        }
    }

    /**
     * 设置宝石的属性以及NBT标签
     * @return 宝石
     */
    public abstract ItemStack setGemstoneMate();

    /**
     * 设置宝石lore
     * @return AbstractGemstone
     */
    public abstract AbstractGemstone<T> setGemstoneLore();



    public AbstractGemstone<T> setGemstoneDisplay() {
        ItemMeta itemMeta = this.gemstone.getItemMeta();
        itemMeta.setDisplayName(this.gemstoneDisplay.getDisplay());
        this.gemstone.setItemMeta(itemMeta);
        return this.setGemstoneLore();
    }

    public static boolean hasGemstone(ItemStack gemstone) {
        if (gemstone.getType() == Material.AIR) return false;
        return BladePlusMain.libraryApi.getCompound(gemstone).hasKey("gemstone");
    }

    public static int getGemstoneType(ItemStack gemstone) {
        TagCompound gemstoneMate = BladePlusMain.libraryApi.getCompound(gemstone).get("gemstone");
        return gemstoneMate.getInt("type").getInt();
    }

    public static TagCompound getGemstoneAttribute(ItemStack gemstone){
        return BladePlusMain.libraryApi.getCompound(gemstone).get("gemstone");
    }


}
