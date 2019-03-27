package com.az.redis.config;


import com.az.redis.utils.RedissonUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
@ConditionalOnClass(Config.class)
public class RedissonConfig {


    @Autowired
    private RedissonProperties redissonProperties;


    @Bean
    @ConditionalOnProperty(name="redisson.address")
    RedissonClient redissonSingle(){
        Config config=new Config();
        SingleServerConfig singleServerConfig=config.useSingleServer()
                .setAddress(redissonProperties.getAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize());
        if(!StringUtils.isEmpty(redissonProperties.getPassword())) {
            singleServerConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    @Bean
    public RedissonUtils redissonUtils(RedissonClient redissonSingle){
        RedissonUtils redissonUtils=new RedissonUtils();
        redissonUtils.setRedissonClient(redissonSingle);
        return redissonUtils;
    }

}
