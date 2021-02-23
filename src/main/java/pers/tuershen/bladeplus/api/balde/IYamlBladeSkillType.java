package pers.tuershen.bladeplus.api.balde;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.core.type.SEffectType;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public interface IYamlBladeSkillType extends IYamlReset {

    boolean isBanSA(int id);

    int getRandomSA();

    void addSA(int id);

    void addSEffects(List<String> effects);

    SEffectType isGetBanSE(TagCompound<TagInt> tagCompound);



}
