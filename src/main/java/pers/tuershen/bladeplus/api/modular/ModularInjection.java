package pers.tuershen.bladeplus.api.modular;


import pers.tuershen.bladeplus.modular.ModularCore;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ModularInjection {

    String name();

    String version();

    String[] info();

    Class<? extends ModularCore> autoWired();


}
