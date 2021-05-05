package pers.tuershen.bladeplus.modular.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadConfiguration {

    private File modularFile;

    public ReadConfiguration(File modularFile) {
        this.modularFile = modularFile;
    }

    public List<File> getModularList(){
        List<File> list = new ArrayList<>();
        boolean isDirectory = this.modularFile.isDirectory();
        if (!isDirectory) {
            this.modularFile.mkdirs();
        }
        String[] fileList = this.modularFile.list();
        String funMkdirsPath = this.modularFile.getPath();
        for (String s : fileList) {
            File readFile = new File(funMkdirsPath + "\\" + s);
            if (readFile.getName().endsWith(".jar")) {
                list.add(readFile);
            }
        }
        return list;
    }



}
