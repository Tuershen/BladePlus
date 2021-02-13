package pers.tuershen.bladeplus.api.balde;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.common.BladePlusMaterial;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public interface IYamlMaterial extends IYamlReset {

    BladePlusMaterial getBladePlusMaterial(String key);

    boolean hasBladePlusMaterial(String key);

    List<String> getMaterialKeys();

}
