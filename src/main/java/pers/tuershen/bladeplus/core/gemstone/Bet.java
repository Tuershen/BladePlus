package pers.tuershen.bladeplus.core.gemstone;

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
 * 赌石 有几率翻倍增加强化石的属性，但也可能翻倍扣除强化石的属性
 *
 */
public class Bet extends AbstractGemstone<IGemstoneDisplay> {

    private double probability;

    private double bet;

    public Bet(ItemStack gemstone, IGemstoneDisplay display, double probability, double bet) {
        super(gemstone, display);
        this.probability = probability;
        this.bet = bet;
    }

    @Override
    public ItemStack setGemstoneMate() {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(this.gemstone);
        TagCompound gemstone = new TagCompound();
        gemstone.set("type", new TagInt(4));
        gemstone.set("probability", new TagDouble(this.probability));
        gemstone.set("magnification", new TagDouble(this.bet));
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
                    .replace("%magnification%", String.valueOf(this.bet))
            );
        }
        itemMeta.setLore(lore);
        this.gemstone.setItemMeta(itemMeta);
        return this;
    }
}
