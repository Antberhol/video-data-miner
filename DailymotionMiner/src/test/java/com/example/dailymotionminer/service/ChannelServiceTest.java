package com.example.dailymotionminer.service;

import com.example.dailymotionminer.model.dailymotion.Channel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChannelServiceTest {
    @Autowired
    ChannelService channelService;

    @Test
    void getChannel() {
        Channel channels = channelService.getChannel("news");
        assertNotNull(channels);
        System.out.println(channels);
    }
}