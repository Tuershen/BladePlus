package pers.tuershen.bladeplus.util;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.api.NBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.entity.AbstractMinecraftEntity;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * @auther Tuershen Create Date on 2021/2/23
 */
public class EntityCheck {

    public final static String BLADE_STAND = "FLAMMPFEIL.SLASHBLADE_BLADESTAND";

    private Player player;

    private Block block;

    private EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    public EntityCheck(Player player, Block block) {
        this.player = player;
        this.block = block;
    }

    public boolean check() {

        Entity[] entities = this.block.getChunk().getEntities();
        for (Entity entity : entities) {
            if (entity.getType().name().equalsIgnoreCase(BLADE_STAND)) {
                Location anvil = entity.getLocation();
                anvil.setY(anvil.getY() - 1);
                if (anvil.getBlock().equals(this.block)) {
                    EntityNBTTagCompoundApi instance = AbstractMinecraftEntity.getInstance(entity);
                    NBTTagCompoundApi nbtTagCompound = instance.getNBTTagCompound();
                    if (nbtTagCompound.hasKey("Blade")) {
                        this.entityNBTTagCompoundApi = instance;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public EntityNBTTagCompoundApi getBladeEntity(){
        return this.entityNBTTagCompoundApi;
    }
}