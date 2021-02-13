package pers.tuershen.bladeplus.inv;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.api.inv.ISladePlusInventory;
import pers.tuershen.bladeplus.common.*;
import pers.tuershen.bladeplus.gemstone.hanlde.ResultDistributionHandle;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.gemstone.AbstractGemstone;
import pers.tuershen.bladeplus.take.BladePlusTake;
import pers.tuershen.bladeplus.type.GemstoneTypeEnum;
import pers.tuershen.bladeplus.type.ResultTypeEnum;
import pers.tuershen.bladeplus.util.ItemUtil;
import com.tuershen.nbtlibrary.api.EntityNBTTagCompoundApi;
import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/1/5
 * <p>
 * <p>
 * 2 = 强化石位置
 * 4 = 强化按钮位置
 * 6 = 保护石位置
 * <p>
 * 保护石tag = 1 表示保护石
 * 保护石tag = 2 表示幸运石
 */
public class BladePlusInventory extends AbstractBladePlusInventory implements ISladePlusInventory {

    private Inventory bladeInventory;

    private Player player;

    private Block block;

    private String name;

    private EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    private static Map<String, Boolean> flat = new HashMap<>();

    public BladePlusInventory(Player player, Block block, IYamlSetting yamlSetting, EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        super(yamlSetting);
        this.player = player;
        this.block = block;
        String title = yamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute().getTitle();
        this.bladeInventory = BladePlusMain.bladePlusMain.getServer().createInventory(this, 2 * 9, title);
        this.name = player.getName();
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
        this.plusInventoryAttribute = yamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute();
    }

    public BladePlusInventory setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public void setEntityNBTTagCompoundApi(EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
    }


    public void setDefaultUi() {
        for (int i = 1; i < (2 * 9); i++) {
            this.bladeInventory.setItem(i, setDefaultItemStack());
        }
        BladePlusItem firstItem = plusInventoryAttribute.getFirstItem();
        ItemStack first = new ItemStack(Material.valueOf(firstItem.getType()), 1, firstItem.getDur());
        this.bladeInventory.setItem(0, first);
        //强化石槽位
        this.bladeInventory.setItem(plusInventoryAttribute.getMaterialSlot(), new ItemStack(Material.AIR));
        //点击锻造槽位
        this.bladeInventory.setItem(plusInventoryAttribute.getButton().getSlot(), ItemUtil.convertItemStack(plusInventoryAttribute.getButton()));
        //保护石槽位
        this.bladeInventory.setItem(plusInventoryAttribute.getGemstoneSlot(), new ItemStack(Material.AIR));
    }

    protected void setDefaultSpeedOfProgress() {
        for (int i = 9; i < (2 * 9); i++) {
            this.bladeInventory.setItem(i, setDefaultItemStack());
        }
    }

    protected void sendMessage(String msg) {
        if (this.player != null) {
            player.sendMessage(msg);
        }
    }

    /**
     * 当玩家点击强化按钮时调用
     * @param material 强化材料
     */
    public void runStart(ItemStack material) {
        //玩家是否存在列队中
        if (flat.containsKey(player.getName())) {
            //如果存在列中中并且是在空闲状态
            //如果不是空闲状态则玩家正在强化拔刀剑
            if (!flat.get(player.getName())) {
                this.taskAsynchronouslyCheck(material);
                return;
            }
            //提示玩家他的拔刀剑正在强化中，不能同时强化两把拔刀剑
            sendMessage(this.iYamlSetting.getIYamlMsg().getMsg("error_8"));
            return;
        }
        //不存在队列中则直接执行强化
        this.taskAsynchronouslyCheck(material);
    }

    public void taskAsynchronouslyCheck(ItemStack material) {
        //获取强化材料的所需等级
        int exp = NBTRead.getMaterialInt(material, "exp");
        //如果玩家等级大于等级强化所需等级
        if (player.getLevel() >= exp) {
            //先扣除玩家等级再执行强化任务
            ForgingExp.givePlayerLevel(this.player, exp);
            //准备强化拔刀
            this.taskRunAsynchronouslyStart(material, exp);
            return;
        }
        //提示玩家等级不够
        sendMessage(this.iYamlSetting.getIYamlMsg().getMsg("error_6"));
    }


    /**
     * 准备强化拔刀剑
     * @param material 强化材料
     * @param exp 强化所需等级
     */
    public void taskRunAsynchronouslyStart(ItemStack material, int exp) {
        //获取强化石的各各属性
        BladePlusMaterial bladePlusMaterial = handleGemstone(NBTRead.createBladePlusMaterial(material, exp));
        //创建一个异步任务
        BladePlusTake take = new BladePlusTake(bladePlusMaterial, this);
        /**
         * 把玩家加入到强化队列中，设置把状态设置为true
         * true 代表玩家正在强化拔刀剑
         * false 表示空闲状态
         */
        flat.put(this.player.getName(), true);
        //开始执行异步
        take.runTaskTimerAsynchronously(BladePlusMain.bladePlusMain, 0L, 20);
        //执行异步后需要扣除一个强化石
        this.slotMaterialDeduction(this.getMaterialSlot());
    }

    /**
     * 宝石预处理
     * 当玩家点击强化的时候，个别宝石需要做预处理
     * 比如幸运石给强化材料增加成功率，该操作一般是在强化结束前先做预处理
     * @param bladePlusMaterial 强化材料属性
     * @return 处理之后的强化材料，可能属性会改变，如强化成功率等等
     */
    public BladePlusMaterial handleGemstone(BladePlusMaterial bladePlusMaterial) {
        //获取宝石槽位上的物品
        ItemStack gemstone = this.bladeInventory.getItem(this.getGemstoneSlot());
        //如果物品不为空
        if (gemstone != null) {
            /**
             * 判断物品是否有gemstone NBT标签
             * gemstone NBT类型是 NBTTagCompound
             * 每种强化石都是以NBTTagCompound存储属性
             * 其中type每个宝石都会有表示宝石类型
             */
            if (AbstractGemstone.hasGemstone(gemstone)) {
                //获取宝石类型
                int type = AbstractGemstone.getGemstoneType(gemstone);
                //根据宝石类型获取不同的属性
                TagCompound gemstoneAttribute = AbstractGemstone.getGemstoneAttribute(gemstone);
                //获取该宝石的枚举实例
                GemstoneTypeEnum instance = GemstoneTypeEnum.getInstance(type, gemstoneAttribute, iYamlSetting, this.player);
                //做预处理，返回一个BladePlusGemstone
                //其中
                BladePlusGemstone pretreatment = instance.pretreatment(bladePlusMaterial);
                //count如果大于0说明已做预处理，需要在强化之前扣除相应数量的宝石
                if (pretreatment.getCount() > 0) {
                    //扣除宝石
                    this.setGemstoneSlot(gemstone, this.getGemstoneSlot());
                }
                //强化材料更新
                bladePlusMaterial = pretreatment.getBladePlus();
            }
        }
        return bladePlusMaterial;
    }


    public synchronized boolean handleDistribution(BladePlusHandle plusHandle, ResultTypeEnum resultType) {
        ResultDistributionHandle distributionHandle = new ResultDistributionHandle(plusHandle, iYamlSetting, player);
        if (distributionHandle.baseCheckHandle()) {
            //获取宝石槽位
            ItemStack gemstone = this.bladeInventory.getItem(this.getGemstoneSlot());
            //如果槽位不为空
            if (gemstone != null) {
                //如果是宝石
                if (AbstractGemstone.hasGemstone(gemstone)) {
                    //获取宝石类型
                    int type = AbstractGemstone.getGemstoneType(gemstone);
                    //获取宝石属性
                    TagCompound gemstoneAttribute = AbstractGemstone.getGemstoneAttribute(gemstone);
                    GemstoneTypeEnum instance = GemstoneTypeEnum.getInstance(type, gemstoneAttribute, this.iYamlSetting, this.player);
                    /**
                     * 宝石属性处理
                     * 处理宝石再处理强化石属性
                     * @resultType 锻造结果 成功或者失败
                     * @instance 宝石枚举实例，里面包括两个功能
                     * 宝石预处理与强化后处理
                     * 强化后处理dispatchGemstone(ResultType, GemstoneTypeEnum)
                     */
                    distributionHandle.dispatchGemstone(resultType, instance);
                    //强化处理后扣除宝石
                    this.setGemstoneSlot(gemstone, this.getGemstoneSlot());
                    //重置进度条
                    this.setDefaultSpeedOfProgress();
                    return true;
                }
            }
            //如果宝石槽位没有宝石则默认调用模式处理模式
            distributionHandle.dispatchDefault(resultType);
            //重置进度条
            this.setDefaultSpeedOfProgress();
            return true;
        }
        //如果强化时间结束后，如果刀架台消失或者位置移动将不做任何强化处理
        //重置进度条
        this.setDefaultSpeedOfProgress();
        return false;
    }

    /**
     * 扣除强化材料
     * @param slot 强化材料位置
     */
    public void slotMaterialDeduction(int slot) {
        //获取强化材料
        ItemStack slotMaterial = this.bladeInventory.getItem(slot);
        //扣除一个
        slotMaterial.setAmount(this.bladeInventory.getItem(slot).getAmount() - 1);
        //最后再保存到强化界面
        this.bladeInventory.setItem(slot, slotMaterial);
    }

    /**
     * 扣除宝石
     * @param gemstone 宝石
     * @param gemstoneSlot 宝石槽位
     */
    public void setGemstoneSlot(ItemStack gemstone, int gemstoneSlot) {
        //如果宝石只剩余一个则把该槽位设置为空
        if (gemstone.getAmount() == 1) {
            this.bladeInventory.setItem(gemstoneSlot, new ItemStack(Material.AIR));
            return;
        }
        //否则扣除一个宝石
        gemstone.setAmount(gemstone.getAmount() - 1);
        this.bladeInventory.setItem(gemstoneSlot, gemstone);
    }


    @Override
    public Inventory getInventory() {
        return this.bladeInventory;
    }

    /**
     * 取消强化任务
     * 每个玩家同时只能强化一个拔刀剑
     * @param name 玩家名称
     */
    public void caneWorld(String name) {
        flat.put(name, Boolean.FALSE);
    }

    public Block getBlock() {
        return this.block;
    }

    public Player getPlayer() {
        return player;
    }

    public EntityNBTTagCompoundApi getEntityNBTTagCompoundApi() {
        return entityNBTTagCompoundApi;
    }

    public IYamlGuiSetting getIYamlGuiSetting() {
        return iYamlSetting.getIYamlGuiSetting();
    }

    @Override
    public String toString() {
        return "ForgingInventory{" +
                "forgingInventory=" + bladeInventory +
                ", player=" + player +
                ", block=" + block +
                ", name='" + name + '\'' +
                ", yamlSetting=" + iYamlSetting +
                '}';
    }


    @Override
    public int getGemstoneSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute().getGemstoneSlot();
    }

    @Override
    public int getMaterialSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute().getMaterialSlot();
    }

    @Override
    public int getButtonSlot() {
        return this.iYamlSetting.getIYamlGuiSetting().getBladePlusInventoryAttribute().getButton().getSlot();
    }
}
