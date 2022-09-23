package com.example.redis.drift;

import com.example.redis.utils.RedisBaseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author yangzhch
 * @date 2022/9/23 16:21
 */
@Component
@Slf4j
public class RedisIncrTest implements InitializingBean {

    @Autowired
    protected RedisBaseUtils redisBaseUtils;
    @Autowired
    protected RedisTemplate redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {

        this.doRun();

    }

    private void doRun() {

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                redisTemplate.opsForValue().increment("yangzhch",1);
                log.info("thread {} done", finalI);
            }).start();
        }

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                redisTemplate.opsForValue().decrement("yangzhch",1);
                log.info("thread {} done", finalI);
            }).start();
        }



    }
}
