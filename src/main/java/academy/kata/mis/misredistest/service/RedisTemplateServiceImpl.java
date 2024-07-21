package academy.kata.mis.misredistest.service;

import academy.kata.mis.misredistest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Service
public class RedisTemplateServiceImpl implements RedisTemplateService {

    private final String KEY;
    private final Duration TTL;
    private final RedisTemplate<String, User> redisTemplate;
    private final HashOperations<String, Long, User> redisHash;
    private final ValueOperations<String, User> redisValue;

    @Autowired
    public RedisTemplateServiceImpl(RedisTemplate<String, User> redisTemplate,
                                    @Value("${redis.bucket.users.key}") String usersKey,
                                    @Value("${redis.bucket.users.ttl}") Long ttlInSecond) {
        this.redisTemplate = redisTemplate;
        this.KEY = usersKey;
        this.TTL = Duration.ofSeconds(ttlInSecond);
        redisHash = this.redisTemplate.opsForHash();
        redisValue = this.redisTemplate.opsForValue();
    }

    public User getUser(Long id) {
//        return redis.get(KEY, id);
        return redisValue.get(KEY + id);
    }

    public List<User> getAllUsers() {
        return redisValue.multiGet(redisTemplate.keys(KEY+"*"));
    }

    public User addUser(Long id, User user) {
        System.out.println("redis addUser");
        redisValue.setIfAbsent(KEY + id, user, TTL);
        return redisValue.get(KEY + id);
//        redisHash.put(KEY, user.getId(), user);
//        return redisValue.get(KEY + id);
    }

    public User deleteUser(Long id) {
        User user = redisValue.getAndDelete(KEY + id);
        System.out.println("delete " + user + " by id = " + id);
        return user;
    }

    public User updateUser(Long id, String name, String lastName) {
        User user = redisValue.get(KEY + id);
        String updatedName = name != null ? name : user.getName();
        String updatedLastName = lastName != null ? lastName : user.getLastName();
        user.setName(updatedName);
        user.setLastName(updatedLastName);
        redisValue.set(KEY + id, user, TTL);
        return redisValue.get(KEY + id);
    }

}
