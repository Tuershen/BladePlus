package pers.tuershen.bladeplus.bukkit.common;

public class BladePlusProgramme {

    private int bladeMinRepair;

    private int bladeMaxRepair;

    private int materialMinRepair;

    private int materialMaxRepair;


    public int getBladeMinRepair() {
        return bladeMinRepair;
    }

    public void setBladeMinRepair(int bladeMinRepair) {
        this.bladeMinRepair = bladeMinRepair;
    }

    public int getBladeMaxRepair() {
        return bladeMaxRepair;
    }

    public void setBladeMaxRepair(int bladeMaxRepair) {
        this.bladeMaxRepair = bladeMaxRepair;
    }

    public int getMaterialMinRepair() {
        return materialMinRepair;
    }

    public void setMaterialMinRepair(int materialMinRepair) {
        this.materialMinRepair = materialMinRepair;
    }

    public int getMaterialMaxRepair() {
        return materialMaxRepair;
    }

    public void setMaterialMaxRepair(int materialMaxRepair) {
        this.materialMaxRepair = materialMaxRepair;
    }

    @Override
    public String toString() {
        return "BladePlusProgramme{" +
                "bladeMinRepair=" + bladeMinRepair +
                ", bladeMaxRepair=" + bladeMaxRepair +
                ", materialMinRepair=" + materialMinRepair +
                ", materialMaxRepair=" + materialMaxRepair +
                '}';
    }
}
