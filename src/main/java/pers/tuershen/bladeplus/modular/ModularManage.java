package pers.tuershen.bladeplus.modular;

import pers.tuershen.bladeplus.BladePlusMain;
import pers.tuershen.bladeplus.api.modular.ModularAnnotaion;
import pers.tuershen.bladeplus.api.modular.ModularInjection;
import pers.tuershen.bladeplus.api.modular.ModularType;
import pers.tuershen.bladeplus.bukkit.command.admin.ACommandHelp;
import pers.tuershen.bladeplus.modular.io.ReadConfiguration;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ModularManage<T extends ModularCore> {

    public Map<String, T> modularMap = new HashMap<>();

    private List<File> fileList;

    private List<Class<? extends ModularCore>> modularList = new ArrayList<>();

    public void loadModular(ReadConfiguration readConfiguration) {
        fileList = readConfiguration.getModularList();
        ModularClassLoader classLoader = new ModularClassLoader(fileList);
        classLoader.loadClass();
        fileList.forEach(file -> {
            if (file != null) {
                this.modularList.add(new ModularJarFile(file).loadJar());
            }
        } );
        this.modularList.forEach(fun -> {
            try {
                ModularCore abstractEditManager = fun.newInstance();
                abstractEditManager.onEnable();
            } catch (InstantiationException|IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }



    public void initModular(){
        this.modularMap.forEach((k , v) -> {
            Class<? extends ModularCore> modularClass = v.getClass();
            if (modularClass.isAnnotationPresent(ModularInjection.class)){
                Method[] modularDeclaredMethods = modularClass.getDeclaredMethods();
                analysisMethod(v, modularDeclaredMethods);
                Field[] modularDeclaredField = modularClass.getDeclaredFields();
                analysisField(v, modularDeclaredField);
            }
        });
    }

    public <T extends ModularCore> void analysisMethod(T modularCore, Method... methods){
        for (Method method : methods) {
            if (method.isAnnotationPresent(ModularAnnotaion.class)) {
                ModularAnnotaion modularAnnotaion = method.getDeclaredAnnotation(ModularAnnotaion.class);
                if (modularAnnotaion.modularType() == ModularType.REGISTER_FILE) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    for (Class<?> parameterType : parameterTypes) {
                        if (parameterType.isAnnotationPresent(ModularAnnotaion.class)) {
                            ModularAnnotaion fileModularAnnotaion = parameterType.getAnnotation(ModularAnnotaion.class);
                            if (fileModularAnnotaion.modularType() == ModularType.FILE) {
                                try {
                                    method.invoke(modularCore, BladePlusMain.bladePlusMain.getDataFolder());
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public <T extends ModularCore>  void analysisField(T modularCore, Field[] fields){
        for (Field field : fields) {
            if (field.isAnnotationPresent(ModularAnnotaion.class)){
                ModularAnnotaion fieldModular = field.getAnnotation(ModularAnnotaion.class);
                ModularType modularType = fieldModular.modularType();
                if (modularType == ModularType.COMMAND_HELP) {
                    try {
                        List<String> helpInfo = (List<String>) field.get(modularCore);
                        ACommandHelp.registerHelp(modularCore.getCode(), helpInfo);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }











}
