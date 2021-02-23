package pers.tuershen.bladeplus.core.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import org.bukkit.configuration.MemorySection;
import pers.tuershen.bladeplus.api.msg.IYamlMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther Tuershen Create Date on 2021/1/5
 */
public class YamlMessages extends YamlReset implements IYamlMsg {

    private Map<String, String> messagesMap = new HashMap<>();

    public YamlMessages() {
        registerYamlReset(this);
    }

    @Override
    public void init() {
        fileConfiguration = BladePlusMain.getFileConfiguration(BladePlusMain.languageFile);
        MemorySection memorySection = (MemorySection) fileConfiguration.get("messages");
        memorySection.getKeys(false).forEach((key -> messagesMap.put(key,
                fileConfiguration.getString("messages." + key)
                .replace('&', '§'))));
    }

    @Override
    public void reload() {
        BladePlusMain.languageFile.reloadConfig();
        this.messagesMap = new HashMap<>();
        init();
    }

    @Override
    public String getMsg(String msg) {
        return this.messagesMap.get(msg);
    }


}
