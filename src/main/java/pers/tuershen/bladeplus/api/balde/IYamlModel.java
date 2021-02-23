package pers.tuershen.bladeplus.api.balde;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.core.common.ForgingModel;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public interface IYamlModel extends IYamlReset {

    boolean hasModel(String key);

    ForgingModel getModel(String key);

    String getModelKey(String key);

    List<String> getModelKeys();
}
