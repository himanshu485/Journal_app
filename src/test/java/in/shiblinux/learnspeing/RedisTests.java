package in.shiblinux.learnspeing;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplates;

//    @Disabled
    @Test
    void testEmail(){

//        redisTemplates.opsForValue().set("email","abc@mp.com");
        Object obj=redisTemplates.opsForValue().get("TMumbai");
        int a=10;
    }
}
