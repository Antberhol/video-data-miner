package com.example.dailymotionminer.controller;

import com.example.dailymotionminer.exception.BadRequestException;
import com.example.dailymotionminer.model.dailymotion.Channel;
import com.example.dailymotionminer.model.dailymotion.Subtitle;
import com.example.dailymotionminer.model.dailymotion.User;
import com.example.dailymotionminer.model.dailymotion.Video;
import com.example.dailymotionminer.model.videominer.*;
import com.example.dailymotionminer.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final VideoService videoService;
    private final SubtitleService subtitleService;
    private final RestTemplate restTemplate;
    private final String videoMinerFullUrl;
    private final int defaultMaxVideos;
    private final int defaultMaxPages;

    public ChannelController(
            ChannelService channelService,
            VideoService videoService,
            SubtitleService subtitleService,
            @Value("${dailymotion.maxVideos:10}") int defaultMaxVideos,
            @Value("${dailymotion.maxPages:2}") int  defaultMaxPages,
            @Value("${videominer.base-url:http://localhost:8080}") String videoMinerBaseUrl,
            @Value("${videominer.channels-path}") String videoMinerChannelsPath) {

        this.channelService = channelService;
        this.videoService = videoService;
        this.subtitleService = subtitleService;
        this.defaultMaxVideos = defaultMaxVideos;
        this.defaultMaxPages = defaultMaxPages;
        this.restTemplate = new RestTemplate();
        this.videoMinerFullUrl = videoMinerBaseUrl + videoMinerChannelsPath;
    }

    @GetMapping("/{id}")
    public VMChannel preview(
            @PathVariable("id") String channelHandle,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxPages) {
        int videosLimit = maxVideos != null ? maxVideos: defaultMaxVideos;
        int pagesLimit = maxPages != null ? maxPages: defaultMaxPages;
        validateLimits(videosLimit, pagesLimit);
        return buildChannel(channelHandle, videosLimit, pagesLimit);
    }

    @PostMapping("/{id}")
    public VMChannel ingest(
            @PathVariable("id") String channelHandle,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxPages) {
        int videosLimit = maxVideos != null ? maxVideos : defaultMaxVideos;
        int pagesLimit = maxPages != null ? maxPages : defaultMaxPages;
        validateLimits(videosLimit, pagesLimit);
        VMChannel channel = buildChannel(channelHandle, videosLimit, pagesLimit);

        HttpEntity<VMChannel> request = new HttpEntity<>(channel);
        ResponseEntity<VMChannel> response = restTemplate.exchange(
                videoMinerFullUrl,
                HttpMethod.POST,
                request,
                VMChannel.class
        );
        VMChannel newChannel = response.getBody();

        return newChannel != null ? newChannel : channel;
    }

    private VMChannel buildChannel(String channelHandle, int maxVideos, int maxPages) {
        Channel channel = channelService.getChannel(channelHandle);
        VMChannel vmChannel = toVMChannel(channel);


        List<Video> videos = videoService.getVideosByChannel(channelHandle, maxVideos, maxPages).getVideo();
        if (videos == null) {
            videos = java.util.Collections.emptyList();
        }
        for (Video video : videos) {
            VMUser vmuser = null;
            if (video.getOwner() != null && !video.getOwner().isEmpty()) {
                vmuser = toVMUser(UserService.getUser(video.getOwner()));
            }
            VMVideo vmVideo = toVMVideo(video, vmuser);
            List<Subtitle> subtitles = subtitleService.getSubtitlesByVideo(video.getId()).getSubtitle();
            if (subtitles != null && !subtitles.isEmpty()) {
                for (Subtitle subtitle : subtitles) {
                    vmVideo.getCaptions().add(toVMCaption(subtitle));
                }
            }
            List<String> tags = video.getTags();
            if (tags != null && !tags.isEmpty()) {
                for (int i = 0; i < tags.size(); i++) {
                    vmVideo.getComments().add(toVMComment(tags.get(i), video, i));
                }
            }
            vmChannel.getVideos().add(vmVideo);
        }

        return vmChannel;
    }

    private VMChannel toVMChannel(Channel channel) {
        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(channel.getId());
        vmChannel.setName(channel.getName());
        vmChannel.setDescription(channel.getDescription());
        vmChannel.setCreatedTime(channel.getCreatedTime().toString());
        return vmChannel;
    }

    private VMVideo toVMVideo(Video video, VMUser vmuser) {
        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(video.getId());
        vmVideo.setName(video.getTitle());
        vmVideo.setDescription(video.getDescription());
        vmVideo.setReleaseTime(video.getCreatedTime().toString());
        vmVideo.setUser(vmuser);
        return vmVideo;
    }

    private VMCaption toVMCaption(Subtitle subtitle) {
        VMCaption vmCaption = new VMCaption();
        vmCaption.setId(subtitle.getId());
        vmCaption.setLink(subtitle.getUrl());
        vmCaption.setLanguage(subtitle.getLanguage());
        return vmCaption;
    }

    private void validateLimits(int maxVideos, int maxPages) {
        if (maxVideos < 0 || maxPages < 0) {
            throw new BadRequestException("maxVideos y maxComments deben ser mayores o iguales a 0");
        }
    }

    private VMComment toVMComment(String tag, Video video, int index) {
        VMComment vmComment = new VMComment();
        vmComment.setId(video.getId() + "-" + index);
        vmComment.setText(tag);
        vmComment.setCreatedOn(video.getCreatedTime().toString());
        return vmComment;
    }

    private VMUser toVMUser(User user) {
        if (user == null) {
            return null;
        }
        VMUser vmUser = new VMUser();
        vmUser.setId(user.getId());
        vmUser.setName(user.getScreenname());
        vmUser.setUserLink(user.getUrl());
        vmUser.setPictureLink(user.getAvatar720Url());
        return vmUser;
    }

}
