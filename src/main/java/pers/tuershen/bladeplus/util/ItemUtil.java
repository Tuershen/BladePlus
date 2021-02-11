package pers.tuershen.bladeplus.util;

import pers.tuershen.bladeplus.entity.ForgingGuiSetting;
import pers.tuershen.bladeplus.entity.ForgingUi;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class ItemUtil {

    public static ItemStack convertItemStack(ForgingGuiSetting forgingGuiSetting){
        short mate = forgingGuiSetting.getMate();
        ItemStack itemStack = new ItemStack(Material.getMaterial(forgingGuiSetting.getMaterial()), mate);
        String display = forgingGuiSetting.getDisplay().replace('&','§');
        List<String> lore = forgingGuiSetting.getLore();
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, lore.get(i).replace('&','§'));
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ForgingUi convertForgingUi(String id) {
        String[] type = id.split(":");
        if (type.length == 2) return new ForgingUi(type[0], Short.parseShort(type[1]));
        return new ForgingUi(type[0]);
    }




}
