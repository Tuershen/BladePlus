package pers.tuershen.bladeplus.nbt.gemstone;

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
 * @auther Tuershen Create Date on 2021/2/11
 * 幸运石 可以增加锻造成功率
 */
public class Lucky extends AbstractGemstone<IGemstoneDisplay> {

    private double probability;

    public Lucky(ItemStack gemstone, IGemstoneDisplay display, double probability) {
        super(gemstone, display);
        this.probability = probability;
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(2));
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
                    .replace("%probability%", String.valueOf(this.probability)));
        }
        itemMeta.setLore(lore);
        this.gemstone.setItemMeta(itemMeta);
        return this;
    }


}
