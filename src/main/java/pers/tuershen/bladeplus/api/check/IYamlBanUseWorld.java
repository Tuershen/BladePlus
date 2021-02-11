package pers.tuershen.bladeplus.api.check;

import org.bukkit.World;
import pers.tuershen.bladeplus.api.IYamlReset;

/**
 * @auther Tuershen Create Date on 2021/1/31
 */
public interface IYamlBanUseWorld extends IYamlReset {

    boolean isBanWorld(World world);

}
