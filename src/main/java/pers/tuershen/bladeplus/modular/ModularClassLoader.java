package pers.tuershen.bladeplus.modular;

import pers.tuershen.bladeplus.BladePlusMain;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class ModularClassLoader {

    private final List<File> classFile;

    public ModularClassLoader(List<File> classFile) {
        this.classFile = classFile;
    }

    public void loadClass(){
        this.classFile.forEach(f -> {
            if (f != null) {
                try {
                    loadClassInBukkitClassLoader(f);
                } catch (NoSuchMethodException
                        | MalformedURLException
                        | IllegalAccessException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void loadClassInBukkitClassLoader(File file) throws
            NoSuchMethodException,
            java.net.MalformedURLException,
            IllegalAccessException,
            java.lang.reflect.InvocationTargetException {
        Method method = null;
        boolean accessible = false;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            accessible = method.isAccessible();
            method.setAccessible(true);
            URLClassLoader classLoader = (URLClassLoader) BladePlusMain
                    .bladePlusMain
                    .getClass()
                    .getClassLoader();
            URL url = file.toURI().toURL();
            method.invoke(classLoader, url);
        } finally {
            assert method != null;
            method.setAccessible(accessible);
        }
    }



}
