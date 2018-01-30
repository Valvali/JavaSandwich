package com.airhacks;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(org.lpro.boundary.CategorieResource.class);
        classes.add(org.lpro.boundary.CategorieNotFound.class);
        classes.add(org.lpro.boundary.CategorieNotFoundMapper.class);
        classes.add(com.github.phillipkruger.apiee.ApieeService.class);
        return classes;
    }

}
