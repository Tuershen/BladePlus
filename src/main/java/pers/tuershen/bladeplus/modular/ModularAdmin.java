package pers.tuershen.bladeplus.modular;

public class ModularAdmin<T extends ModularCore> {

    private T modular;

    private String name;

    private String version;

    private String[] info;

    public T getModular() {
        return modular;
    }

    public void setModular(T modular) {
        this.modular = modular;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
    }
}
