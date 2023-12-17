package LLD;

import java.util.List;

/**
 Create short ( 15 secs) videos
 Share videos with the community/other users
 Search short videos
 Engage with videos — Like/comment/show reaction
 Celebrity Endorsements
 Localized Content
 link:
    https://app.diagrams.net/#G18TRXrWWC3qMVjfRbQsN0V-zfJndJNJUo
 * */
public class Tiktok {
}

class UserTiktok {
    String id;
    String user_name;
    String password;
    LocationMeta address;
    UserStatus status;
}

enum UserStatus{
    ACTIVE,
    INACTIVE,
    BANNED,
}

class LocationMeta{
    String HNo;
    String street;
    String city;
    String country;
    String pinCode;
}

class VideoMeta{
    String id;
    ContentCategory category;
    String url;
    UserTiktok owner;
    String title;
    int engageCnt;
}

enum ContentCategory{
    ROMANCE,
    COMEDY,
    SAD,
    LEARNING,
    TECH
}

class EngageMeta{
    String id;
    EngageType engageType;
    String v_id;
    UserTiktok user1;
    UserTiktok user2;
}

enum EngageType{
    LIKE,
    COMMENT;
}


abstract class UserService2{
    abstract void createUser(UserTiktok user);

    abstract void updateUser(UserTiktok user);

    abstract UserTiktok getUser(String userId);
}

abstract class FeedManagerService{
    abstract List<VideoMeta> getFeed(String userId);
    abstract void addToUserFeed(String userId, String vId);
}

abstract class ContentManagerService{
    abstract void uploadContent(VideoMeta videoMeta);
    abstract VideoMeta getVideo(String vId);
    abstract void comment(String userId, String vId, String comment);
    abstract void makeLike(String userId, String vId);
}

abstract class FollowerManageService{
    abstract void follow(String userId1, String userId2);
}