package pers.tuershen.bladeplus.bukkit.check;

import com.tuershen.nbtlibrary.minecraft.entity.AbstractMinecraftEntity;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import pers.tuershen.bladeplus.bukkit.common.AnvilBlockLocation;
import pers.tuershen.bladeplus.bukkit.common.BladeEntityLocation;
import pers.tuershen.bladeplus.bukkit.common.BladePlusHandle;
import pers.tuershen.bladeplus.util.EntityCheck;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class BlandStandCheck {

    private BladePlusHandle handle;

    private BladeEntityLocation blade;

    public BlandStandCheck (BladePlusHandle handle) {
        this.handle = handle;
        this.blade = handle.getEntityLocation();
    }

    public boolean basicCheck() {
        Location loc = handle.getLocation();
        AnvilBlockLocation anvil = handle.getAnvilBlockLocation();
        if (loc.getX() == anvil.getX() && loc.getY() == anvil.getY() && anvil.getZ() == anvil.getZ()) {
            if (loc.getBlock() != null) {
                return blockAnvilCheck(loc);
            }
        }
        return false;
    }

    private boolean blockAnvilCheck(Location al){
        String name = al.getBlock().getType().name();
        if (name.equalsIgnoreCase("Anvil")) {
            Entity[] entities = al.getChunk().getEntities();
            return entityBladeCheck(entities);
        }
        return false;
    }

    private boolean entityBladeCheck(Entity[] entities) {
        for (Entity entity : entities) {
            if (entity.getType().name().equalsIgnoreCase(EntityCheck.BLADE_STAND)) {
                Location el = entity.getLocation();
                if (el.getX() == blade.getX() && el.getY() == blade.getY() && el.getZ() == blade.getZ()) {
                    if (AbstractMinecraftEntity.getInstance(entity).getNBTTagCompound().hasKey("Blade")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
