package pers.tuershen.bladeplus.gemstone;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gemstone.IGemstoneDisplay;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 * 保护石
 * 保护石可以免除一次强化失败的惩罚
 */
public class Protect extends AbstractGemstone<IGemstoneDisplay> {

    public Protect(ItemStack gemstone, IGemstoneDisplay display) {
        super(gemstone, display);
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

    @Override
    public AbstractGemstone<IGemstoneDisplay> setGemstoneLore() {
        ItemMeta itemMeta = this.gemstone.getItemMeta();
        List<String> lore = this.gemstoneDisplay.getLore();
        itemMeta.setLore(lore);
        this.gemstone.setItemMeta(itemMeta);
        return this;
    }


}
