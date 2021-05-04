package pers.tuershen.bladeplus.bukkit.common;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import org.bukkit.Location;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class BladePlusHandle {

    private EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    private BladePlusMaterial bladePlusMaterial;

    private Location location;

    private BladeEntityLocation bladeEntityLocation;

    private AnvilBlockLocation anvilBlockLocation;

    public BladePlusHandle(EntityNBTTagCompoundApi entityNBTTagCompoundApi, BladePlusMaterial bladePlusMaterial, Location location, BladeEntityLocation bladeEntityLocation, AnvilBlockLocation anvilBlockLocation) {
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
        this.bladePlusMaterial = bladePlusMaterial;
        this.location = location;
        this.bladeEntityLocation = bladeEntityLocation;
        this.anvilBlockLocation = anvilBlockLocation;
    }

    public EntityNBTTagCompoundApi getEntityNBTTagCompoundApi() {
        return entityNBTTagCompoundApi;
    }

    public BladePlusMaterial getBladePlusMaterial() {
        return bladePlusMaterial;
    }

    public Location getLocation() {
        return location;
    }

    public BladeEntityLocation getEntityLocation() {
        return bladeEntityLocation;
    }

    public AnvilBlockLocation getAnvilBlockLocation() {
        return anvilBlockLocation;
    }
}
