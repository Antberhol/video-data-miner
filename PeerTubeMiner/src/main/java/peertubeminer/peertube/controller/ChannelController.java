package peertubeminer.peertube.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import peertubeminer.peertube.model.peertube.*;
import peertubeminer.peertube.model.videominer.*;
import peertubeminer.peertube.service.CaptionService;
import peertubeminer.peertube.service.ChannelService;
import peertubeminer.peertube.service.CommentService;
import peertubeminer.peertube.service.VideoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final VideoService videoService;
    private final CaptionService captionService;
    private final CommentService commentService;
    private final RestTemplate restTemplate;
    private final String videoMinerBaseUrl;
    private final String videoMinerFullUrl;
    private final int defaultMaxVideos;
    private final int defaultMaxComments;

    public ChannelController(
            ChannelService channelService,
            VideoService videoService,
            CaptionService captionService,
            CommentService commentService,
            @Value("${peertubeminer.maxVideos:10}") int defaultMaxVideos,
            @Value("${peertubeminer.maxComments:2}") int defaultMaxComments,
            @Value("${videominer.base-url:http://localhost:8080}") String videoMinerBaseUrl,
            @Value("${videominer.channels-path}") String videoMinerChannelsPath) {

        this.channelService = channelService;
        this.videoService = videoService;
        this.captionService = captionService;
        this.commentService = commentService;
        this.defaultMaxVideos = defaultMaxVideos;
        this.defaultMaxComments = defaultMaxComments;
        this.restTemplate = new RestTemplate();
        this.videoMinerBaseUrl = videoMinerBaseUrl;
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

    @PutMapping("/{id}")
    public VMChannel update(
            @PathVariable("id") String channelHandle,
            @RequestParam(required = false) Integer maxVideos,
            @RequestParam(required = false) Integer maxComments
    ) {
        int videosLimit = maxVideos != null ? maxVideos : defaultMaxVideos;
        int commentsLimit = maxComments != null ? maxComments : defaultMaxComments;
        validateLimits(videosLimit, commentsLimit);

        VMChannel channel = buildChannel(channelHandle, videosLimit, commentsLimit);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getVideoMinerToken());
        HttpEntity<VMChannel> request = new HttpEntity<>(channel, headers);
        restTemplate.exchange(
                videoMinerFullUrl + "/" + channel.getId(),
                HttpMethod.PUT,
                request,
                Void.class
        );
        return channel;
    }

    private String getVideoMinerToken() {
        Map<?, ?> client = restTemplate.postForObject(
                videoMinerBaseUrl + "/videominer/register/peertubeminer",
                null,
                Map.class
        );
        return client != null ? String.valueOf(client.get("token")) : "";
    }

    private VMChannel buildChannel(String channelHandle, int maxVideos, int maxComments) {
        Channel channel = channelService.getChannel(channelHandle);
        VMChannel vmChannel = toVMChannel(channel);

        List<Video> videos = videoService.getVideosByChannel(channelHandle, maxVideos).getData();
        for (Video video : videos) {
            VMVideo vmVideo = toVMVideo(video);

            for (Caption caption : captionService.getCaptionsByVideo(video.getId()).getData()) {
                vmVideo.getCaptions().add(toVMCaption(caption, vmVideo.getId()));
            }

            for (Comment comment : commentService.getCommentsByVideo(video.getId(), maxComments).getData()) {
                vmVideo.getComments().add(toVMComment(comment));
            }

            vmChannel.getVideos().add(vmVideo);
        }

        return vmChannel;
    }

    private VMChannel toVMChannel(Channel channel) {
        VMChannel vmChannel = new VMChannel();
        vmChannel.setId(channel.getId() != null ? String.valueOf(channel.getId()) : null);
        vmChannel.setName(channel.getDisplayName());
        vmChannel.setDescription(channel.getDescription());
        vmChannel.setCreatedTime(channel.getCreatedAt());
        return vmChannel;
    }

    private VMVideo toVMVideo(Video video) {
        VMVideo vmVideo = new VMVideo();
        vmVideo.setId(video.getId() != null ? String.valueOf(video.getId()) : null);
        vmVideo.setName(video.getName());
        vmVideo.setDescription(video.getDescription() != null ? video.getDescription() : video.getTruncatedDescription());
        vmVideo.setReleaseTime(video.getPublishedAt());
        vmVideo.setUser(toVMUser(video.getAccount()));
        return vmVideo;
    }

    private VMCaption toVMCaption(Caption caption, String videoId) {
        VMCaption vmCaption = new VMCaption();
        if (caption.getId() != null) {
            vmCaption.setId(String.valueOf(caption.getId()));
        } else {
            String language = caption.getLanguage() != null ? caption.getLanguage().getId() : "caption";
            vmCaption.setId(videoId + "-" + language);
        }
        vmCaption.setLink(caption.getFileUrl() != null ? caption.getFileUrl() : caption.getCaptionPath());
        vmCaption.setLanguage(caption.getLanguage() != null ? caption.getLanguage().getLabel() : null);
        return vmCaption;
    }

    private void validateLimits(int maxVideos, int maxComments) {
        if (maxVideos < 0 || maxComments < 0) {
            throw new IllegalArgumentException("maxVideos y maxComments deben ser mayores o iguales a 0");
        }
    }

    private VMComment toVMComment(Comment comment) {
        VMComment vmComment = new VMComment();
        vmComment.setId(comment.getId() != null ? String.valueOf(comment.getId()) : null);
        vmComment.setText(comment.getText());
        vmComment.setCreatedOn(comment.getCreatedAt());
        return vmComment;
    }

    private VMUser toVMUser(Account account) {
        if (account == null) {
            return null;
        }
        VMUser vmUser = new VMUser();
        vmUser.setId(account.getId() != null ? String.valueOf(account.getId()) : null);
        vmUser.setName(account.getDisplayName() != null ? account.getDisplayName() : account.getName());
        vmUser.setUserLink(account.getUrl());
        vmUser.setPictureLink(resolvePicture(account.getAvatars()));
        return vmUser;
    }

    private String resolvePicture(List<Avatar> avatars) {
        if (avatars == null || avatars.isEmpty()) {
            return null;
        }
        Avatar first = avatars.getFirst();
        return first.getFileUrl() != null ? first.getFileUrl() : first.getPath();
    }
}
