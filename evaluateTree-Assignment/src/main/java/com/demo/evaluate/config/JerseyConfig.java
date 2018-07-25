epackage com.demo.evaluate.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.demo.evaluate.util.CatalogOptionUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Config class for Jersey
 * <p>
 * Instruct Jersey to send our Bean Validation errors back to the caller in a 4xx/5xx response.
 * Register all the resources
 * set the packages to scan
 * <p>
 * Remainder of the Jersey config is automatically done by Spring Boot.
 */
@Slf4j
@Configuration
@ApplicationPath("/evaluate-tree")
public class JerseyConfig extends ResourceConfig {

    @Value("${evaluatetree.releaselevel:-1}")
    private String releaseLevel;

    @Value("${evaluatetree.datamode:runtime}")
    private String dataMode;

    /**
     * Set property so that the error is included in 4xx/5xx responses.
     */
    public JerseyConfig() {
        super();
        packages("com.demo.evaluate.controllers","com.demo.evaluate.kryo");
        register(io.swagger.jaxrs.listing.AcceptHeaderApiListingResource.class);
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, "true");
        property(ServerProperties.MEDIA_TYPE_MAPPINGS, getMediaType());
    }

    @PostConstruct
    public void init() {
        if(CatalogOptionUtil.isTopMaster(dataMode) && "-1".equalsIgnoreCase(releaseLevel)) {
            log.error("Release Level should be set in application.yml when data Mode is "+dataMode);
            System.exit(0);
        }
    }

    /**
     * add possible mediaType extensions that your service supports
     */
    private static Map<String, MediaType> getMediaType(){
        Map<String, MediaType> mappings = new HashMap<>();
        mappings.put("json", MediaType.APPLICATION_JSON_TYPE);
        mappings.put("xml", com.demo.evaluate.kryo.MediaType.APPLICATION_XML_TYPE);
        return mappings;
    }
}
