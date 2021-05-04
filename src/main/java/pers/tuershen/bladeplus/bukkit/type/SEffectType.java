package pers.tuershen.bladeplus.bukkit.type;


/**
 * @auther Tuershen Create Date on 2021/2/22
 */
public enum SEffectType {

    VALUE {
        @Override
        public String getKetType() {
            return type;
        }
    },
    ERROR {
        @Override
        public String getKetType() {
            return "null";
        }
    };

    protected String type;

    public static SEffectType error = SEffectType.ERROR;

    public abstract String getKetType();

    public SEffectType sEffectType(String type) {
        this.type = type;
        return this;
    }

    public static SEffectType getInstance(String type) {
        return SEffectType.VALUE.sEffectType(type);
    }



}
