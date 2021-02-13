package pers.tuershen.bladeplus.api.appraisal;

import pers.tuershen.bladeplus.api.IYamlReset;
import pers.tuershen.bladeplus.common.appraisal.AppraisalMaterial;

import java.util.List;

/**
 * @auther Tuershen Create Date on 2021/1/8
 */
public interface IYamlAppraisalMaterial extends IYamlReset {


    boolean hasAppraisalMaterial(String appraisal);

    AppraisalMaterial getAppraisalMaterial(String appraisal);

    List<String> getAppraisalSchemes();


}
