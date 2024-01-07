package systemDesign.LLD.Facebook;

import lombok.Data;
import systemDesign.LLD.utility.Notification;
import systemDesign.LLD.utility.NotificationFactory;
import systemDesign.LLD.utility.NotificationService;
import systemDesign.LLD.utility.NotificationType;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 KISS - Keep it simple and stupid
 Post - Text, image, video, link, etc
 Like, comment, share
 Add friends - non-directional relationship
 The system should display posts of friends to the display board/timeline.
 People can like/comment a post.
 Activity log - about likes, comments, and shares
 People can share their friends post to their own display board/timeline. [Optional]

 use case:
    user
        1. Create/update profile - name, email, DOB, etc
        2. Create post - text, image, video, link, etc
        3. Like, comment, share
        4. Add friends - non-directional relationship

    system:
        1. The system should display posts of friends to the display board/timeline.
        2. Activity log - about likes, comments, and shares
        3. Notification - about likes, comments, and shares

    class level diagram:
        1. User
         1.1. Person
         1.2. System
        2. Post
            2.1. TextPost
               2.2. ImagePost
                  2.3. VideoPost
                        2.4. LinkPost
        3. Action:
            3.1 Like
               3.2 Comment
                  3.3 Share
        6. Notification
            6.1. EmailNotification
               6.2. SMSNotification
                  6.3. PushNotification
        7. ActivityLog
            7.1. LikeActivity
               7.2. CommentActivity
                  7.3. ShareActivity
        8. Friend
            8.1. FriendRequest
               8.2. FriendList

 */
public class FaceBook {
}

enum PostType {
    TEXT,
    IMAGE,
    VIDEO,
    LINK
}

enum PostStatus {
    ACTIVE,
    INACTIVE
}

class POST {
    Integer id;
    PostType type;
    String userid;
    List<Comment> comments;
    List<Like> likes;
    List<Share> shares;

    PostStatus status;

    static class PostBuilder{
        Integer id;
        String content;
        PostType type;
        String userid;
        List<Comment> comments;
        List<Like> likes;
        List<Share> shares;
        static PostBuilder builder(){
            return new PostBuilder();
        }
        PostBuilder id(Integer id){
            this.id =  id;
            return this;
        }

        PostBuilder content(String content){
            this.content =  content;
            return this;
        }

        PostBuilder type(PostType type){
            this.type =  type;
            return this;
        }

        PostBuilder userid(String userid){
            this.userid =  userid;
            return this;
        }

        PostBuilder comments(List<Comment> comments){
            this.comments =  comments;
            return this;
        }

        PostBuilder likes(List<Like> likes){
            this.likes =  likes;
            return this;
        }

        PostBuilder shares(List<Share> shares){
            this.shares =  shares;
            return this;
        }

        POST build(){
            POST post = new POST();
            post.id = this.id;
            post.type = this.type;
            post.userid = this.userid;
            post.comments = this.comments;
            post.likes = this.likes;
            post.shares = this.shares;
            return post;
        }


    }
}

class TextPost extends POST {
    String text;
}

class ImagePost extends POST {
    String imageUrl;
}

class VideoPost extends POST {
    String videoUrl;
}

class LinkPost extends POST {
    String linkUrl;
}

class PostFactory {
    public static POST getPost(PostType postType) {
        if (postType == null) {
            return null;
        }
        if (postType == PostType.TEXT) {
            return new TextPost();
        } else if (postType == PostType.IMAGE) {
            return new ImagePost();
        } else if (postType == PostType.VIDEO) {
            return new VideoPost();
        } else if (postType == PostType.LINK) {
            return new LinkPost();
        }
        return null;
    }
}

class PostRequest {
    String userId;
    PostType postType;
    String text;
    String imageUrl;

    String videoUrl;

    String linkUrl;
}

class Comment {
    Integer id;
    String content;
    String userId;

    String postId;
}

@Data
class Like {
    Integer id;
    String userId;
    String postId;
}

class Share {
    Integer id;
    String userId;
    String postId;
}

// userService for user related operations

// postService for post related operations
class PostService{

    private FriendService friendService;

    private TimelineService timelineService;

    private ActivityLogService activityLogService;

    private NotificationService notificationService;

    public void savePost(POST post) {
        // save post
    }

    public POST getPost(Integer id) {
        return null;
    }

    public POST createPost(PostRequest postRequest) {
        POST post = PostFactory.getPost(postRequest.postType);
        if (post instanceof TextPost) {
            ((TextPost) post).text = postRequest.text;
        } else if (post instanceof ImagePost) {
            ((ImagePost) post).imageUrl = postRequest.imageUrl;
        } else if (post instanceof VideoPost) {
            ((VideoPost) post).videoUrl = postRequest.videoUrl;
        } else if (post instanceof LinkPost) {
            ((LinkPost) post).linkUrl = postRequest.linkUrl;
        }
        savePost(post);
        // add post to friends timeline
        List<String> friendIds = friendService.getFriendList(postRequest.userId);

        timelineService.addPostToTimeline(post, postRequest.userId);
        for (String friendId : friendIds) {
            // add post to friends timeline
            timelineService.addPostToTimeline(post, friendId);

            // add notification to friends notification
            Notification notification = NotificationFactory.getNotification(NotificationType.PUSH);
            notification.userId = friendId;
            notification.message = "New post from " + postRequest.userId;
            notificationService.createNotification(notification);
        }
        return post;
    }

    public POST updatePost(POST post) {
        return null;
    }

    public POST deletePost(Integer id) {
        POST post  = getPost(id);
        post.status = PostStatus.INACTIVE;
        savePost(post);

        // remove from friends timeline
        List<String> friendIds = friendService.getFriendList(post.userid);
        for (String friendId : friendIds) {
            // remove post from friends timeline
            timelineService.removePostFromTimeline(post, friendId);
        }
        return post;
    }
}

// actionService for like, comment, share related operations

enum ActivityType {
    LIKE,
    COMMENT,
    SHARE
}
class ActivityLog {
    Integer id;
    String userId;
    String postId;
}
class LikeActivity extends ActivityLog {
    String commentId;
}

class CommentActivity extends ActivityLog {

}

class ShareActivity extends ActivityLog {

}

class ActivityLogFactory {
    public static ActivityLog getActivityLog(ActivityType activityType) {
        if (activityType == null) {
            return null;
        }
        if (activityType == ActivityType.LIKE) {
            return new LikeActivity();
        } else if (activityType == ActivityType.COMMENT) {
            return new CommentActivity();
        } else if (activityType == ActivityType.SHARE) {
            return new ShareActivity();
        }
        return null;
    }
}

class LikeService{
    // Dao methods
    public Like addLike(Like like) {
        return null;
    }

    public Like getLike(Integer id) {
        return null;
    }

    public Like deleteLike(Integer id) {
        return null;
    }
}

class CommentService{
    public Comment addComment(Comment comment) {
        return null;
    }

    public Comment getComment(Integer id) {
        return null;
    }

    public Comment deleteComment(Integer id) {
        return null;
    }
}

class ShareService{
    public Share addShare(Share share) {
        return null;
    }

    public Share getShare(Integer id) {
        return null;
    }

    public Share deleteShare(Integer id) {
        return null;
    }
}


class ActivityLogService{
    private PostService postService;

    private LikeService likeService;
    private CommentService commentService;
    private ShareService shareService;

    private NotificationService notificationService;

    private FriendService friendService;

    public ActivityLog addActivityLog(ActivityLog activityLog, String userId) {
        // add activity log
        return null;
    }

    public ActivityLog likePost(String postId, String  userId) {
        // add activity logs for user profile
        ActivityLog activityLog = ActivityLogFactory.getActivityLog(ActivityType.LIKE);
        activityLog.postId = postId;
        activityLog.userId = userId;
        activityLog = addActivityLog(activityLog, userId);

        Like like = new Like();
        like.postId = postId;
        like.userId = userId;
        like = likeService.addLike(like);

        // add like to post
        POST post = postService.getPost(Integer.parseInt(postId));
        post.likes.add(like);
        postService.savePost(post);

        // notification to post owner
        Notification notification = NotificationFactory.getNotification(NotificationType.PUSH);
        notification.userId = post.userid;
        notification.message = "New like from " + userId;
        notificationService.createNotification(notification);

        return activityLog;
    }

    public ActivityLog commentPost(String postId, String  userId, String commentText) {
        ActivityLog activityLog = ActivityLogFactory.getActivityLog(ActivityType.COMMENT);
        activityLog.postId = postId;
        activityLog.userId = userId;
        activityLog = addActivityLog(activityLog, userId);

        Comment comment = new Comment();
        comment.postId = postId;
        comment.userId = userId;
        comment.content = commentText;
        comment = commentService.addComment(comment);

        // add comment to post
        POST post = postService.getPost(Integer.parseInt(postId));
        post.comments.add(comment);
        postService.savePost(post);

        // notification to post owner
        Notification notification = NotificationFactory.getNotification(NotificationType.PUSH);
        notification.userId = post.userid;
        notification.message = "New comment from " + userId;
        notificationService.createNotification(notification);
        return activityLog;
    }

    public ActivityLog sharePost(String postId, String  userId) {
        ActivityLog activityLog = ActivityLogFactory.getActivityLog(ActivityType.SHARE);
        activityLog.postId = postId;
        activityLog.userId = userId;
        activityLog = addActivityLog(activityLog, userId);

        Share share = new Share();
        share.postId = postId;
        share.userId = userId;
        share = shareService.addShare(share);

        // add share to post
        POST post = postService.getPost(Integer.parseInt(postId));
        post.shares.add(share);
        postService.savePost(post);

        // notification to post owner
        Notification notification = NotificationFactory.getNotification(NotificationType.PUSH);
        notification.userId = post.userid;
        notification.message = "Re-shared post from " + userId;
        notificationService.createNotification(notification);

        // notification to friends
        List<String> friendIds = friendService.getFriendList(userId);
        for (String friendId : friendIds) {
            // add notification to friends notification
            notification = NotificationFactory.getNotification(NotificationType.PUSH);
            notification.userId = friendId;
            notification.message = "New share from " + userId;
            notificationService.createNotification(notification);
        }
        return activityLog;
    }

}

// notificationService for notification related operations

// friendService for friend related operations
class FriendService{
    Map<String, List<String>> friendListMap;

    Map<String, String> friendRequestMap;
    public FriendRequest createFriendRequest(FriendRequest friendRequest) {
        friendRequestMap.put(friendRequest.fromUserId, friendRequest.toUserId);
        return friendRequest;
    }

    public boolean acceptFriendRequest(FriendRequest friendRequest) {
        friendListMap.get(friendRequest.fromUserId).add(friendRequest.toUserId);
        friendListMap.get(friendRequest.toUserId).add(friendRequest.fromUserId);
        friendRequestMap.remove(friendRequest.fromUserId);
        return true;
    }

    public FriendRequest rejectFriendRequest(FriendRequest friendRequest) {
        friendRequestMap.remove(friendRequest.fromUserId);
        return friendRequest;
    }
    public List<String> getFriendList(String userId) {
        return friendListMap.get(userId);
    }
}

class FriendRequest {
    Integer id;
    String fromUserId;
    String toUserId;
}

// timelineService for timeline related operations
class TimelineService{
    public static final int DEFAULT_TIMELINE_SIZE = 10;
    Map<String, Queue<POST>> timelineMap;
    public List<POST> getPosts(String userId) {
        // get posts from user timeline
        return timelineMap.get(userId).stream().toList();
    }

    public void addPostToTimeline(POST post, String userId) {
        Queue queue =  timelineMap.get(userId);
        if(queue.size() == DEFAULT_TIMELINE_SIZE){
            queue.poll();
        }
        queue.add(post);
        timelineMap.put(userId, queue);
    }

    public void removePostFromTimeline(POST post, String userId) {
        Queue queue =  timelineMap.get(userId);
        queue.remove(post);
        timelineMap.put(userId, queue);
        // remove post from timeline
    }
}