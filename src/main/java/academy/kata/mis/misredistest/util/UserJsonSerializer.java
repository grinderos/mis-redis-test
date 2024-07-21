package academy.kata.mis.misredistest.util;

import academy.kata.mis.misredistest.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class UserJsonSerializer implements RedisSerializer<User> {

    private final ObjectMapper objectMapper;

    public UserJsonSerializer() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public byte[] serialize(User user) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(user); // Сериализация в JSON
        } catch (JsonProcessingException e) {
            throw new SerializationException("Could not serialize user", e);
        }
    }

    @Override
    public User deserialize(byte[] bytes) throws SerializationException {
        try {
            return objectMapper.readValue(bytes, User.class); // Десериализация из JSON
        } catch (Exception e) {
            throw new SerializationException("Could not deserialize user", e);
        }
    }
}