package main.cdi.injectusingannotations;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.logging.Logger;

/**
 * This resource demonstrates the use of CDI annotations to inject beans.
 * ClassChildA doesn't have any qualifier, so it has @Default qualifier by default.
 *      As for classParent1, no qualifier is mentioned, it will inject the bean with @Default qualifier, which ClassChildA in this case.
 * ClassChildB has RandomCustomizedQualifier qualifier attached to it.
 *      As for classParent2, RandomCustomizedQualifier is mentioned, it will inject bean for ClassChildB.
 */

@Path("/cdi-annotation")
public class Resource {
    ClassParent classParent1;
    ClassParent classParent2;

    public Resource(ClassParent classParent1,
                    @RandomCustomizedQualifier ClassParent classParent2) {
        this.classParent1 = classParent1;
        this.classParent2 = classParent2;
    }

    @GET
    @Path("/resource-1")
    public String get1() {
        classParent1.hello();
        Logger.getLogger(Resource.class).info("--------------------");
        classParent2.hello();
        return "Hello from CDI annotation resource 1";
    }
}
