package com.hasindu.redissample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPoolConfig;


import javax.sql.DataSource;
import java.sql.SQLDataException;
import java.util.Arrays;
import java.util.List;

/**
 * @author hasindu_d
 */
@EnableTransactionManagement
    @EnableRedisRepositories
    public class RedisConfig {


        //    @Value("${redis.cluster.nodes}")
//    private List<String> clusterNodes;
//        @Value("${redis.host}")
//        private String redisHost;
//        @Value("${redis.port}")
//        private int redisPort;
//        @Value("${redis.password}")
//        private String redisPassword;



        @Bean
        public JedisConnectionFactory redisConnectionFactory()
        {
            List<String> clusterNodes = Arrays.asList("127.0.0.1:30001,127.0.0.1:30002,127.0.0.1:30003,127.0.0.1:30004,127.0.0.1:30005,127.0.0.1:30006");


//            JedisPoolConfig poolConfig = new JedisPoolConfig();
//            poolConfig.setMaxTotal(50);
//            poolConfig.setMinIdle(20);
//            poolConfig.setMaxIdle(30);
//            JedisConnectionFactory jedisConFactory
//                    = new JedisConnectionFactory();
//            jedisConFactory.setHostName(redisHost);
//            jedisConFactory.setPort(redisPort);
//            jedisConFactory.setPassword(redisPassword);
//            return jedisConFactory;

            return new JedisConnectionFactory(new RedisClusterConfiguration(clusterNodes));



        }
        @Bean
        public RedisTemplate<Object, Object> redisTemplate()
        {
            RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
            redisTemplate.setConnectionFactory(redisConnectionFactory());
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
            redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setDefaultSerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setEnableTransactionSupport(true);
            redisTemplate.afterPropertiesSet();
            return redisTemplate;
        }
        @Bean
        CacheManager cacheManager() {
            final RedisCacheManager redisCacheManager = RedisCacheManager.create(redisConnectionFactory());
            return redisCacheManager;
        }

}
