package code.DSimpl;

import lombok.Data;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * design a reddit like system which offer following operations
 * 1. create user
 * 2. show trending and subscribed feed
 * 3. create post
 * 4. create comment
 * 5. upvote/downvote post
 */
public interface Reddit {
    void createUser(RedditUser user);
    void userFeed(int userId);
    void showTrendingFeed();
    void showSubscribedFeed(int userId);
    void createPost(Post post);
    void createComment(Comment comment);
}


@Data
class RedditUser{
    int id;
    String name;

    List<Post> feed;
}

@Data
class Post{
    int id;
    String title;
    String description;
    int userId;

    long timestamp;

    long upvotes;

    long downvotes;

    List<Integer> comments;
}

class Comment{
    int id;
    String description;
    int userId;
    int postId;
}

abstract class UserService{
    static void addUser(RedditUser user){}
    static void removeUser(int userId){}

    static RedditUser getUser(int userId){
        return null;
    }
}

 class PostService{
    static void addPost(Post post){}
    static void deletePost(int postId){}

    static public void upvotePost(int postId) {
    }

    static public void downvotePost(int postId) {

    }
}

class SubscribedUserService{
    static Map<Integer, List<Integer>> subscribedUsers;
    static void addSubscribedUser(int userId, int subscribedUserId){
        subscribedUsers.get(userId).add(subscribedUserId);
    }
    static void removeSubscribedUser(int userId, int subscribedUserId){
        subscribedUsers.get(userId).remove(subscribedUserId);
    }

    static List<Integer> getSubscribedUsers(int userId){
        return subscribedUsers.get(userId);
    }
}

class feedService{
    static List<Post> getTrandingFeed(){
        return null;
    }
    static List<Post> getSubscribedFeed(int userId){
        return null;
    }

    static void addPostToFeed(int userId, Post post){
        UserService.getUser(userId).getFeed().add(post);
    }
}
class RedditImpl implements Reddit{
    List<RedditUser> users;
    List<Post> posts;
    List<Comment> comments;
    Map<Integer, List<Integer>> subscribedUsers;

    @Override
    public void createUser(RedditUser user) {
        // addUser(user);
    }

    @Override
    public void showTrendingFeed() {
        List<Post> posts = feedService.getTrandingFeed();
        posts = posts.stream().sorted(Comparator.comparing(Post::getUpvotes))
                .limit(10)
                .collect(Collectors.toList());
        for (RedditUser user: users){
            user.setFeed(posts);
        }
    }

    @Override
    public void showSubscribedFeed(int userId) {
        List<Integer> subscribedUsers = SubscribedUserService.getSubscribedUsers(userId);
        for (Integer subscribedUser : subscribedUsers){
            List<Post> posts = feedService.getSubscribedFeed(subscribedUser);
            posts = posts.stream().sorted(Comparator.comparing(Post::getTimestamp))
                    .filter(post -> post.timestamp >= System.currentTimeMillis() - 24*60*60*1000)
                    .limit(10)
                    .collect(Collectors.toList());
            UserService.getUser(userId).setFeed(posts);
        }
    }

    @Override
    public void createPost(Post post) {
        posts.add(post);
    }

    @Override
    public void createComment(Comment comment) {
        comments.add(comment);
        posts.get(comment.postId).getComments().add(comment.id);
    }

    @Override
    public void userFeed(int userId) {
        List<Post> posts = UserService.getUser(userId).getFeed();
        posts.stream().forEach(post -> System.out.println(post.getTitle()));
    }
}


