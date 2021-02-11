package pers.tuershen.bladeplus.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.entity.ForgingGuiSetting;
import pers.tuershen.bladeplus.entity.ForgingUi;
import pers.tuershen.bladeplus.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class YamlInventorySetting extends YamlReset implements IYamlGuiSetting {

    private int forgingMaterialSlot;

    private ForgingGuiSetting forgingAnvilSetting;

    private int forgingGemstoneSlot;

    private String speedOfProgress;

    private ForgingUi speedOfProgressMaterial;

    private ItemStack speedOfProgressItem;

    public YamlInventorySetting(){
        registerYamlReset(this);
    }


    public void init()  {
        FileConfiguration fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.guiSettingFile);
        forgingMaterialSlot = fileConfiguration.getInt("forging_material_slot");
        forgingGemstoneSlot = fileConfiguration.getInt("forging_gemstone_slot");
        speedOfProgressMaterial = ItemUtil.convertForgingUi(fileConfiguration.getString("speed_of_progress_material"));
        speedOfProgressItem = new ItemStack(Material.getMaterial(speedOfProgressMaterial.getType()), 1, speedOfProgressMaterial.getDur());
        speedOfProgress = fileConfiguration.getString("speed_of_progress");
        int slot = fileConfiguration.getInt("forging_button.slot");
        String material = fileConfiguration.getString("forging_button.material");
        int mate = fileConfiguration.getInt("forging_button.mate");
        String display = fileConfiguration.getString("forging_button.display");
        List<String> lore = fileConfiguration.getStringList("forging_button.lore");
        forgingAnvilSetting = new ForgingGuiSetting(slot, material, (short) mate, display, lore);
    }


    public void reload(){
        BladePlusMain.guiSettingFile.reloadConfig();
        this.init();
    }


    @Override
    public int getMaterialSlot() {
        return this.forgingMaterialSlot;
    }

    @Override
    public ForgingGuiSetting getForgingGuiSetting() {
        return this.forgingAnvilSetting;
    }

    @Override
    public int getGemstoneSlot() {
        return this.forgingGemstoneSlot;
    }

    @Override
    public String getSpeedOfProgress() {
        return this.speedOfProgress;
    }

    @Override
    public ItemStack getSpeedOfProgressMaterial(){
        return speedOfProgressItem;
    }


}
