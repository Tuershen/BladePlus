package pers.tuershen.bladeplus.bukkit.gemstone;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagDouble;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/12
 * 封印石
 * 增加拔刀上限锻造值
 */
public class Repair extends AbstractGemstone<IGemstoneDisplay> {

    private int maxRepair;

    private double probability;

    public Repair(ItemStack gemstone, IGemstoneDisplay display, int maxRepair, double probability) {
        super(gemstone, display);
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

    @Override
    public AbstractGemstone<IGemstoneDisplay> setGemstoneLore() {
        ItemMeta itemMeta = this.gemstone.getItemMeta();
        List<String> lore = this.gemstoneDisplay.getLore();
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, lore.get(i)
                    .replace("%probability%", String.valueOf(this.probability))
                    .replace("%repair%", String.valueOf(this.maxRepair))
            );
        }
        itemMeta.setLore(lore);
        this.gemstone.setItemMeta(itemMeta);
        return this;
    }
}