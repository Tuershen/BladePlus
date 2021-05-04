package pers.tuershen.bladeplus.bukkit.yaml;

import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.api.IYamlReset;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public class YamlReset implements IYamlReset {

    private static final List<IYamlReset> resets = new ArrayList<>();

    protected FileConfiguration fileConfiguration;

    public static <T extends YamlReset> void registerYamlReset(T reset) {
        resets.add(reset);
    }


    @Override
    public void init() {
        for (IYamlReset reset : resets) {
            reset.init();
        }
    }

    @Override
    public void reload() {
        for (IYamlReset reset : resets) {
            reset.reload();
        }
    }





}
