package com.example.redis.drift;

import com.example.redis.utils.RedisBaseUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangzhch
 * @date 2022/9/23 16:21
 */
@Component
public class RedisTest implements InitializingBean {

    @Autowired
    protected RedisBaseUtils redisBaseUtils;


    @Override
    public void afterPropertiesSet() throws Exception {

        this.doRun();

    }

    private void doRun() {

        redisBaseUtils.set("yangzhch",100);

    }
}
