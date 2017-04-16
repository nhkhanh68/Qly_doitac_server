package Qly_dn.stereotype;

import Qly_dn.model.Role;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by nhkha on 13/04/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredRoles {
    Role[] value();
}

