package resource;

import java.net.URI;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;

/**
 *
 * @author Notreal
 */
public class MyJerseyIT extends JerseyTest {

    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);

        final MyApplication application = new MyApplication();
        application.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        return application;
    }

    @Override
    protected void configureClient(final ClientConfig config) {
        super.configureClient(config);

        config.register(MoxyJsonFeature.class);
    }
    
    @Override
    protected URI getBaseUri() {
        final UriBuilder baseUriBuilder = UriBuilder.fromUri(super.getBaseUri()).path("aStore_it");
        final boolean externalFactoryInUse = getTestContainerFactory() instanceof ExternalTestContainerFactory;
        System.out.println("externalFactoryInUse = " + externalFactoryInUse);
        return externalFactoryInUse ? baseUriBuilder.path("rest").build() : baseUriBuilder.build();
    }
}
