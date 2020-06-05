package com.websocket.chat.repo;

import com.websocket.chat.domain.ChatMessage;
import com.websocket.chat.domain.ChatRoom;
import com.websocket.chat.domain.Group;
import com.websocket.chat.pubsub.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;


@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    private static final String CHATTINGS = "CHATTINGS";
    private final RedisTemplate<String, Object> roomRedisTemplate;
    private final RedisTemplate<String, Object> chattingRedisTemplate;
    private HashOperations<String, Integer, ChatRoom> opsHashChatRoom;
    private HashOperations<String, Integer, List<ChatMessage>> opsHaschChattings;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
    private Map<String, ChannelTopic> topics;
    @PostConstruct
    private void init() {
        opsHashChatRoom = roomRedisTemplate.opsForHash();
        opsHaschChattings = chattingRedisTemplate.opsForHash();
        topics = new HashMap<>();
    }
    public List<ChatRoom> findAllRoom() {

        System.out.println(opsHashChatRoom.values(CHAT_ROOMS));
        return opsHashChatRoom.values(CHAT_ROOMS);
    }
    public ChatRoom findRoomById(int roomId) {
        return opsHashChatRoom.get(CHAT_ROOMS, roomId);
    }
    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    public ChatRoom createChatRoom(Group group) {
        ChatRoom chatRoom = ChatRoom.create(group.getName(), group.getRoomId());
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public List<ChatRoom> deleteChatRoom(int roomId){
        opsHashChatRoom.delete(CHAT_ROOMS,roomId);
        System.out.println(opsHashChatRoom.values(CHAT_ROOMS));
        opsHaschChattings.delete(CHATTINGS,roomId);
        return opsHashChatRoom.values(CHAT_ROOMS);
    }

    public void addChatting(ChatMessage chatMessage){
        System.out.println("채팅확인~");
        List<ChatMessage> list = null;
        list = opsHaschChattings.get(CHATTINGS, chatMessage.getRoomId());
        System.out.println(list);
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())){
            return;
        }
        if(list == null){
            System.out.println("채팅없을때~");
            list = new ArrayList<>();
            System.out.println(chatMessage);
            list.add(chatMessage);
            System.out.print(list);
            opsHaschChattings.put(CHATTINGS, chatMessage.getRoomId(), list);
        }else{

            System.out.println(chatMessage);
            list.add(chatMessage);
            opsHaschChattings.delete(CHATTINGS, chatMessage.getRoomId());
            opsHaschChattings.put(CHATTINGS, chatMessage.getRoomId(), list);
        }

    }
    public List<ChatMessage> findAllChatting(int roomId) {

        System.out.println(opsHashChatRoom.values(CHAT_ROOMS));
        List<ChatMessage> list = opsHaschChattings.get(CHATTINGS, roomId);
        return list;

    }


    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
    public void enterChatRoom(int roomId) {
        ChannelTopic topic = topics.get(String.valueOf(roomId));
        if (topic == null) {
            topic = new ChannelTopic(String.valueOf(roomId));
            redisMessageListener.addMessageListener(redisSubscriber, topic);
            topics.put(String.valueOf(roomId), topic);
        }
    }
    public ChannelTopic getTopic(int roomId) {
        return topics.get(String.valueOf(roomId));
    }
}