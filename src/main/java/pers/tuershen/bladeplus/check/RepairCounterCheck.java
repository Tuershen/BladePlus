package pers.tuershen.bladeplus.check;

import com.tuershen.nbtlibrary.minecraft.nbt.TagCompound;
import com.tuershen.nbtlibrary.minecraft.nbt.TagInt;
import pers.tuershen.bladeplus.api.IProgramme;

/**
 * @auther Tuershen Create Date on 2021/2/10
 */
public class RepairCounterCheck {

    private TagCompound tagCompound;

    private int forgingRepairCounter;

    private int maxRepairCounter;

    public RepairCounterCheck(TagCompound tagCompound, int forgingRepairCounter, int maxRepairCounter) {
        this.tagCompound = tagCompound;
        this.forgingRepairCounter = forgingRepairCounter;
        this.maxRepairCounter = maxRepairCounter;
    }

    public IProgramme getRepairCounter() {
        return tagCompound.hasKey("MaxRepairCounter") ? haveMaxRepairCounterLabel() : defaultRepairCounter();
    }

    private IProgramme haveMaxRepairCounterLabel() {
        if (!tagCompound.hasKey("RepairCounter")) {
            tagCompound.set("RepairCounter", new TagInt(0));
        }
        int repairCounter = tagCompound.getInt("RepairCounter").getInt() + forgingRepairCounter;
        int limit = tagCompound.getInt("MaxRepairCounter").getInt();
        if (repairCounter >= limit) {
            return new IProgramme() {
                @Override
                public boolean isAchieveLimit() {
                    return true;
                }

                @Override
                public int getRepairCounter() {
                    return limit;
                }
            };
        }
        return new IProgramme() {
            @Override
            public boolean isAchieveLimit() {
                return false;
            }

            @Override
            public int getRepairCounter() {
                return repairCounter;
            }
        };
    }

    private IProgramme defaultRepairCounter(){
        if (!tagCompound.hasKey("RepairCounter")) {
            tagCompound.set("RepairCounter", new TagInt(0));
        }
        int repairCounter = tagCompound.getInt("RepairCounter").getInt() + forgingRepairCounter;
        int max = maxRepairCounter;
        if (repairCounter >= maxRepairCounter) {
            return new IProgramme() {
                @Override
                public boolean isAchieveLimit() {
                    return true;
                }

                @Override
                public int getRepairCounter() {
                    return max;
                }
            };
        } else {
            return new IProgramme() {
                @Override
                public boolean isAchieveLimit() {
                    return false;
                }

                @Override
                public int getRepairCounter() {
                    return repairCounter;
                }
            };
        }
    }


}
