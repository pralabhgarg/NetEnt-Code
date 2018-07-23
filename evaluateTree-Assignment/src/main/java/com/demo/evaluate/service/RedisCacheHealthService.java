package com.demo.evaluate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.RedisHealthIndicator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import lombok.extern.apachecommons.CommonsLog;

/**
 * This redis health indicator has been created so that we can return a different status
 * when the redis cache is down and redis health can be ignored in the final aggregate health  
 * status of the microservice. So, if redis is DOWN, microservice health status would still be UP.
 */
@Component(value="redisHealthIndicator")
@CommonsLog
@RefreshScope
public class RedisCacheHealthService extends RedisHealthIndicator {

    /**
     * Status code to be set when optional service is down.
     */
    @Value("${catalogoption.health.optionalServiceDownStatus}")
    private String optionalServiceDownStatus;   

    public RedisCacheHealthService(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        try {
            super.doHealthCheck(builder);
        }
        catch (Exception ex) {
            if (log.isTraceEnabled()) {
                log.trace("Returning Health Status of [Redis] = [" + optionalServiceDownStatus + ']');
            }
            builder.status(optionalServiceDownStatus).withException(ex);
        }
    }

}
