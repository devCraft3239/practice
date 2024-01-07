package systemDesign.LLD.instagram;

import lombok.AllArgsConstructor;
import systemDesign.LLD.utility.Notification;
import systemDesign.LLD.utility.NotificationFactory;
import systemDesign.LLD.utility.NotificationService;
import systemDesign.LLD.utility.NotificationType;

import java.util.*;
import java.util.stream.Collectors;

/**
 User should be able to upload an image/video on his profile.
 User should be able to see uploads of other users followed the user.
 User should be able to follow other users.
User should be able to see the timeline of uploads of users followed the user.
User should be able to add/remove comments on images/videos.
User should be able to add/remove likes on images/videos.
User should be able to add/remove tags on images/videos.
User should be able to see the most trending tag.
User should be able to see the most trending image/video based on likes/comments/tags.

 functional requirements:
    add/update/remove a POST
    add/update/remove a COMMENT
    add/update/remove a LIKE
    add/update/remove a TAG
    follow a USER
    search for uploads by followed users
    user-timeline
    trending tags

    non-functional requirements:
    high availability
    low latency
    consistency

 use cases:
    upload a POST
    upload a COMMENT
    upload a LIKE
    upload a TAG
    follow a USER
    search for uploads by followed users
    user-timeline
    trending tags

 classes:
    User
    Post
        - Image
          - Video
            - Text
             - Link
    Comment
    Like
        PostLike
        CommentLike
    Tag
    Follow





 */
public class Instagram {
}

enum AccountStatus {
    ACTIVE,
    BLOCKED,
    BANNED,
    COMPROMISED,
    ARCHIVED,
    UNKNOWN
}

enum AccountType {
    PUBLIC,
    PRIVATE
}

class User {
    String id;
    String name;
    String email;
    AccountStatus accountStatus;

    AccountType accountType;
}

enum PostType {
    IMAGE,
    VIDEO,
    TEXT,
    LINK
}

enum PostStatus {
    ACTIVE,
    INACTIVE,
    DELETED,
    REJECTED
}
class Post {
    String id;
    String title;
    String userId;

    PostStatus status;
    List<Tag> tags;
    List<Comment> comments;
    List<Like> likes;
}

@AllArgsConstructor
class ImagePost extends Post {
    String imageUrl;
}

@AllArgsConstructor
class VideoPost extends Post {
    String videoUrl;
}

@AllArgsConstructor
class TextPost extends Post {
    String text;
}


@AllArgsConstructor
class LinkPost extends Post {
    String linkUrl;
}

class PostFactory {
    public static Post getPost(PostType postType) {
        if (postType == null) {
            return null;
        }
        if (postType == PostType.IMAGE) {
            return new ImagePost("");
        } else if (postType == PostType.VIDEO) {
            return new VideoPost("");
        } else if (postType == PostType.TEXT) {
            return new TextPost("");
        } else if (postType == PostType.LINK) {
            return new LinkPost("");
        }
        return null;
    }
}

@AllArgsConstructor
class Comment {
    String id;
    String postId;
    String userId;
    String text;
    List<Like> likes;
}

class Like {
    String id;
    String userId;
}

enum LikeType {
    POST,
    COMMENT
}

class PostLike extends Like {
    String postId;
}

class CommentLike extends Like {
    String commentId;
}

class LikeFactory {
    public static Like getLike(LikeType likeType) {
        if (likeType == null) {
            return null;
        }
        if (likeType == LikeType.POST) {
            return new PostLike();
        } else if (likeType == LikeType.COMMENT) {
            return new CommentLike();
        }
        return null;
    }
}

@AllArgsConstructor
class Tag {
    String id;
    String name;
    List<Post> posts;
}

enum Relationship {
    FOLLOW,
    UNFOLLOW
}

enum FollowRequestStatus {
    ACCEPTED,
    REJECTED,
}
@AllArgsConstructor
class FollowRequest {
    String id;
    String fromUserId;
    String toUserId;
}

// <---- Request ---->
class PostRequest {
    String id;
    String title;
    String userId;
    PostType postType;
    String imageUrl;
    String videoUrl;
    String text;
    String linkUrl;
}

// <---- Services ---->

class PostService {

    TimelineService timelineService;

    FollowService followService;

    TagService tagService;


    public Post savePost(Post post) {
        return null;
    }
    public Post getPost(String id) {
        return null;
    }
    public Post createPost(PostRequest postRequest) {
        Post post = PostFactory.getPost(postRequest.postType);
        post.userId = postRequest.userId;
        post.title = postRequest.title;
        savePost(post);

        // get followers
        List<User> followers = followService.getFollowers(post.userId);

        // add post to follower's timeline
        for (User follower : followers) {
            timelineService.addPostToTimeline(post, follower.id);
            // send notification to follower
        }

        // add post to tag's timeline
        for (Tag tag : post.tags) {
            tagService.addPostToTags(tag.id, post);
        }
        return post;
    }

    public boolean deletePost(String id) {
        Post post = getPost(id);
        post.status = PostStatus.DELETED;
        savePost(post);

        // get the followers
        List<User> followers = followService.getFollowers(post.userId);

        // delete post from user's timeline
        for (User follower : followers) {
            timelineService.removePostFromTimeline(post, follower.id);
            // send notification to follower
        }

        // delete post from tag's timeline
        for (Tag tag : post.tags) {
            tagService.deletePostFromTags(tag.id, post);
        }
        return true;
    }
}

class CommentService {
    PostService postService;
    NotificationService notificationService;

    public Comment saveComment(Comment comment) {
        return null;
    }
    public Comment addComment(Comment commentRequest) {
        Comment comment = new Comment(commentRequest.id, commentRequest.postId, commentRequest.userId, commentRequest.text, new ArrayList<>());
        saveComment(comment);

        // send notification to post owner
        Post post = postService.getPost(comment.postId);
        // add comment to post
        post.comments.add(comment);
        postService.savePost(post);

        // send notification to post owner
        Notification notification = NotificationFactory.getNotification(NotificationType.PUSH);
        notification.userId =  post.userId;
        notification.message = "commented on your post";
        notificationService.sendNotification(notification);
        return comment;
    }
    public Comment getComment(String id) {
        return null;
    }
    public boolean deleteComment(String id) {
        return true;
    }
}

class LikeService {
    PostService postService;
    CommentService commentService;
    public Like saveLike(Like like) {
        return null;
    }
    public Like getLike(String id) {
        return null;
    }
    public Like likePost(String postId, String userId) {
        Like like = LikeFactory.getLike(LikeType.POST);
        like.userId = userId;
        like.id = postId;
        saveLike(like);
        // add like to post
        Post post = postService.getPost(postId);
        post.likes.add(like);
        postService.savePost(post);

        // send notification to post owner
        return like;
    }

    public List likeComment(String commentId, String userId) {
        Like like = LikeFactory.getLike(LikeType.COMMENT);
        like.userId = userId;
        like.id = commentId;
        saveLike(like);
        // add like to comment
        Comment comment = commentService.getComment(commentId);
        comment.likes.add(like);
        commentService.saveComment(comment);

        // send notification to comment owner
        return comment.likes;
    }

    public boolean deleteLike(String id) {
        return true;
    }
}


class TagService {
    Map<String, List<Post>> tagMap; // tagId -> post

    public boolean addPostToTags(String tagId, Post post) {
        tagMap.getOrDefault(tagId, new ArrayList<>()).add(post);
        return true;
    }

    public boolean deletePostFromTags(String tagId, Post post) {
        tagMap.getOrDefault(tagId, new ArrayList<>()).remove(post);
        return true;
    }

    public List<Post> getPostsByTag(String tagId) {
        return tagMap.getOrDefault(tagId, new ArrayList<>());
    }

    public List<Tag> getTrendingTags() {
        // sort by post count
        return tagMap.entrySet().stream().sorted((e1, e2) -> e2.getValue().size() - e1.getValue().size()).limit(10)
                .map(e -> new Tag(e.getKey(), "", e.getValue())).collect(Collectors.toList());
    }
}


class FollowService {

    Map<String, List<String>> followerMap; // userId -> followerIds

    public FollowRequest saveRequest(FollowRequest followRequest) {
        return null;
    }
    public FollowRequest getFollowRequest(String id) {
        return null;
    }
    public FollowRequest createFollowRequest(FollowRequest followRequest1) {
        FollowRequest followRequest = new FollowRequest(followRequest1.id, followRequest1.fromUserId, followRequest1.toUserId);

        return followRequest;
    }
    public boolean deleteFollowRequest(String id) {
        return true;
    }

    public boolean followUser(String fromUserId, String toUserId) {
        followerMap.getOrDefault(toUserId, new ArrayList<>()).add(fromUserId);
        return true;
    }

    public boolean unfollowUser(String fromUserId, String toUserId) {
        followerMap.getOrDefault(toUserId, new ArrayList<>()).remove(fromUserId);
        return true;
    }

    public List<User> getFollowers(String userId) {
        return followerMap.getOrDefault(userId, new ArrayList<>()).stream().map(id -> new User()).collect(Collectors.toList());
    }

    public List<User> getFollowing(String userId) {
        return followerMap.entrySet().stream().filter(e -> e.getValue().contains(userId)).map(e -> new User()).collect(Collectors.toList());
    }
}


class TimelineService{ // singleton

    private static TimelineService instance;
    private TimelineService() {}
    public static TimelineService getInstance() {
        if (instance == null) {
            synchronized (TimelineService.class){
                if (instance == null) {
                    instance = new TimelineService();
                }
            }
        }
        return instance;
    }


    public static final int DEFAULT_TIMELINE_SIZE = 10;
    Map<String, Queue<Post>> timelineMap; // userId -> posts
    public List<Post> getPosts(String userId) {
        // get posts from user timeline
        return timelineMap.get(userId).stream().toList();
    }

    public void addPostToTimeline(Post post, String userId) {
        Queue queue =  timelineMap.get(userId);
        if(queue.size() == DEFAULT_TIMELINE_SIZE){
            queue.poll();
        }
        queue.add(post);
        timelineMap.put(userId, queue);
    }

    public void removePostFromTimeline(Post post, String userId) {
        Queue queue =  timelineMap.get(userId);
        queue.remove(post);
        timelineMap.put(userId, queue);
        // remove post from timeline
    }
}
