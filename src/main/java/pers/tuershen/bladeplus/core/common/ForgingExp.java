package pers.tuershen.bladeplus.core.common;

import org.bukkit.entity.Player;

public class ForgingExp {


    /**
     * 计算当前等级的总经验值
     * @param level
     * @return
     */
    public static int getTotalExp(int level) {
        return (int)((level < 17)
                ? (level * (level + 6)) : ((level < 31)
                ? (level * (level * 2.5D - 40.5D) + 360.0D) : (level * (level * 4.5D - 162.5D) + 2220.0D))); }

    /**
     * 计算下一级所需要的经验值
     * @param level
     * @return
     */
    public static int nextExp(int level) {
        return (level < 16) ? (level * 2 + 7) : ((level < 30) ? (level * 5 - 38) : (level * 9 - 158));
    }

    public static void givePlayerLevel(Player player, int level){
        int l = player.getLevel() - level;
        int exp = getTotalExp(l);
        player.setExp(0.0f);
        player.setLevel(0);
        player.setTotalExperience(0);
        givePlayerExp(player,exp);
    }

    public static void givePlayerExp(Player player, int exp) {
        int level = player.getLevel();
        int next = nextExp(level);
        int plus = (int)(player.getExp() * next) + exp;
        while (plus >= next) {
            plus -= next;
            next = nextExp(++level);
        }
        while (plus < 0) {
            plus += next = nextExp(--level);
            if (level < 0) {
                level = plus = 0;
                continue;
            }
        }
        player.setLevel(level);
        player.setExp(plus / next);
        int totalExp = getTotalExp(level) + plus;
        player.setTotalExperience(totalExp);
    }

}


