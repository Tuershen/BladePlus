package pers.tuershen.bladeplus.common;

/**
 * @auther Tuershen Create Date on 2021/2/9
 */
public class AnvilBlockLocation {

    private int x;

    private int y;

    private int z;

    public AnvilBlockLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
