package pers.tuershen.bladeplus.bukkit.take;

import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagDouble;
import com.tuershen.nbtlibrary.minecraft.nbt.TagList;
import java.text.DecimalFormat;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.ISladePlusTask;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.bukkit.common.AnvilBlockLocation;
import pers.tuershen.bladeplus.bukkit.common.BladeEntityLocation;
import pers.tuershen.bladeplus.bukkit.common.BladePlusHandle;
import pers.tuershen.bladeplus.bukkit.common.BladePlusMaterial;
import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;
import pers.tuershen.bladeplus.util.Calculation;
import pers.tuershen.bladeplus.util.ItemUtil;

public class BladePlusTake extends BukkitRunnable {
    private BladePlusMaterial bladePlusMaterial;

    private Location blockLocation;

    private ISladePlusTask iSladePlusTask;

    private int time;

    private int cumulative = 1;

    private String name;

    private EntityNBTTagCompoundApi blade;

    private BladeEntityLocation bladeEntityLocation;

    private AnvilBlockLocation anvilBlockLocation;

    private IYamlGuiSetting guiSetting;

    private ItemStack speedOfProgressMaterial;

    private double factor;

    private Location effLocation;

    private static DecimalFormat decimalFormat = new DecimalFormat("#.00");

    private static final int START_SLOT = 9;

    private boolean enableEff;

    public BladePlusTake(BladePlusMaterial bladePlusMaterial, ISladePlusTask iSladePlusTask) {
        this.enableEff = BladePlusMain.bladePlusMain.getConfig().getBoolean("enableEff", true);
        this.bladePlusMaterial = bladePlusMaterial;
        this.blockLocation = iSladePlusTask.getBladeEntityLocation();
        this.effLocation = iSladePlusTask.getBladeEntityLocation();
        this.effLocation.setY(this.effLocation.getY() + 1.0D);
        this.iSladePlusTask = iSladePlusTask;
        this.time = ((int)this.bladePlusMaterial.getTime() <= 0) ? 1 : (int)this.bladePlusMaterial.getTime();
        this.name = iSladePlusTask.getPlayerDisPlay();
        this.blade = iSladePlusTask.getBladeEntity();
        this.bladeEntityLocation = loadForgingLocation(this.blade);
        this.anvilBlockLocation = loadBlockLocation(this.blockLocation);
        this.guiSetting = iSladePlusTask.getIYamlGuiSetting();
        this.speedOfProgressMaterial = ItemUtil.convertItemStack(this.guiSetting.getBladeSpeedOfProgressAttribute());
        this.factor = this.time / 9.0D;
    }

    public void run() {
        if (this.time == this.cumulative) {
            if (this.enableEff)
                this.effLocation.getWorld().strikeLightning(this.effLocation);
            ResultTypeEnum resultType = Calculation.getResultType(this.bladePlusMaterial.getProbability());
            this.iSladePlusTask.handleDistribution(createForgingHandle(), resultType);
            this.iSladePlusTask.caneWorld(this.name);
            cancel();
        } else {
            this.effLocation.getWorld().playEffect(this.effLocation, Effect.MOBSPAWNER_FLAMES, 10);
            this.effLocation.getWorld().playSound(this.effLocation, Sound.ANVIL_LAND, 1.0F, 1.0F);
            double speedOfProgress = this.cumulative / this.time * 100.0D;
            ItemMeta itemMeta = this.speedOfProgressMaterial.getItemMeta();
            itemMeta.setDisplayName(speedOfProgressDisplay(speedOfProgress, (this.time - this.cumulative)));
            this.speedOfProgressMaterial.setItemMeta(itemMeta);
            int i = (int)(this.cumulative / this.factor);
            for (int slot = 0; slot <= i; slot++)
                this.iSladePlusTask.getInventory().setItem(slot + 9, this.speedOfProgressMaterial);
            this.cumulative++;
        }
    }

    private String speedOfProgressDisplay(double speedOfProgress, double time) {
        return this.guiSetting.getBladeSpeedOfProgressAttribute().getDisplay().replace("%SpeedOfProgress%", decimalFormat.format(speedOfProgress)).replace("%time%", decimalFormat.format(time)).replace("&", "§");
    }

    private BladeEntityLocation loadForgingLocation(EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        TagList<TagDouble> pos = entityNBTTagCompoundApi.getNBTTagCompound().get("Pos");
        return new BladeEntityLocation(pos.getData().get(0).getDouble(), pos.getData().get(1).getDouble(), ((TagDouble)pos.getData().get(2)).getDouble());
    }

    private AnvilBlockLocation loadBlockLocation(Location location) {
        return new AnvilBlockLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    private BladePlusHandle createForgingHandle() {
        return new BladePlusHandle(this.blade, this.bladePlusMaterial, this.blockLocation, this.bladeEntityLocation, this.anvilBlockLocation);
    }
}
