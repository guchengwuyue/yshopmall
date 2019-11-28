package co.yixiang.mp.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @author Zheng Jie
 * @date 2018-12-10
 */
@Component
public class RedisHandler{

    @Autowired
    RedisTemplate redisTemplate;


    public String getVal(String key) {
        try {
            String value = redisTemplate.opsForValue().get(key).toString();
            return value;
        }catch (Exception e){
            return "";
        }
    }


    public Object getObj(String key) {
        return redisTemplate.opsForValue().get(key);
    }


}
