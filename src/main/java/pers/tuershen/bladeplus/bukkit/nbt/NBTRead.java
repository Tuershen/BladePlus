package pers.tuershen.bladeplus.bukkit.nbt;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.*;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.bukkit.common.BladePlusMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class NBTRead {


    /**
     * 获取Int
     *
     * @param itemStack item
     * @param key       标签名称
     * @return int 数据
     */
    public static int getMaterialInt(ItemStack itemStack, String key) {
        return BladePlusMain.libraryApi.getCompound(itemStack).getInt(key);
    }

    /**
     * 获取String
     *
     * @param itemStack item
     * @param key       标签名称
     * @return String数据
     */
    public static String getMaterialString(ItemStack itemStack, String key) {
        return BladePlusMain.libraryApi.getCompound(itemStack).getString(key);
    }

    /**
     * 获取Double
     *
     * @param itemStack item
     * @param key       标签名称
     * @return double数据
     */
    public static double getMaterialDouble(ItemStack itemStack, String key) {
        return BladePlusMain.libraryApi.getCompound(itemStack).getDouble(key);
    }

    /**
     * 获取拔刀的皮肤列表
     *
     * @param blade 拔刀本体
     * @return 皮肤列表
     */
    public static TagList<TagString> getModelList(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).get("ForgingModel");
    }

    public static BladePlusMaterial createBladePlusMaterial(ItemStack material, int exp) {
        int repairCounter = NBTRead.getMaterialInt(material, "repairCounter");
        int proudSoul = NBTRead.getMaterialInt(material, "proudSoul");
        String model = NBTRead.getMaterialString(material, "model");
        double time = NBTRead.getMaterialDouble(material, "time");
        double probability = NBTRead.getMaterialDouble(material, "probability");
        int fail = NBTRead.getMaterialInt(material, "fail");
        return new BladePlusMaterial()
                .setExp(exp)
                .setRepairCounter(repairCounter)
                .setProudSoul(proudSoul)
                .setModel(model)
                .setTime(time)
                .setProbability(probability)
                .setFail(fail);
    }

    /**
     * 获取拔刀剑sa技能id
     *
     * @param blade 拔刀
     * @return
     */
    public static int getBladeSpecialAttackTypeId(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).getInt("SpecialAttackType");
    }

    /**
     * 是否有技能id
     *
     * @param blade 拔刀
     * @return
     */
    public static boolean hasSpecialAttackTypeId(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).hasKey("SpecialAttackType");
    }


    public static boolean hasSEffect(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).hasKey("SB.SEffect");
    }


    public static List<String> getSEffectList(ItemStack blade) {
        TagCompound<TagInt> effectMap = BladePlusMain.libraryApi.getCompound(blade).get("SB.SEffect");
        Map<String, TagInt> map = effectMap.getMap();
        return new ArrayList<>(map.keySet());
    }

    public static TagCompound<TagInt> getSEffect(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).get("SB.SEffect");
    }

    /**
     * 获取拔刀剑的最大锻造值
     *
     * @param blade
     * @return
     */
    public static int getMaxRepairCounter(ItemStack blade) {
        return BladePlusMain.libraryApi.getCompound(blade).getInt("MaxRepairCounter");
    }

    public static int getBladeEntityInt(EntityNBTTagCompoundApi api, String key) {
        NBTTagCompoundApi nbtTagCompound = api.getNBTTagCompound();
        if (nbtTagCompound.hasKey("Blade")) {
            NBTTagCompoundApi blade = nbtTagCompound.getCompound("Blade");
            if (blade.hasKey("tag")) {
                NBTTagCompoundApi tag = blade.getCompound("tag");
                if (tag.hasKey(key)){
                    return tag.getInt(key);
                }
            }
        }
        //RepairCounter
        return 0;
    }


}
