package pers.tuershen.bladeplus.util;

import pers.tuershen.bladeplus.core.common.BladePlusButton;
import pers.tuershen.bladeplus.core.common.BladeSpeedOfProgressAttribute;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class ItemUtil {

    public static ItemStack convertItemStack(BladePlusButton bladePlusButton){
        short mate = bladePlusButton.getMate();
        ItemStack itemStack = new ItemStack(Material.getMaterial(bladePlusButton.getMaterial()),1, mate);
        String display = bladePlusButton.getDisplay().replace('&','§');
        List<String> lore = bladePlusButton.getLore();
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, lore.get(i).replace('&','§'));
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack convertItemStack(BladeSpeedOfProgressAttribute ofProgressAttribute){
        short mate = ofProgressAttribute.getMate();
        return new ItemStack(Material.getMaterial(ofProgressAttribute.getMaterial()),1, mate);
    }

    public static ItemStack createItem(String display){
        ItemStack itemStack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(display);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }







}
