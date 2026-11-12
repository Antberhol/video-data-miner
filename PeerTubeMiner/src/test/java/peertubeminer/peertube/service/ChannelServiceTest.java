package peertubeminer.peertube.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import peertubeminer.peertube.model.peertube.Channel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChannelServiceTest {

    @Autowired
    ChannelService channelService;

    @Test
    @DisplayName("Deberia obtener el canal de un video dado su handle")
    void getChannel() {
        Channel canal = channelService.getChannel("defy@peertube.uno");
        assertNotNull(canal);
        System.out.println("Canal obtenido: " + canal);
    }
}