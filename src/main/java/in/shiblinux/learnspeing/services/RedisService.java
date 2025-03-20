package in.shiblinux.learnspeing.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * Retrieve an object from Redis and deserialize it to the specified class type.
     *
     * @param key    The Redis key.
     * @param clazz  The class type to deserialize into.
     * @param <T>    The type of the returned object.
     * @return The deserialized object, or null if an error occurs.
     */
    public <T> T get(String key, Class<T> clazz) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value != null) {
                return clazz.cast(value); // Automatic deserialization using configured serializer
            }
        } catch (Exception e) {
            log.error("Failed to get value for key {}: {}", key, e.getMessage());
        }
        return null;
    }

    /**
     * Store an object in Redis with a specified time-to-live (TTL).
     *
     * @param key The Redis key.
     * @param value The object to store.
     * @param ttl The time-to-live in seconds.
     */
    public void set(String key, Object value, long ttl) {
        try {
            redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS); // Automatic serialization using configured serializer
        } catch (Exception e) {
            log.error("Failed to set value for key {}: {}", key, e.getMessage());
        }
    }
}


