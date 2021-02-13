package pers.tuershen.bladeplus.nbt;

import com.tuershen.nbtlibrary.minecraft.nbt.*;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.common.appraisal.AppraisalMaterial;
import pers.tuershen.bladeplus.common.BladePlusAppraisalDisplay;
import pers.tuershen.bladeplus.common.BladePlusMaterial;
import pers.tuershen.bladeplus.common.ForgingModel;
import pers.tuershen.bladeplus.util.Calculation;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class NBTWrite {

    public static ItemStack setMaxRepairCounter(ItemStack itemStack, int maxVal) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(itemStack);
        compound.setInt("MaxRepairCounter", maxVal);
        return BladePlusMain.libraryApi.setCompound(itemStack, compound);
    }

    /**
     * 属性写入到强化材料
     * 该方法一般用于指令获取强化石
     * FORGING 标签用来识别是不是强化石
     * @param strengthenMaterial 强化材料
     * @param material 材料属性
     * @return 强化材料
     */
    public static ItemStack writerNBT(ItemStack strengthenMaterial, BladePlusMaterial material) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(strengthenMaterial);
        compound.setInt("exp", material.getExp());
        compound.setInt("repairCounter", material.getRepairCounter());
        compound.setInt("proudSoul", material.getProudSoul());
        compound.setString("model", material.getModel());
        compound.setDouble("time", material.getTime());
        compound.setDouble("probability", material.getProbability());
        compound.setDouble("fail", material.getFail());
        compound.setInt("FORGING", 1);
        TagCompound display = new TagCompound();
        display.set("Name", new TagString(material.getDisplay().replace('&', '§')));
        List<TagString> lore = new ArrayList<>();
        List<String> materialLore = material.getLore();
        for (int i = 0; i < materialLore.size(); i++) {
            lore.add(new TagString(materialLore.get(i)
                    .replace('&', '§')
                    .replace("%exp%", String.valueOf(material.getExp()))
                    .replace("%probability%", String.valueOf(material.getProbability()))
                    .replace("%time%", String.valueOf(material.getTime()))
                    .replace("%repairCounter%", String.valueOf(material.getRepairCounter()))
                    .replace("%proudSoul%", String.valueOf(material.getProudSoul()))
                    .replace("%model%", String.valueOf(material.getModel()))
                    .replace("%fail%", String.valueOf(material.getFail()))
            ));
        }
        display.set("Lore", new TagList<>(lore));
        compound.set("display", display);
        return BladePlusMain.libraryApi.setCompound(strengthenMaterial, compound);
    }





    /**
     * 设置拔刀模型
     * TextureName 皮肤图片路径
     * ModelName 模型路径
     * @param blade 拔刀本体
     * @param model 属性
     * @return 修改皮肤之后的拔刀
     */
    public static ItemStack setModel(ItemStack blade, ForgingModel model) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.setString("TextureName", model.getTextPath());
        compound.setString("ModelName", model.getModelPath());
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }

    /**
     * 先创建一个材料属性对象，然后通过鉴定石的随机属性生成一个属性对象
     * 然后再调用写入属性到鉴定石变成强化材料
     * @param appraisal 鉴定石
     * @param appraisalMaterial 鉴定材料
     * @return 返回一个固定范围的随机属性强化材料
     */
    public static ItemStack appraisalToMaterial(ItemStack appraisal, ItemStack appraisalMaterial, BladePlusAppraisalDisplay display) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(appraisal);
        TagCompound appraisalMaterial1 = compound.get("AppraisalMaterial");
        BladePlusMaterial bladePlusMaterial = new BladePlusMaterial();
        bladePlusMaterial.setExp(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_exp").getInt(), appraisalMaterial1.getInt("max_exp").getInt()));
        bladePlusMaterial.setRepairCounter(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_repairCounter").getInt(), appraisalMaterial1.getInt("max_repairCounter").getInt()));
        bladePlusMaterial.setTime(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_time").getInt(), appraisalMaterial1.getInt("max_time").getInt()));
        bladePlusMaterial.setProbability(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_probability").getInt(), appraisalMaterial1.getInt("max_probability").getInt()));
        bladePlusMaterial.setProudSoul(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_proudSoul").getInt(), appraisalMaterial1.getInt("max_proudSoul").getInt()));
        bladePlusMaterial.setFail(Calculation.getRandomNumber(appraisalMaterial1.getInt("min_fail").getInt(), appraisalMaterial1.getInt("max_fail").getInt()));
        bladePlusMaterial.setModel(compound.getString("model"));
        bladePlusMaterial.setDisplay(display.getDisplay());
        bladePlusMaterial.setLore(display.getLore());
        return writerNBT(appraisalMaterial, bladePlusMaterial);
    }

    /**
     * 鉴定材料写入强化属性
     * @param itemStack 鉴定材料
     * @param appraisalMaterial 属性对象，由鉴定石随机生成属性
     * @return 最终返回一个强化石
     */
    public static ItemStack wireAppraisalToMaterial(ItemStack itemStack, AppraisalMaterial appraisalMaterial) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(itemStack);
        TagCompound display = new TagCompound();
        display.set("Name", new TagString(appraisalMaterial.getDisplay().replace('&', '§')));
        List<TagString> lore = new ArrayList<>();
        List<String> materialLore = appraisalMaterial.getLore();
        for (int i = 0; i < materialLore.size(); i++) {
            lore.add(new TagString(materialLore.get(i)
                    .replace('&', '§')
                    .replace("%min_exp%", String.valueOf(appraisalMaterial.getAppraisalExp().getMin()))
                    .replace("%max_exp%", String.valueOf(appraisalMaterial.getAppraisalExp().getMax()))
                    .replace("%min_fail%", String.valueOf(appraisalMaterial.getAppraisalFail().getMin()))
                    .replace("%max_fail%", String.valueOf(appraisalMaterial.getAppraisalFail().getMax()))
                    .replace("%min_probability%", String.valueOf(appraisalMaterial.getAppraisalProbability().getMin()))
                    .replace("%max_probability%", String.valueOf(appraisalMaterial.getAppraisalProbability().getMax()))
                    .replace("%min_time%", String.valueOf(appraisalMaterial.getAppraisalTime().getMin()))
                    .replace("%max_time%", String.valueOf(appraisalMaterial.getAppraisalTime().getMax()))
                    .replace("%min_repairCounter%", String.valueOf(appraisalMaterial.getAppraisalRepairCounter().getMin()))
                    .replace("%max_repairCounter%", String.valueOf(appraisalMaterial.getAppraisalRepairCounter().getMax()))
                    .replace("%min_proudSoul%", String.valueOf(appraisalMaterial.getAppraisalProudSoul().getMin()))
                    .replace("%max_proudSoul%", String.valueOf(appraisalMaterial.getAppraisalProudSoul().getMax()))
                    .replace("%model%", String.valueOf(appraisalMaterial.getModel()))
            ));
        }
        display.set("Lore", new TagList<>(lore));
        compound.set("display", display);
        compound.set("model", new TagString(appraisalMaterial.getModel()));
        TagCompound appraisal = new TagCompound();
        appraisal.set("min_exp", new TagInt(appraisalMaterial.getAppraisalExp().getMin()));
        appraisal.set("max_exp", new TagInt(appraisalMaterial.getAppraisalExp().getMax()));
        appraisal.set("min_probability", new TagInt(appraisalMaterial.getAppraisalProbability().getMin()));
        appraisal.set("max_probability", new TagInt(appraisalMaterial.getAppraisalProbability().getMax()));
        appraisal.set("min_time", new TagInt(appraisalMaterial.getAppraisalTime().getMin()));
        appraisal.set("max_time", new TagInt(appraisalMaterial.getAppraisalTime().getMax()));
        appraisal.set("min_repairCounter", new TagInt(appraisalMaterial.getAppraisalRepairCounter().getMin()));
        appraisal.set("max_repairCounter", new TagInt(appraisalMaterial.getAppraisalRepairCounter().getMax()));
        appraisal.set("min_proudSoul", new TagInt(appraisalMaterial.getAppraisalProudSoul().getMin()));
        appraisal.set("max_proudSoul", new TagInt(appraisalMaterial.getAppraisalProudSoul().getMax()));
        appraisal.set("min_fail", new TagInt(appraisalMaterial.getAppraisalFail().getMin()));
        appraisal.set("max_fail", new TagInt(appraisalMaterial.getAppraisalFail().getMax()));
        compound.set("AppraisalMaterial", appraisal);
        return BladePlusMain.libraryApi.setCompound(itemStack, compound);
    }






    /**
     * 添加皮肤，不可重复
     * 如果皮肤名称相同则不做处理直接返回原有的皮肤
     * 如果不存在则添加一个新皮肤
     * @param list 原有的皮肤列表
     * @param model 新增的皮肤
     * @return 列表皮肤
     */
    public static List<TagString> putModel(List<TagString> list, String model) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getData().equalsIgnoreCase(model)) return list;
        }
        list.add(new TagString(model));
        return list;
    }

    public static ItemStack setDouble(ItemStack blade, String key, double val) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.set(key, new TagDouble(val));
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }


    public static ItemStack setFloat(ItemStack blade, String key, float val) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.set(key, new TagFloat(val));
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }

    public static ItemStack setInt(ItemStack blade, String key, int val) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.set(key, new TagInt(val));
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }

    public static ItemStack setString(ItemStack blade, String key, String val) {
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.set(key, new TagString(val));
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }


    public static ItemStack setSpecialAttackTypeId(ItemStack blade, int id){
        NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
        compound.set("SpecialAttackType", new TagInt(id));
        return BladePlusMain.libraryApi.setCompound(blade, compound);
    }




}
