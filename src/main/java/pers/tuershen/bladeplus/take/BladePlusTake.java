package pers.tuershen.bladeplus.take;

import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.entity.AnvilBlockLocation;
import pers.tuershen.bladeplus.entity.BladeEntityLocation;
import pers.tuershen.bladeplus.entity.BladePlusHandle;
import pers.tuershen.bladeplus.entity.BladePlusMaterial;
import pers.tuershen.bladeplus.inv.BladePlusInventory;
import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagDouble;
import com.tuershen.nbtlibrary.minecraft.nbt.TagList;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import pers.tuershen.bladeplus.type.ResultTypeEnum;
import pers.tuershen.bladeplus.util.Calculation;

import java.text.DecimalFormat;

/**
 * @auther Tuershen Create Date on 2021/1/7
 */
public class BladePlusTake extends BukkitRunnable {

    //强化属性
    private BladePlusMaterial bladePlusMaterial;
    //铁砧location
    private Location blockLocation;
    //强化界面
    private BladePlusInventory bladePlusInventory;
    //强化时间
    private int time;
    //计数，每次+1
    private int cumulative = 1;
    //玩家名称
    private String name;
    //刀挂台实体NBT
    private EntityNBTTagCompoundApi blade;
    //刀挂台的位置
    private BladeEntityLocation bladeEntityLocation;
    //铁砧位置
    private AnvilBlockLocation anvilBlockLocation;
    //界面设置api
    private IYamlGuiSetting guiSetting;
    //进度材质
    private ItemStack speedOfProgressMaterial;
    //进度计算
    private double factor;
    //粒子特效播放位置
    private Location effLocation;

    //小数点格式化
    private static DecimalFormat decimalFormat = new DecimalFormat("#.00");
    //强化进度初始槽位
    private static final int START_SLOT = 9;


    public BladePlusTake(BladePlusMaterial bladePlusMaterial, BladePlusInventory bladePlusInventory) {
        this.bladePlusMaterial = bladePlusMaterial;
        this.blockLocation = bladePlusInventory.getBlock().getLocation();
        this.effLocation = bladePlusInventory.getBlock().getLocation();
        this.effLocation.setY(this.effLocation.getY() + 1);
        this.bladePlusInventory = bladePlusInventory;
        //强化时间不能为0
        this.time = (int) this.bladePlusMaterial.getTime() <= 0 ? 1 : (int) this.bladePlusMaterial.getTime();
        this.name = bladePlusInventory.getPlayer().getName();
        this.blade = bladePlusInventory.getEntityNBTTagCompoundApi();
        this.bladeEntityLocation = loadForgingLocation(this.blade);
        this.anvilBlockLocation = loadBlockLocation(this.blockLocation);
        this.guiSetting = bladePlusInventory.getIYamlGuiSetting();
        //设置进度条材质
        this.speedOfProgressMaterial = this.guiSetting.getSpeedOfProgressMaterial();
        this.factor = this.time / 9.0;
    }


    @Override
    public void run() {
        //强化时间结束
        if (this.time == cumulative) {
            this.effLocation.getWorld().strikeLightning(this.effLocation);
            //强化成功
            ResultTypeEnum resultType = Calculation.getResultType(this.bladePlusMaterial.getProbability());
            this.bladePlusInventory.handleDistribution(createForgingHandle(), resultType);
            this.bladePlusInventory.caneWorld(name);
            //结束异步
            this.cancel();
            return;
        }
        //播放火焰粒子
        effLocation.getWorld().playEffect(effLocation, Effect.MOBSPAWNER_FLAMES, 10);
        //强化进度
        double speedOfProgress = ((double) this.cumulative / this.time) * 100;
        ItemMeta itemMeta = speedOfProgressMaterial.getItemMeta();
        itemMeta.setDisplayName(speedOfProgressDisplay(speedOfProgress, this.time - this.cumulative));
        speedOfProgressMaterial.setItemMeta(itemMeta);
        //计算进度i以及对应槽位
        int i = (int)(this.cumulative / this.factor);
        for (int slot = 0; slot <= i; slot++) {
            this.bladePlusInventory.getInventory().setItem(slot + START_SLOT, this.speedOfProgressMaterial);
        }
        this.cumulative++;
    }




    public String speedOfProgressDisplay(double speedOfProgress, double time) {
        return this.guiSetting.getSpeedOfProgress()
                .replace("%SpeedOfProgress%", decimalFormat.format(speedOfProgress))
                .replace("%time%", decimalFormat.format(time))
                .replace("&", "§");
    }


    public BladeEntityLocation loadForgingLocation(EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        TagList<TagDouble> pos = entityNBTTagCompoundApi.getNBTTagCompound().get("Pos");
        return new BladeEntityLocation(
                pos.getData().get(0).getDouble(),
                pos.getData().get(1).getDouble(),
                pos.getData().get(2).getDouble()

        );
    }

    public AnvilBlockLocation loadBlockLocation(Location location) {
        return new AnvilBlockLocation(
                location.getBlockX(),
                location.getBlockY(),
                location.getBlockZ()
        );
    }


    private BladePlusHandle createForgingHandle() {
        return new BladePlusHandle(
                this.blade,
                this.bladePlusMaterial,
                this.blockLocation,
                this.bladeEntityLocation,
                this.anvilBlockLocation
        );
    }

}
