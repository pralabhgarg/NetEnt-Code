package com.demo.evaluate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.DefaultRedisCachePrefix;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import com.demo.evaluate.domain.LogicTree;

import lombok.extern.apachecommons.CommonsLog;

@Configuration
@EnableCaching
@CommonsLog
public class CacheConfig extends CachingConfigurerSupport {
    private static final int PARAM_POSITION = 0;
    private static final int LPARAM_POSITION = 1;
    private static final int LNGCODE_PARAM_POSITION = 2;
    private static final String LANGUAGES = "languages:";
    private static final String MODELS = "/models:";
    private static final String OPTION_DETAILS = "/details";


    private CacheConfigProperties cacheConfigProperties;

    public CacheConfig() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Autowired
    public void setTemplate(@Qualifier("redisTemplate") RedisTemplate template) {
        template.setKeySerializer(template.getStringSerializer());
    }

    @Autowired
    public void setCacheConfigProperties(CacheConfigProperties cacheConfigProperties) {
        this.cacheConfigProperties = cacheConfigProperties;
    }

    @Autowired
	public void setRedisCacheManager(@Qualifier("cacheManager") RedisCacheManager redisCacheManager) {
		redisCacheManager.setCachePrefix(new DefaultRedisCachePrefix(""));
	}

    @Bean
    public KeyGenerator catalogOptionKeyGenerator() {
        return (o, method, params) -> this.cacheConfigProperties.getKeyUrl()
                + LANGUAGES + (params[LNGCODE_PARAM_POSITION]
                + MODELS + params[PARAM_POSITION]
                + "" + params[LPARAM_POSITION]).toLowerCase();
    }

    /**
     * Key Generator for generating keys for the Cache catalog-option option map
     * <p>
     * params order is same as order in
     * Used in OptionServiceImpl
     *
     * @return bean keyGenerator
     */
    @Bean
    public KeyGenerator catalogOptionDetailsKeyGenerator() {
        return (target, method, params) -> {
            LogicTree logictree = (LogicTree) params[0];
            return this.cacheConfigProperties.getKeyUrl()
                    + LANGUAGES + (logictree.getLanguageCode()
                    + MODELS + logictree.getCode()
                    + "" + logictree.getPackCode()).toLowerCase()
                    + OPTION_DETAILS;
        };
    }


    /**
     * This class will only log errors for any cache issues, instead of failing.
     * If the cache is not available, the repositories/services will go to the
     * database directly.
     *
     */
    private static class CustomCacheErrorHandler extends SimpleCacheErrorHandler {
        @Override
        public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
            log.error("Error getting data :" + key + " from cache:" + exception.getMessage());
        }

        @Override
        public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
            log.error("Error writing data to cache :" + key + " :" + exception.getMessage());
        }

        @Override
        public void handleCacheClearError(RuntimeException exception, Cache cache) {
            log.error("Error clearing cache :" + exception.getMessage());
        }

        @Override
        public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
            log.error("Error evicting cache data :" + key + ":" + exception.getMessage());
        }

    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }
}
