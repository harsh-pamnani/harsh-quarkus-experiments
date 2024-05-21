package main.db;

import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.enterprise.context.RequestScoped;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameCase;
import org.jooq.conf.RenderQuotedNames;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

public class DslContextProducer {

    @Inject
    AgroalDataSource dataSource;

    @Produces
    @RequestScoped
    public DSLContext getDslContext() {
        try {
            return DSL.using(getConfiguration());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Configuration getConfiguration() {
        return new DefaultConfiguration().set(dataSource)
                                         .set(SQLDialect.MYSQL)
                                         .set(new Settings().withExecuteLogging(true)
                                                            .withRenderFormatted(true)
                                                            .withRenderCatalog(false)
                                                            .withRenderSchema(false)
                                                            .withRenderQuotedNames(RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED)
                                                            .withRenderNameCase(RenderNameCase.LOWER_IF_UNQUOTED));
    }
}
