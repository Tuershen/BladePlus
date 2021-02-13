package pers.tuershen.bladeplus.api.inv;

import org.bukkit.entity.Player;

/**
 * @auther Tuershen Create Date on 2021/2/12
 */
public interface IAppraisalInventory extends IAttributeInventory {

    boolean checkAppraisalGemstone(Player player);

}
