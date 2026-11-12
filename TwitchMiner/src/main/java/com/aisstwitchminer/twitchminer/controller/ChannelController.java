package com.aisstwitchminer.twitchminer.controller;

import com.aisstwitchminer.twitchminer.exception.BadRequestException;
import com.aisstwitchminer.twitchminer.exception.ExternalServiceException;
import com.aisstwitchminer.twitchminer.model.twitchminer.*;
import com.aisstwitchminer.twitchminer.model.videominer.*;
import com.aisstwitchminer.twitchminer.service.CaptionService;
import com.aisstwitchminer.twitchminer.service.ChannelService;
import com.aisstwitchminer.twitchminer.service.UserService;
import com.aisstwitchminer.twitchminer.service.VideoService;
import com.aisstwitchminer.twitchminer.service.CommentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@RequestMapping("/helix/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final VideoService videoService;
    private final CaptionService captionService;
    private final CommentService commentService;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final String videoMinerFullUrl;
    private final int defaultMaxVideos;
    private final int defaultMaxComments;

    public ChannelController(
            ChannelService channelService,
            VideoService videoService,
            CaptionService captionService,
            CommentService commentService,
            UserService userService,
            @Value("${twitchminer.maxVideos:10}") int defaultMaxVideos,
            @Value("${twitchminer.maxComments:2}") int defaultMaxComments,
            @Value("${videominer.base-url:http://localhost:8080}") String videoMinerBaseUrl,
            @Value("${videominer.channels-path}") String videoMinerChannelsPath) {

        this.channelService = channelService;
        this.videoService = videoService;
        this.captionService = captionService;
        this.commentService = commentService;
        this.userService = userService;
        this.defaultMaxVideos = defaultMaxVideos;
        this.defaultMaxComments = defaultMaxComments;
        this.restTemplate = new RestTemplate();
        this.videoMinerFullUrl = videoMinerBaseUrl + videoMinerChannelsPath;
    }

    @GetMapping("/{id}")
    public VMChannel preview(
            @PathVariable("id") String channelHandle,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxComments) {
        int videosLimit = maxVideos != null ? maxVideos : defaultMaxVideos;
        int commentsLimit = maxComments != null ? maxComments : defaultMaxComments;
        validateLimits(videosLimit, commentsLimit);
        return buildChannel(channelHandle, videosLimit, commentsLimit);
    }

    @PostMapping("/{id}")
    public VMChannel ingest(
            @PathVariable("id") String channelHandle,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxComments) {
        int videosLimit = maxVideos != null ? maxVideos : defaultMaxVideos;
        int commentsLimit = maxComments != null ? maxComments : defaultMaxComments;
        validateLimits(videosLimit, commentsLimit);
        VMChannel channel = buildChannel(channelHandle, videosLimit, commentsLimit);

        VMChannel newChannel;
        try {
            HttpEntity<VMChannel> request = new HttpEntity<>(channel);
            ResponseEntity<VMChannel> response = restTemplate.exchange(
                    videoMinerFullUrl,
                    HttpMethod.POST,
                    request,
                    VMChannel.class
            );
            newChannel = response.getBody();
        } catch (org.springframework.web.client.HttpStatusCodeException ex) {
            System.err.println("TwitchMiner ha rechazado la peticion (HttpStatusCodeException):");
            System.err.println("Status: " + ex.getStatusCode());
            System.err.println("Body: " + ex.getResponseBodyAsString());
            throw new ExternalServiceException("VideoMiner respondio con error: " + ex.getStatusCode(), ex);
        } catch (RuntimeException ex) {
            System.err.println("Fallo al enviar datos a VideoMiner (RuntimeException): " + ex.getMessage());
            ex.printStackTrace();
            throw new ExternalServiceException("Error enviando datos a VideoMiner", ex);
        }

        return newChannel != null ? newChannel : channel;
    }

    private VMChannel buildChannel(String broadcasterId, int maxVideos, int maxComments) {
        Channel channel = channelService.getChannel(broadcasterId);
        User owner = userService.getUser(broadcasterId);
        VMChannel vmChannel = toVMChannel(channel, owner);
        VMUser vmOwner = toVMUser(owner);

        List<Game> games = captionService.getGame(channel.getGameId()).getData();
        List<Clip> clips = maxComments > 0
                ? commentService.getClipsByBroadcaster(broadcasterId).getData()
                : List.of();

        for (Stream video : videoService.getVideosByChannel(broadcasterId, maxVideos).getData()) {
            VMVideo vmVideo = toVMVideo(video);
            vmVideo.setUser(broadcasterId.equals(video.getUserId()) ? vmOwner : toVMUser(userService.getUser(video.getUserId())));

            for (Game game : games) {
                vmVideo.getCaptions().add(toVMCaption(game, video));
            }

            clips.stream()
                    .filter(clip -> video.getId().equals(clip.getVideoId()))
                    .limit(maxComments)
                    .map(this::toVMComment)
                    .forEach(vmVideo.getComments()::add);

            vmChannel.getVideos().add(vmVideo);
        }

        return vmChannel;
    }

    private VMChannel toVMChannel(Channel channel, User owner) {
        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(channel.getBroadcasterId());
        vmChannel.setName(channel.getBroadcasterName());
        vmChannel.setDescription(owner.getDescription());
        vmChannel.setCreatedTime(owner.getCreatedAt());
        return vmChannel;
    }

    private VMVideo toVMVideo(Stream video) {
        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(video.getId());
        vmVideo.setName(video.getTitle());
        vmVideo.setDescription(video.getDescription() != null && !video.getDescription().isBlank()
                ? video.getDescription()
                : VideoService.joinedTags(video.getTags()));
        vmVideo.setReleaseTime(video.getPublishedAt() != null ? video.getPublishedAt() : video.getCreatedAt());
        return vmVideo;
    }

    private VMCaption toVMCaption(Game game, Stream video) {
        VMCaption vmCaption = new VMCaption();
        vmCaption.setId(video.getId() + "-" + game.getId());
        vmCaption.setLink(game.getBoxArtUrl() != null ? game.getBoxArtUrl() : game.getIgdbId());
        vmCaption.setLanguage(game.getName());
        return vmCaption;
    }

    private void validateLimits(int maxVideos, int maxComments) {
        if (maxVideos < 0 || maxComments < 0) {
            throw new BadRequestException("maxVideos y maxComments deben ser mayores o iguales a 0");
        }
    }

    private VMComment toVMComment(Clip clip) {
        VMComment vmComment = new VMComment();
        vmComment.setId(clip.getId());
        vmComment.setText(clip.getUrl());
        vmComment.setCreatedOn(clip.getCreatedAt());
        return vmComment;
    }

    private VMUser toVMUser(User user) {
        if (user == null) {
            return null;
        }
        VMUser vmUser = new VMUser();
        vmUser.setId(user.getId());
        vmUser.setName(user.getDisplayName() != null ? user.getDisplayName() : user.getLogin());
        vmUser.setUserLink(user.getLogin() != null ? "https://www.twitch.tv/" + user.getLogin() : null);
        vmUser.setPictureLink(user.getProfileImageUrl());
        return vmUser;
    }

}