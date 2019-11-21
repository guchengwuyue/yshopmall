//package co.yixiang.config;
//
//import cn.hutool.core.util.StrUtil;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * redisson 配置类
// * Created on 2018/6/19
// */
//@Configuration
//public class RedissonConfig {
//
//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private String port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.database}")
//    private Integer database;
//
//   // @Bean
//    public RedissonClient getRedisson(){
//
//        Config config = new Config();
//        if(StrUtil.isNotEmpty(password)){
//            config.useSingleServer().setAddress("redis://" + host + ":" + port)
//                    .setPassword(password).setDatabase(database);
//        }else{
//            config.useSingleServer().setAddress("redis://" + host + ":" + port).setDatabase(database);
//        }
//        //config.useSingleServer().setAddress("redis://" + host + ":" + port).setPassword(password).setDatabase(database);
//        //添加主从配置
////        config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
//
//        return Redisson.create(config);
//    }
//
//}
