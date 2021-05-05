package pers.tuershen.bladeplus.api.balde;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.api.Result;
import pers.tuershen.bladeplus.bukkit.type.ResultTypeEnum;

public interface IYamlBladeProgramme extends IYamlReset {

    default Result equalResult(int bladeRepair, int materialRepair) {
        return () -> ResultTypeEnum.SUCCESS;
    }


}
