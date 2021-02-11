package pers.tuershen.bladeplus.command.player;

import com.tuershen.nbtlibrary.minecraft.nbt.TagList;
import com.tuershen.nbtlibrary.minecraft.nbt.TagString;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.inv.TextModelInventory;
import pers.tuershen.bladeplus.nbt.NBTLookup;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.type.CommandExecutorType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class PCommandOpen extends AbstractPlayerCommand {

    public PCommandOpen(IYamlSetting iYamlSetting) {
        super(iYamlSetting);
    }

    @Override
    public <T extends CommandSender> boolean onCommandHandle(T sender, String... args) {
        Player player = (Player) sender;
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getType() != Material.AIR) {
            if (NBTLookup.hasModel(itemStack)) {
                TagList<TagString> modelList = NBTRead.getModelList(itemStack);
                TextModelInventory modelInventory = new TextModelInventory(modelList, iYamlSetting.getIYamlModel(), itemStack);
                player.openInventory(modelInventory.getInventory());
                return true;
            }
            player.sendMessage("§7[§3Console§7] §7▶ §c你手中的拔刀没有皮肤.");
            return true;
        }
        player.sendMessage("§7[§3Console§7] §7▶ §c请手持拔刀.");
        return true;
    }

    @Override
    public String[] getArgs() {
        return new String[]{"open"};
    }

    @Override
    public int getCommandLength() {
        return 0;
    }

    @Override
    public List<String> getTabExecutorResult() {
        return this.tabResultList;
    }

    @Override
    public CommandExecutorType getCommandExecutorType() {
        return CommandExecutorType.PLAYER;
    }
}
