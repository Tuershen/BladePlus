package pers.tuershen.bladeplus.yaml;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.check.IYamlBanUseWorld;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/31
 */
public class YamlBanUseWorld extends YamlReset implements IYamlBanUseWorld {

    private List<String> banWorld  = new ArrayList<>();



    public YamlBanUseWorld(){
        registerYamlReset(this);
    }


    public void init() {
        fileConfiguration = BladePlusMain.bladePlusMain.getConfig();
        this.banWorld = fileConfiguration.getStringList("banUseWorld");
    }

    public void reload() {
        BladePlusMain.bladePlusMain.reloadConfig();
        this.banWorld = new ArrayList<>();
        this.init();
    }

    @Override
    public boolean isBanWorld(World world) {
        for (int i = 0; i < this.banWorld.size(); i++) {
            if (this.banWorld.get(i).equalsIgnoreCase(world.getName())) return true;
        }
        return false;
    }
}
