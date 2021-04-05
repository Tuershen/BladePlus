package pers.tuershen.bladeplus.core.inv;

import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.core.common.ForgingModel;

import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.nbt.NBTWrite;
import com.tuershen.nbtlibrary.minecraft.nbt.TagList;
import com.tuershen.nbtlibrary.minecraft.nbt.TagString;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


/**
 * @auther Tuershen Create Date on 2021/1/8
 * 拔刀皮肤库界面
 */
public class TextModelInventory extends AbstractInventory implements InventoryHolder {

    private TagList<TagString> textModelList;

    private IYamlModel iYamlModel;

    private ItemStack itemStack;

    public TextModelInventory(TagList<TagString> textModelList,
                              IYamlModel iYamlModel,
                              ItemStack itemStack) {
        this.textModelList = textModelList;
        this.iYamlModel = iYamlModel;
        this.itemStack = itemStack;
        analysis();
    }

    /**
     * 界面解析以及分页
     *
     * @return 界面
     */
    public Inventory analysis() {
        List<TagString> data = this.textModelList.getData();
        this.page = data.size() / 45 + 1;
        int slot = 0;
        boolean never = NBTLookup.isNever(this.itemStack);
        ItemStack copy = new ItemStack(this.itemStack.getType(), 1, this.itemStack.getDurability());
        for (int j = 0; j < this.page; j++) {
            Inventory inventory = setDefaultInventory(this);
            for (int i = never ? 1 : 0; i < 45 && slot < data.size(); i++, slot++)
                inventory.setItem(i, setTextModel(copy, data.get(slot)));
            this.inventoryList.add(inventory);
        }
        this.inventory = (this.inventoryList.size() > 0) ? this.inventoryList.get(0) : setDefaultInventory(this);
        if (never) {
            TagCompound neverCompound = NBTWrite.getNever(this.itemStack);
            this.inventory.setItem(0, NBTWrite.setInventoryModel(this.itemStack, neverCompound));
        }
        return this.inventory;
    }

    public ItemStack replaceModel(ItemStack replaceItem, ItemStack blade) {
        if (replaceItem != null &&
                replaceItem.getType() != Material.AIR) {
            if (NBTLookup.hasTextModel(replaceItem)) {
                String text = NBTRead.getMaterialString(replaceItem, "TextureName");
                String model = NBTRead.getMaterialString(replaceItem, "ModelName");
                return NBTWrite.setModel(blade, (new ForgingModel()).setModelPath(model).setTextPath(text));
            }
            NBTTagCompoundApi compound = BladePlusMain.libraryApi.getCompound(blade);
            ItemStack newItem = new ItemStack(replaceItem.getType(), 1, replaceItem.getDurability());
            compound.remove("TextureName");
            compound.remove("ModelName");
            return BladePlusMain.libraryApi.setCompound(newItem, compound);
        }
        return blade;
    }

    public ItemStack setTextModel(ItemStack itemStack, TagString tagString) {
        if (this.iYamlModel.hasModel(tagString.getData())) {
            ForgingModel model = this.iYamlModel.getModel(tagString.getData());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(this.iYamlModel.getModelKey(tagString.getData()));
            itemStack.setItemMeta(itemMeta);
            return NBTWrite.setBladeModel(itemStack, model);
        }
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
