package pers.tuershen.bladeplus.api.file;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

/**
 * @auther Tuershen Create Date on 2021/2/11
 */
public interface IBladeFile {

    void saveDefaultFile();

    void reloadConfig();

    FileConfiguration getFileConfiguration();

    void saveConfig();


}
