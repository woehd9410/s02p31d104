package com.help.back.backend.service;

import com.help.back.backend.dto.ICS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class RedisService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    private static final String GoogleUrl = "GoogleCalender";
    private HashOperations<String,Integer, String> vop;

    @PostConstruct
    private void init() {
        vop = redisTemplate.opsForHash();
    }


    public String addUrl(ICS ics){
        vop.put(GoogleUrl, ics.getId(), ics.getUrl());
        return vop.get(GoogleUrl, ics.getId());
    }

    public String getUrl(int id){
        System.out.println("들어옴");
        String url = vop.get(GoogleUrl, id);
        System.out.println(url);
        if(url == null){
            return null;
        }else{
            return url;
        }
    }

    public void deleteUrl(int id){
        vop.delete(GoogleUrl, id);
        return;
    }

}
