package pers.tuershen.bladeplus.entity;

/**
 * @auther Tuershen Create Date on 2021/1/7
 */
public class ForgingModel {

    private String textPath;

    private String modelPath;

    public ForgingModel(String textPath, String modelPath) {
        this.textPath = textPath;
        this.modelPath = modelPath;
    }
    public ForgingModel(){}


    public String getTextPath() {
        return textPath;
    }

    public String getModelPath() {
        return modelPath;
    }


    public ForgingModel setTextPath(String textPath) {
        this.textPath = textPath;
        return this;
    }

    public ForgingModel setModelPath(String modelPath) {
        this.modelPath = modelPath;
        return this;
    }
}
