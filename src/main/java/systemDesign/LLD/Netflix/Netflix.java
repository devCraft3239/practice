package systemDesign.LLD.Netflix;

import lombok.Builder;
import systemDesign.LLD.utility.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 Design a streaming service like Netflix or Youtube.
 Functional Requirements:
     Users should be able to upload videos.
     Users should have ability to share and view videos.
     Users should be able to perform searches based on video titles.
     Service should have starts on videos, e.g likes/dislikes, no. of views, etc.
     Users should be able to add and view comments on videos

 use-case:
  System:
        - notification
        - recommendation
  Admin:
        - add/remove videos
        - add/remove categories
  user:
    - singup/login
    - upload video
    - search video
    - view video
    - like/dislike video
    - add comment

class diagram:
    User:
        - Person
        - Admin
        - System
    Video - builder pattern
    Comment
    Notification - factory pattern
    Payment - strategy pattern
    Order - builder pattern
    Like - builder pattern  // optional
    Dislike - builder pattern // optional
    Search - predicate pattern, strategy pattern
    Recommendation - observer pattern
    VideoPlayer - singleton
    VideoUploader - singleton
    VideoEditor - singleton
    VideoEncoder - singleton
    VideoDecoder - singleton
    VideoCompressor - singleton
    VideoDecompressor - singleton
    VideoConverter - singleton
    VideoSplitter - singleton

 */

class User {
    String userId;
    String name;
    String email;
    String password;
    AccountStatus accountStatus;
    AccountType accountType;
}
public class Netflix {
}

enum ContentType {
    MOVIE, TV_SHOW, CLIP, SHORTS
}

enum Genre {
    ACTION, COMEDY, DRAMA, HORROR, ROMANCE, SCI_FI, THRILLER
}

enum VideoQuality {
    HD, SD, UHD, FOURK
}

enum VideoType {
    MP4, AVI, MKV, FLV
}

enum AccountStatus {
    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
}

enum AccountType {
    FREE, BASIC, STANDARD, PREMIUM
}

enum OrderStatus {
    PENDING, FAILURE, SUCCESS, CANCELLED, REFUNDED
}

class Video {
    String title;
    String description;
    int duration;
    int size;
    VideoQuality videoQuality;
    VideoType videoType;
    ContentType contentType;
    List<Genre> genres;
    String url;
    int totalLikes;
    int totalDislikes;
    int totalViews;
    List<Comment> comments;

    public Video(VideoBuilder videoBuilder) {
        this.title = videoBuilder.title;
        this.description = videoBuilder.description;
        this.duration = videoBuilder.duration;
        this.size = videoBuilder.size;
        this.videoQuality = videoBuilder.videoQuality;
        this.videoType = videoBuilder.videoType;
        this.contentType = videoBuilder.contentType;
        this.genres = videoBuilder.genres;
        this.url = videoBuilder.url;
        this.totalLikes = videoBuilder.totalLikes;
        this.totalDislikes = videoBuilder.totalDislikes;
        this.totalViews = videoBuilder.totalViews;
        this.comments = videoBuilder.comments;
    }
}

class VideoBuilder{
String title;
    String description;
    int duration;
    int size;
    VideoQuality videoQuality;
    VideoType videoType;
    ContentType contentType;
    List<Genre> genres;
    String url;
    int totalLikes;
    int totalDislikes;
    int totalViews;
    List<Comment> comments;
    public VideoBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    public VideoBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    public VideoBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }
    public VideoBuilder setSize(int size) {
        this.size = size;
        return this;
    }
    public VideoBuilder setVideoQuality(VideoQuality videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }
    public VideoBuilder setVideoType(VideoType videoType) {
        this.videoType = videoType;
        return this;
    }
    public VideoBuilder setContentType(ContentType contentType) {
        this.contentType = contentType;
        return this;
    }
    public VideoBuilder setGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }
    public VideoBuilder setUrl(String url) {
        this.url = url;
        return this;
    }
    public VideoBuilder setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
        return this;
    }
    public VideoBuilder setTotalDislikes(int totalDislikes) {
        this.totalDislikes = totalDislikes;
        return this;
    }
    public VideoBuilder setTotalViews(int totalViews) {
        this.totalViews = totalViews;
        return this;
    }
    public VideoBuilder setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
    public Video build() {
        return new Video(this);
    }
    public VideoBuilder() {
    }
    public VideoBuilder(Video video) {
        this.title = video.title;
        this.description = video.description;
        this.duration = video.duration;
        this.size = video.size;
        this.videoQuality = video.videoQuality;
        this.videoType = video.videoType;
        this.contentType = video.contentType;
        this.genres = video.genres;
        this.url = video.url;
        this.totalLikes = video.totalLikes;
        this.totalDislikes = video.totalDislikes;
    }
}

class Comment {
    String commentId;
    String userId;
    String comment;
    int totalLikes;
    int totalDislikes;
}

@Builder
class Order {
    String orderId;
    String userId;
    String subscriptionId;
    OrderStatus orderStatus;
}

// payment class and payment strategy
// notification class and notification factory

class VideoPlayer { // singleton
    private static VideoPlayer videoPlayer;
    private VideoPlayer() {
    }
    public static VideoPlayer getInstance() {
        if (videoPlayer == null) {
            videoPlayer = new VideoPlayer();
        }
        return videoPlayer;
    }
    public void playVideo(Video video) {

    }
}

class VideoUploader { // singleton
    private static VideoUploader videoUploader;
    private VideoUploader() {
    }
    public static VideoUploader getInstance() {
        if (videoUploader == null) {
            videoUploader = new VideoUploader();
        }
        return videoUploader;
    }
    public void uploadVideo(Video video) {
    }
}

class VideoEncoder { // singleton
    private static VideoEncoder videoEncoder;
    private VideoEncoder() {
    }
    public static VideoEncoder getInstance() {
        if (videoEncoder == null) {
            videoEncoder = new VideoEncoder();
        }
        return videoEncoder;
    }
    public void encodeVideo(Video video) {
        // encode video with different formats
        // mp4, avi, mkv, flv
    }
}

// <------ Service classes ------>

class UserService {
    public void registerUser(User user) {
    }

    public void loginUser(String username, String password) {
    }

    public void logoutUser(String username) {
    }
}

class VideoService{
    public void uploadVideo(Video video) {
        VideoUploader.getInstance().uploadVideo(video);
        VideoEncoder.getInstance().encodeVideo(video);
    }

    public void deleteVideo(Video video) {
    }

    public void updateVideo(Video video) {
    }

    public void shareVideo(Video video) {
    }

    public void likeVideo(Video video) {
    }

    public void dislikeVideo(Video video) {
    }

    public void commentVideo(Video video) {
    }

    public void searchVideo(String title) {
    }

    public void viewVideo(Video video) {
    }

    Video getVideo(String videoId) {
        // get video
        return null;
    }
}

enum SubscriptionStatus {
    ACTIVE, INACTIVE, CANCELLED, EXPIRED
}

enum SubscriptionType {
    MONTHLY, QUARTERLY, YEARLY
}
@Builder
class Subscription{
    String subscriptionId;
    private String userId;
    private AccountType accountType;
    private SubscriptionType subscriptionType;
    private Date startDate;
    private Date endDate;
    private SubscriptionStatus status;
}

class SubscriptionService {
    private PaymentService paymentService;
    NotificationService notificationService;
    public void subscribeUser(User user, AccountType accountType) {
        // create subscription
        Subscription subscription = Subscription.builder()
                .userId(user.userId)
                .accountType(accountType)
                .subscriptionType(SubscriptionType.MONTHLY)
                .startDate(new Date())
                .endDate(new Date())
                .status(SubscriptionStatus.ACTIVE)
                .build();
        // create Order
        Order order = Order.builder()
                .userId(user.userId)
                .subscriptionId(subscription.subscriptionId)
                .orderStatus(OrderStatus.SUCCESS)
                .build();
        // create payment
        paymentService.processPayment(PaymentType.UPI, Payment.builder()
                .userId(user.userId)
                .amount(100)
                .status(PaymentStatus.COMPLETED)
                .type(PaymentType.UPI)
                .build());
        // send notification
        notificationService.sendNotification(NotificationType.EMAIL, Notification.builder()
                .userId(user.userId)
                .message("Your subscription is activated")
                .build());
    }

    public void unsubscribeUser(User user) {
        // cancel subscription
        // cancel payment
        // refund
    }

    public void upgradeSubscription(User user, AccountType accountType) {
        // upgrade subscription
        // create payment
        // pay
    }

    public void downgradeSubscription(User user, AccountType accountType) {
        // downgrade subscription
        // create payment
        // pay
    }

    public void cancelSubscription(User user) {
        // cancel subscription
        // cancel payment
        // refund
    }

    public void renewSubscription(User user) {
        // renew subscription
        // create payment
        // pay
    }
}

class CatalogService {
    List<Video> videos;

    SearchPredicateBuilder searchPredicateBuilder;
    CatalogService() {
        videos = new ArrayList<>();
        searchPredicateBuilder = new SearchPredicateBuilder();
    }

    public List<Video> searchVideos(String title, int duration, int size, VideoQuality videoQuality, VideoType videoType, ContentType contentType, Genre genre) {
        searchPredicateBuilder.byTitle(title)
                .byDuration(duration)
                .bySize(size)
                .byVideoQuality(videoQuality)
                .byVideoType(videoType)
                .byContentType(contentType)
                .byGenre(genre);
        return searchPredicateBuilder.search(videos);
    }
    public List<Video> getVideosByGenre(Genre genre) {
        return searchPredicateBuilder.byGenre(genre).search(videos);
    }

    public List<Video> getVideosByTitle(String title) {
        return searchPredicateBuilder.byTitle(title).search(videos);
    }

    public List<Video> getVideosByType(ContentType contentType) {
        return searchPredicateBuilder.byContentType(contentType).search(videos);
    }

    public List<Video> getVideosByDuration(int duration) {
        return searchPredicateBuilder.byDuration(duration).search(videos);
    }

    public List<Video> getVideosByQuality(VideoQuality videoQuality) {
        return searchPredicateBuilder.byVideoQuality(videoQuality).search(videos);
    }

    public List<Video> getVideosBySize(int size) {
        return searchPredicateBuilder.bySize(size).search(videos);
    }

    public List<Video> getVideosByType(VideoType videoType) {
        return searchPredicateBuilder.byVideoType(videoType).search(videos);
    }
}

class SearchPredicateBuilder {

    Predicate<Video> videoPredicate;
    SearchPredicateBuilder() {
        videoPredicate = video -> true;
    }

    public SearchPredicateBuilder byTitle(String title) {
        videoPredicate = videoPredicate.and(video -> video.title.contains(title));
        return this;
    }

    public SearchPredicateBuilder byDuration(int duration) {
        videoPredicate = videoPredicate.and(video -> video.duration <= duration);
        return this;
    }

    public SearchPredicateBuilder bySize(int size) {
        videoPredicate = videoPredicate.and(video -> video.size == size);
        return this;
    }

    public SearchPredicateBuilder byVideoQuality(VideoQuality videoQuality) {
        videoPredicate = videoPredicate.and(video -> video.videoQuality == videoQuality);
        return this;
    }

    public SearchPredicateBuilder byVideoType(VideoType videoType) {
        videoPredicate = videoPredicate.and(video -> video.videoType == videoType);
        return this;
    }

    public SearchPredicateBuilder byContentType(ContentType contentType) {
        videoPredicate = videoPredicate.and(video -> video.contentType == contentType);
        return this;
    }

    public SearchPredicateBuilder byGenre(Genre genre) {
        videoPredicate = videoPredicate.and(video -> video.genres.contains(genre));
        return this;
    }

    public Predicate<Video> build() {
        return videoPredicate;
    }

    public List<Video> search(List<Video> videos) {
        return videos.stream().filter(videoPredicate).toList();
    }
}

class View {
    String userId;
    String videoId;
    Date startTime;
    Date endTime;
}
class StatisticsService {
    Map<String, List<View>> videoViews; // videoId -> views
    Map<String, List<View>> userViews; // userId -> views

    public void getVideoStatistics(Video video) {
        // get video statistics
    }

    public List<View> getUserStatistics(User user) {
        // get user statistics
        return userViews.get(user.userId);
    }
}

class RecommendationService {
    StatisticsService statisticsService;
    VideoService videoService;

    CatalogService catalogService;
    Map<String, List<Video>> recommendedVideos;


    public List<Video> getRecommendedVideos(User user) {
        List<View> views = statisticsService.getUserStatistics(user); // recommend based on user views
        // get genre of videos with count
        Map<Genre, Long> genreCount = views.stream().map(view -> videoService.getVideo(view.videoId))
                .flatMap(video -> video.genres.stream())
                .collect(Collectors.groupingBy(genre -> genre, Collectors.counting()));
        // get top 3 genres
        List<Genre> top3Genres = genreCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        // get content type of videos with count
        Map<ContentType, Long> contentTypeCount = views.stream().map(view -> videoService.getVideo(view.videoId))
                .collect(Collectors.groupingBy(video -> video.contentType, Collectors.counting()));
        // get top 3 content types
        List<ContentType> top3ContentTypes = contentTypeCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();

        // get videos based on top 3 genres and top 3 content types
        List<Video> recommendedVideos = new ArrayList<>();
        for (Genre genre : top3Genres) {
            recommendedVideos.addAll(catalogService.getVideosByGenre(genre));
        }

        // get videos based on top 3 content types
        for (ContentType contentType : top3ContentTypes) {
            recommendedVideos.addAll(catalogService.getVideosByType(contentType));
        }

        return recommendedVideos;
    }
}








