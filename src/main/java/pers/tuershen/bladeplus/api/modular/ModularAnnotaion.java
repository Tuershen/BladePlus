package pers.tuershen.bladeplus.modular;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModularAnnotaion {

    ModularType modularType();

}
