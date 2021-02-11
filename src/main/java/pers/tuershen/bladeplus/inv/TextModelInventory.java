package pers.tuershen.bladeplus.inv;

import pers.tuershen.bladeplus.api.balde.IYamlModel;
import pers.tuershen.bladeplus.entity.ForgingModel;

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
 */
public class TextModelInventory extends AbstractInventory implements InventoryHolder {

    private TagList<TagString> textModelList;

    private IYamlModel iYamlModel;

    private ItemStack itemStack;

    public TextModelInventory(TagList<TagString> textModelList, IYamlModel iYamlModel, ItemStack itemStack) {
        this.textModelList = textModelList;
        this.iYamlModel = iYamlModel;
        this.itemStack = itemStack;
        analysis();
    }

    public Inventory analysis(){
        List<TagString> data = textModelList.getData();
        this.page = (data.size() / 45) + 1;
        int slot = 0;
        for (int j = 0; j < this.page; j++) {
            Inventory inventory = setDefaultInventory(this);
            for (int i = 0; i < 45 && slot < data.size(); i++, slot++) {
                inventory.setItem(i, setTextModel(this.itemStack, data.get(slot)));
            }
            this.inventoryList.add(inventory);
        }
        this.inventory = this.inventoryList.size() > 0 ? this.inventoryList.get(0) : setDefaultInventory(this);
        return this.inventory;
    }

    public ItemStack replaceModel(ItemStack replaceItem, ItemStack itemStack){
        if (NBTLookup.hasTextModel(replaceItem)) {
            String text = NBTRead.getMaterialString(replaceItem, "TextureName");
            String model = NBTRead.getMaterialString(replaceItem, "ModelName");
            return NBTWrite.setModel(itemStack, new ForgingModel().setModelPath(model).setTextPath(text));
        }
        return itemStack;
    }

    public ItemStack setTextModel(ItemStack itemStack, TagString tagString) {
        if (iYamlModel.hasModel(tagString.getData())) {
            ForgingModel model = iYamlModel.getModel(tagString.getData());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(iYamlModel.getModelKey(tagString.getData()));
            itemStack.setItemMeta(itemMeta);
            return NBTWrite.setModel(itemStack, model);
        }
        return itemStack;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
