package pers.tuershen.bladeplus.bukkit.yaml;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.Result;
import pers.tuershen.bladeplus.api.balde.IYamlBladeProgramme;
import pers.tuershen.bladeplus.bukkit.common.BladePlusProgramme;
import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YamlBladeProgramme extends YamlReset implements IYamlBladeProgramme {

    private List<BladePlusProgramme> plusProgrammes = new ArrayList<>();

    public YamlBladeProgramme(){
        registerYamlReset(this);
    }

    @Override
    public void init() {
        List<?> forgingLevel = BladePlusMain.bladePlusMain.getConfig().getList("forgingLevel");
        forgingLevel.forEach((f) -> {
            if (f instanceof Map) {
                Map<?, ?> programme = (Map<?, ?>) f;
                programme.forEach((k, v) -> {
                    BladePlusProgramme plusProgramme = new BladePlusProgramme();
                    if (v instanceof Map) {
                        Map<?, ?> type = (Map<?, ?>) v;
                        type.forEach((t, l) -> {
                            if (l instanceof Map) {
                                if (t.toString().equalsIgnoreCase("material")) {
                                    Map<?, ?> value = (Map<?, ?>) l;
                                    plusProgramme.setMaterialMinRepair(Integer.parseInt(value.get("min").toString()));
                                    plusProgramme.setMaterialMaxRepair(Integer.parseInt(value.get("max").toString()));
                                }else {
                                    Map<?, ?> value = (Map<?, ?>) l;
                                    plusProgramme.setBladeMinRepair(Integer.parseInt(value.get("min").toString()));
                                    plusProgramme.setBladeMaxRepair(Integer.parseInt(value.get("max").toString()));
                                }
                            }
                        });
                        plusProgrammes.add(plusProgramme);
                    }
                });
            }
        });
    }

    @Override
    public void reload() {
        this.plusProgrammes = new ArrayList<>();
        BladePlusMain.materialFile.reloadConfig();
        init();
    }

    @Override
    public Result equalResult(int bladeRepair, int materialRepair) {
        for (BladePlusProgramme plusProgramme : plusProgrammes) {
            int bladeMinRepair = plusProgramme.getBladeMinRepair();
            int bladeMaxRepair = plusProgramme.getBladeMaxRepair();
            if (bladeMinRepair <= bladeRepair && bladeRepair <= bladeMaxRepair) {
                int materialMinRepair = plusProgramme.getMaterialMinRepair();
                int materialMaxRepair = plusProgramme.getMaterialMaxRepair();
                if (materialMinRepair <= materialRepair && materialRepair <= materialMaxRepair) {
                    return () -> ResultTypeEnum.SUCCESS;
                }
            }
        }
        return () -> ResultTypeEnum.FAIL;
    }
}
