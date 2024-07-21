//package academy.kata.mis.misredistest.caching;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.listener.ChannelTopic;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//@NoArgsConstructor
//public class MessagePublisherImpl implements MessagePublisher {
//    private RedisTemplate<String, Object> redisTemplate;
//    private ChannelTopic topic;
//
//    public void publish(final String message) {
//        redisTemplate.convertAndSend(topic.getTopic(), message);
//    }
//}
