package pers.tuershen.bladeplus.file;

import guava10.com.google.common.base.Charsets;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.file.IBladeFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public abstract class AbstractBladeFile implements IBladeFile {

    protected File file;

    protected FileConfiguration fileConfiguration;

    protected String fileNode;

    public AbstractBladeFile(File baseFile, String fileNode) {
        this.file = new File(baseFile, fileNode);
        this.fileNode = fileNode;
    }

    public void saveDefaultFile() {
        if (!this.file.exists()) {
            BladePlusMain.bladePlusMain.saveResource(this.fileNode, false);
        }
    }

    public void reloadConfig() {
        this.fileConfiguration = YamlConfiguration.loadConfiguration(this.file);
        InputStream defConfigStream = BladePlusMain.bladePlusMain.getResource(this.fileNode);
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, Charsets.UTF_8));
            this.fileConfiguration.setDefaults(defConfig);
        }
    }

    public FileConfiguration getFileConfiguration() {
        if (this.fileConfiguration == null) {
            reloadConfig();
        }
        return this.fileConfiguration;
    }

    public void saveConfig() {
        try {
            fileConfiguration.save(this.file);
        } catch (IOException var2) {
            BladePlusMain.bladePlusMain.getLogger().info(Level.SEVERE + "无法将配置保存到guiSetting.yml" + var2);
        }

    }


}
