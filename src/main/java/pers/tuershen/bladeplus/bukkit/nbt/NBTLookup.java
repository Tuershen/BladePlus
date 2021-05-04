package pers.tuershen.bladeplus.nbt;

import pers.tuershen.bladeplus.BladePlusMain;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class NBTLookup {

    /**
     * 是否是拔刀剑
     * 这个是根据拔刀名称进行判断
     * @param blade 物品
     * @return 是否是拔刀剑
     */
    public static boolean isSlashBlade(ItemStack blade) {
        if (blade == null || blade.getType() == Material.AIR) return false;
        return BladePlusMain.yamlSlashBlade.isSlashBlade(blade.getType().name()) && !BladePlusMain.libraryApi.getCompound(blade).hasKey("FLAMMPFEILSLASHBLADE");
    }

    /**
     * 是否是耀魂，用于阻止玩家用时铁砧强化拔刀
     * @param itemStack 物品
     * @return 是否是耀魂
     */
    public static boolean isForgingMaterial(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return false;
        return itemStack.getType().name().contains("FLAMMPFEILSLASHBLADE");
    }



    /**
     * 是否是拔刀强化材料
     * @param itemStack item
     * @return true | false
     */
    public static boolean hasMaterial(ItemStack itemStack) {
        return BladePlusMain.libraryApi.getCompound(itemStack).hasKey("FORGING");
    }


    /**
     * 拔刀是否有皮肤
     *
     * @param itemStack item
     * @return  true | false
     */
    public static boolean hasModel(ItemStack itemStack) {
        return BladePlusMain.libraryApi.getCompound(itemStack).hasKey("ForgingModel");
    }


    public static boolean hasTextModel(ItemStack itemStack) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(itemStack);
        return compound.hasKey("TextureName") && compound.hasKey("ModelName");
    }

    public static boolean hasAppraisalMaterial(ItemStack itemStack) {
        return BladePlusMain.libraryApi.getCompound(itemStack).hasKey("AppraisalMaterial");
    }

    public static boolean hasMaxRepairCounter(ItemStack blade){
        return BladePlusMain.libraryApi.getCompound(blade).hasKey("MaxRepairCounter");
    }

    public static boolean isNever(ItemStack blade){
        return BladePlusMain.libraryApi.getCompound(blade).hasKey("never_replace");
    }





}
