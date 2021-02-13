package pers.tuershen.bladeplus.gemstone;

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
 * 异类石
 * 强化成功有几率增加拔刀的基础伤害值，杀敌数，以及随机剑气颜色
 */
public class Special extends AbstractGemstone<IGemstoneDisplay> {

    //几率
    private double probability;

    //最大范围
    private int max;

    //最小范围
    private int min;

    public Special(ItemStack gemstone, IGemstoneDisplay display,  double probability, int max, int min) {
        super(gemstone, display);
        this.probability = probability;
        this.max = max;
        this.min = Math.max(min, 0);
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(3));
        gemstone.set("probability", new TagDouble(this.probability));
        gemstone.set("max", new TagInt(this.max));
        gemstone.set("min", new TagInt(this.min));
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
                    .replace("%max%", String.valueOf(this.max))
                    .replace("%min%", String.valueOf(this.min))
            );
        }
        itemMeta.setLore(lore);
        this.gemstone.setItemMeta(itemMeta);
        return this;
    }
}
