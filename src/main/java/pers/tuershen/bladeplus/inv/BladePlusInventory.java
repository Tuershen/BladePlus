package pers.tuershen.bladeplus.inv;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.gui.IYamlGuiSetting;
import pers.tuershen.bladeplus.api.IYamlSetting;
import pers.tuershen.bladeplus.common.ForgingExp;
import pers.tuershen.bladeplus.common.hanlde.ResultDistributionHandle;
import pers.tuershen.bladeplus.entity.*;
import pers.tuershen.bladeplus.nbt.NBTRead;
import pers.tuershen.bladeplus.nbt.gemstone.AbstractGemstone;
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
import org.bukkit.inventory.InventoryHolder;
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
public class BladePlusInventory implements InventoryHolder {

    private Inventory bladeInventory;

    private Player player;

    private Block block;

    private String name;

    private IYamlSetting yamlSetting;

    private EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    private static Map<String, Boolean> flat = new HashMap<>();

    public BladePlusInventory(Player player, Block block, IYamlSetting yamlSetting, String title, EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        this.player = player;
        this.block = block;
        this.yamlSetting = yamlSetting;
        this.bladeInventory = BladePlusMain.bladePlusMain.getServer().createInventory(this, 2 * 9, title);
        this.name = player.getName();
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
    }

    public BladePlusInventory setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public void setEntityNBTTagCompoundApi(EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
    }


    public void setDefaultUi() {
        ForgingGuiSetting forgingGuiSetting = this.yamlSetting.getIYamlGuiSetting().getForgingGuiSetting();
        System.out.println(forgingGuiSetting);
        for (int i = 1; i < (2 * 9); i++) {
            this.bladeInventory.setItem(i, setDefaultItemStack());
        }
        ForgingUi forgingUi = this.yamlSetting.getForgingUi();
        ItemStack first = new ItemStack(Material.valueOf(forgingUi.getType()), 1, forgingUi.getDur());
        this.bladeInventory.setItem(0, first);
        //强化石槽位
        this.bladeInventory.setItem(this.yamlSetting.getIYamlGuiSetting().getMaterialSlot(), new ItemStack(Material.AIR));
        //点击锻造槽位
        this.bladeInventory.setItem(forgingGuiSetting.getSlot(), ItemUtil.convertItemStack(forgingGuiSetting));
        //保护石槽位
        this.bladeInventory.setItem(this.yamlSetting.getIYamlGuiSetting().getGemstoneSlot(), new ItemStack(Material.AIR));
    }

    protected void setDefaultSpeedOfProgress() {
        for (int i = 9; i < (2 * 9); i++) {
            this.bladeInventory.setItem(i, setDefaultItemStack());
        }
    }

    protected ItemStack setDefaultItemStack() {
        ForgingUi forgingFail = this.yamlSetting.getForgingFail();
        return new ItemStack(Material.valueOf(forgingFail.getType()), 1, forgingFail.getDur());
    }


    /**
     * 强化成功或者强化失败
     * 取消任务列队，不取消玩家无法继续强化
     *
     * @param exp 扣除等级
     */
    protected void cancelWork(int exp) {
        ForgingExp.givePlayerLevel(this.player, exp);
        flat.put(this.player.getName(), Boolean.FALSE);
    }

    protected void sendMessage(String msg) {
        if (this.player != null) {
            player.sendMessage(msg);
        }
    }

    /**
     * 点击强化
     *
     * @param material 强化材料
     */
    public void runStart(ItemStack material) {
        if (flat.containsKey(player.getName())) {
            if (!flat.get(player.getName())) {
                this.taskAsynchronouslyCheck(material);
                return;
            }
            sendMessage(this.yamlSetting.getIYamlMsg().getMsg("error_8"));
            return;
        }
        this.taskAsynchronouslyCheck(material);
    }

    public void taskAsynchronouslyCheck(ItemStack material) {
        int exp = NBTRead.getMaterialInt(material, "exp");
        if (player.getLevel() >= exp) {
            this.taskRunAsynchronouslyStart(material, exp);
            return;
        }
        sendMessage(this.yamlSetting.getIYamlMsg().getMsg("error_6"));
    }


    public void taskRunAsynchronouslyStart(ItemStack material, int exp) {
        //获取强化石的各各属性
        BladePlusMaterial bladePlusMaterial = NBTRead.createBladePlusMaterial(material, exp);
        bladePlusMaterial = handleGemstone(bladePlusMaterial);
        BladePlusTake take = new BladePlusTake(bladePlusMaterial, this);
        int slot = yamlSetting.getIYamlGuiSetting().getMaterialSlot();
        flat.put(this.player.getName(), true);
        take.runTaskTimerAsynchronously(BladePlusMain.bladePlusMain, 0L, 20);
        this.slotMaterialDeduction(slot);
    }

    public BladePlusMaterial handleGemstone(BladePlusMaterial bladePlusMaterial) {
        int gemstoneSlot = this.yamlSetting.getIYamlGuiSetting().getGemstoneSlot();
        ItemStack gemstone = this.bladeInventory.getItem(gemstoneSlot);
        if (gemstone != null) {
            if (AbstractGemstone.hasGemstone(gemstone)) {
                int type = AbstractGemstone.getGemstoneType(gemstone);
                TagCompound gemstoneMate = AbstractGemstone.getGemstoneMate(gemstone);
                GemstoneTypeEnum instance = GemstoneTypeEnum.getInstance(type, gemstoneMate, yamlSetting);
                BladePlusGemstone pretreatment = instance.pretreatment(bladePlusMaterial);
                if (pretreatment.getCount() > 0) {
                    this.setGemstoneSlot(gemstone, gemstoneSlot);
                }
                bladePlusMaterial = pretreatment.getBladePlus();
            }
        }
        return bladePlusMaterial;
    }


    public synchronized boolean handleDistribution(BladePlusHandle plusHandle, ResultTypeEnum resultType) {
        ResultDistributionHandle distributionHandle = new ResultDistributionHandle(plusHandle, yamlSetting, player);
        if (distributionHandle.baseCheckHandle()) {
            int gemstoneSlot = this.yamlSetting.getIYamlGuiSetting().getGemstoneSlot();
            ItemStack gemstone = this.bladeInventory.getItem(gemstoneSlot);
            //如果有宝石
            if (AbstractGemstone.hasGemstone(gemstone)) {
                int type = AbstractGemstone.getGemstoneType(gemstone);
                TagCompound gemstoneMate = AbstractGemstone.getGemstoneMate(gemstone);
                GemstoneTypeEnum instance = GemstoneTypeEnum.getInstance(type, gemstoneMate, this.yamlSetting);
                distributionHandle.dispatchGemstone(resultType, instance);
                this.bladeInventory.setItem(gemstoneSlot, gemstone);
                this.setGemstoneSlot(gemstone, gemstoneSlot);
                this.setDefaultSpeedOfProgress();
                return true;
            }
            distributionHandle.dispatchDefault(resultType);
            this.setDefaultSpeedOfProgress();
            return true;
        }
        this.setDefaultSpeedOfProgress();
        return false;
    }

    public void slotMaterialDeduction(int slot) {
        ItemStack slotMaterial = this.bladeInventory.getItem(slot);
        slotMaterial.setAmount(this.bladeInventory.getItem(slot).getAmount() - 1);
        this.bladeInventory.setItem(slot, slotMaterial);
    }

    public void setGemstoneSlot(ItemStack gemstone, int gemstoneSlot) {
        if (gemstone.getAmount() == 1) {
            this.bladeInventory.setItem(gemstoneSlot, new ItemStack(Material.AIR));
            return;
        }
        gemstone.setAmount(gemstone.getAmount() - 1);
        this.bladeInventory.setItem(gemstoneSlot, gemstone);
    }


    @Override
    public Inventory getInventory() {
        return this.bladeInventory;
    }

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
        return yamlSetting.getIYamlGuiSetting();
    }

    @Override
    public String toString() {
        return "ForgingInventory{" +
                "forgingInventory=" + bladeInventory +
                ", player=" + player +
                ", block=" + block +
                ", name='" + name + '\'' +
                ", yamlSetting=" + yamlSetting +
                '}';
    }


}
