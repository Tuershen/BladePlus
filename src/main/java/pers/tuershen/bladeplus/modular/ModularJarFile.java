package pers.tuershen.bladeplus.modular;

import pers.tuershen.bladeplus.api.modular.ModularInjection;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModularJarFile {

    private final File jar;

    public ModularJarFile(File jar) {
        this.jar = jar;
    }

    public Class<? extends ModularCore> loadJar(){
        try {
            JarFile jarFile = new JarFile(jar);
            Enumeration<JarEntry> enumFiles = jarFile.entries();
            while (enumFiles.hasMoreElements()) {
                JarEntry entry = enumFiles.nextElement();
                String classFullName = entry.getName();
                if (entry.getName().endsWith(".class")) {
                    String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                    Class<?> loadClazz = Class.forName(className);
                    if (ModularCore.class.isAssignableFrom(loadClazz) && loadClazz.isAnnotationPresent(ModularInjection.class)) {
                        ModularInjection modularInjection = loadClazz.getAnnotation(ModularInjection.class);
                        return modularInjection.autoWired();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



}
