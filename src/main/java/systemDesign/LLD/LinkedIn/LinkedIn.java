package systemDesign.LLD.LinkedIn;

import lombok.Builder;
import lombok.Data;
import systemDesign.LLD.utility.NotificationService;
import systemDesign.LLD.utility.NotificationType;

import javax.xml.stream.Location;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 <a href="https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-linkedin.md">...</a>

 We will focus on the following set of requirements while designing LinkedIn:

 Each member should be able to add information about their basic profile, experiences, education, skills, and accomplishments.
 Any user of our system should be able to search for other members or companies by their name.
 Members should be able to send or accept connection requests from other members.
 Any member will be able to request a recommendation from other members. // optional
 The system should be able to show basic stats about a profile, like the number of profile views, the total number of connections, and the total number of search appearances of the profile.
 Members should be able to create new posts to share with their connections.
 Members should be able to add comments to posts, as well as like or share a post or comment.
 Any member should be able to send messages to other members.
 The system should send a notification to a member whenever there is a new message, connection invitation or a comment on their post.
 Members will be able to create a page for a Company and add job postings.
 Members should be able to create groups and join any group they like.
 Members should be able to follow other members or companies.

 functional requirement:
    /profile - add/update/delete  - basic profile, experiences, education, skills, and accomplishments
    /search - by name
    /connection - send/accept
    /recommendation - request
    /stats - views, connections, search appearances
     /post - create
        /comment - add
        /like
    /company - create
        /job - add
    /group - create/join
    /follow - member/company
    /notification - new message, connection invitation or a comment on their post

    use case diagram:
    member:
          add profile
          search
            - search member
            - search company
            - search job
          connection
          recommendation // optional
          stats
          post
                comment
                like
          company
                job
          group
          follow
    System:
          notification

 class level diagram:
    user
        - admin
        - member
        - system
    profile
          - basic
          - experiences
          - education
          - skills
          - accomplishments

    post
    Like
        - PostLike
        - CommentLike
    Comment
    companyPage
    JobBoard
    Job
    Group
    Follow
    Notification
 */
public class LinkedIn {
}

class BasicInfo {
    String name;
    String email;
    String phone;
    String address;
    String imageUrl;
}

class Experience {
    String title;
    String company;
    String location;
    String dateFrom;
    String dateTo;
    String description;
}

class Education {
    String school;
    String degree;
    String fieldOfStudy;
    String dateFrom;
    String dateTo;
    String description;
}

class Skill {
    String name;
    String level;
}

class Accomplishment {
    String name;
    String date;
    String description;
}

class Profile {
    BasicInfo basicInfo;
    List<Experience> experiences;
    List<Education> educations;
    List<Skill> skills;
    List<Accomplishment> accomplishments;
}

@Data
class Post {
    String id;
    String userId;
    String title;
    String description;
    String imageUrl;
    List<String> likes;
    List<Comment> comments;
    String date;
}

class Like {
    String id;
    String userId;
    String postId;
    String commentId;
    String date;
}

@Builder
class Comment {
    String id;
    String userId;
    String postId;
    String description;
    String date;
}

@Data
class CompanyPage {
    String id;
    String title;
    String description;
    String about;
    String imageUrl;
    List<Location> locations;

    // add constructor with CompanyBuilder
    CompanyPage(CompanyBuilder companyBuilder) {
        this.id = companyBuilder.id;
        this.title = companyBuilder.title;
        this.description = companyBuilder.description;
        this.about = companyBuilder.about;
        this.imageUrl = companyBuilder.imageUrl;
        this.locations = companyBuilder.locations;
    }

    // many more
}

class CompanyBuilder {
    String id;
    String title;
    String description;
    String about;
    String imageUrl;
    List<Location> locations;

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }

    CompanyBuilder addId(String id) {
        this.id = id;
        return this;
    }

    CompanyBuilder addTitle(String title) {
        this.title = title;
        return this;
    }

    CompanyBuilder addDescription(String description) {
        this.description = description;
        return this;
    }

    CompanyBuilder addAbout(String about) {
        this.about = about;
        return this;
    }

    CompanyBuilder addImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    CompanyBuilder addLocations(List<Location> locations) {
        this.locations = locations;
        return this;
    }

    CompanyPage build() {
        return new CompanyPage(this);
    }
}
@Data
class JobBoard {
    String id;
    String companyId;
    List<Job> jobs;
}

@Data
class Job {
    String id;
    String title; // elasticsearch index
    String description;
    String location;
    List<String> skills;
    String date;
}

class JobApplication {
    String id;
    String userId;
    String jobId;
    String date;
}

class Group {
    String id;
    String title;
    String description;
    String imageUrl;
    List<String> members;
}

@Builder
class Follow {
    String id;
    String userId;
    String companyId;
    String groupId;
    String date;
}

@Builder
class Connection {
    String id;
    String fromUserId;
    String toUserId;
    String date;
}

// <------ Request/Dto------>

enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED
}

@Builder
@Data
class ConnectionRequest {
    String id;
    String fromUserId;
    String toUserId;
    String date;
    RequestStatus requestStatus;
}

class CompanyPageRequest {
    String title;
    String description;
    String about;
    String imageUrl;
    List<Location> locations;
}

@Builder
class SearchResponse {
    List<Profile> profiles;
    List<CompanyPage> companyPages;
}

// <-------- Service -------->

class ProfileService {
    Profile getProfile(String userId) {
        return null;
    }

    Profile updateProfile(String userId, Profile profile) {
        return null;
    }

    Profile addExperience(String userId, Experience experience) {
        return null;
    }

    Profile addEducation(String userId, Education education) {
        return null;
    }

    Profile addSkill(String userId, Skill skill) {
        return null;
    }

    Profile addAccomplishment(String userId, Accomplishment accomplishment) {
        return null;
    }

    // delete methods
}

interface SearchStrategy<T> {
    List<T> search(String name);
}

class SearchByMember implements SearchStrategy<Profile> {
    List<Profile> users; // would be better to use search engine like elastic search
    @Override
    public List<Profile> search(String name) {
        return users.stream().filter(user -> user.basicInfo.name.contains(name)).toList();
    }
}

class SearchByCompany implements SearchStrategy<CompanyPage> {
    List<CompanyPage> companies; // would be better to use search engine like elastic search
    @Override
    public List<CompanyPage> search(String name) {
        return companies.stream().filter(company -> company.title.contains(name)).toList();
    }
}

class SearchByJob implements SearchStrategy<Job> {
    List<Job> jobs; // would be better to use search engine like elastic search
    @Override
    public List<Job> search(String name) {
        return jobs.stream().filter(job -> job.title.contains(name)).toList();
    }
}



class PostService {
    private ConnectionService connectionService;
    private NotificationService notificationService;
    private TimelineService timelineService;

    Post getPost(String postId) {
        return null;
    }


    Post createPost(Post post) {
        // save post to DB
        // get all followers of the user
        List<String> connections = connectionService.getConnections(post.userId);

        // add post to timeline  // aynsc
        for (String connection : connections) {
            // add post to timeline
            timelineService.addPostToTimeline(connection, post);
        }

        // send notification to all followers // async
        for (String connection : connections) {
            notificationService.sendNotification(NotificationType.PUSH, connection, "New post from " + post.userId);
        }
        return post;
    }

    Post updatePost(Post post) {
        return null;
    }

    Post deletePost(String userId, String postId) {
        // save post status to DB
        // get all followers of the user
        // remove from their timeline if exists
        return null;
    }
}

class LikeService {
    private PostService postService;
    private CommentService commentService;

    private NotificationService notificationService; // singleton bean
    Like likePost(String userId, String postId) {
        // create and save like to DB
        // add like to post
        Post post = postService.getPost(postId);
        post.getLikes().add(userId);
        postService.updatePost(post);

        // send notification to post owner
        notificationService.sendNotification(NotificationType.PUSH, post.getUserId(), "New like from " + userId);
        return null;
    }

    Like likeComment(String userId, String commentId) {
        // same as likePost
        return null;
    }

    Like unlikePost(String userId, String postId) {
        return null;
    }

    Like unlikeComment(String userId, String commentId) {
        return null;
    }
}

class CommentService {
    private PostService postService;
    private NotificationService notificationService; // singleton bean
    Comment addComment(String userId, String postId, String comment) {
        Comment comment1 = Comment.builder().userId(userId).postId(postId).description(comment).build();
        // save comment to DB

        // add comment to post
        Post post = postService.getPost(postId);
        post.getComments().add(comment1);
        postService.updatePost(post);


        // send notification to post owner
        notificationService.sendNotification(NotificationType.PUSH, post.getUserId(), "New comment from " + userId);
        return comment1;
    }

    Comment updateComment(String userId, String commentId, Comment comment) {
        return null;
    }

    Comment deleteComment(String userId, String commentId) {
        return null;
    }
}

class CompanyPageService {

    CompanyPage getCompanyPage(String companyId) {
        return null;
    }
    CompanyPage createCompanyPage(String userId, CompanyPageRequest companyPageRequest) {
        CompanyPage companyPage = CompanyBuilder.builder()
                .addTitle(companyPageRequest.title)
                .addDescription(companyPageRequest.description)
                .addAbout(companyPageRequest.about)
                .addImageUrl(companyPageRequest.imageUrl)
                .addLocations(companyPageRequest.locations)
                .build();
        // save company page to DB
        return companyPage;
    }

    CompanyPage updateCompanyPage(String userId, CompanyPage companyPage) {
        return null;
    }

    CompanyPage deleteCompanyPage(String userId, String companyId) {
        return null;
    }
}

class JobBoardService {
    private Map<String, String> jobBoardMap; // companyId, jobBoardId
    private CompanyPageService companyPageService;

    private Map<String, List<String>> jobApplicantMap; // jobId, list of userIds

    JobBoard getJobBoard(String jobBoardId) {
        return null;
    }
    JobBoard createJobBoard(JobBoard jobBoard) {
        // save job board to DB
        // add job board to jobBoardMap
        jobBoardMap.put(jobBoard.getCompanyId(), jobBoard.getId());

        return jobBoard;
    }

    JobBoard updateJobBoard(String userId, JobBoard jobBoard) {
        return null;
    }

    JobBoard deleteJobBoard(String userId, String jobBoardId) {
        return null;
    }

    void addJob(String jobBoardId, Job job) { // use observer pattern
        JobBoard jobBoard = getJobBoard(jobBoardId);
        jobBoard.getJobs().add(job);
        // save job board to DB
        // add job board to jobBoardMap
        jobBoardMap.put(jobBoard.getCompanyId(), jobBoard.getId());

        // get all followers of the company
        // send notification to all followers
    }

    void applyJob(String userId, String jobId) {
        // save to DB
        jobApplicantMap.get(jobId).add(userId);
        jobApplicantMap.put(jobId, jobApplicantMap.get(jobId));
        // send notification to company
    }
}

// subscribe to job posting
class JobObserver {
    void update(String companyId, Job job) {
        // send notification to all followers
    }

    void updateGroup(String groupId, String postId) {
        // send notification to all group members
    }
}

class FollowerJobObserver extends JobObserver {

    private String userId;

    FollowerJobObserver(String userId) {
        this.userId = userId;
    }
    private NotificationService notificationService; // singleton bean
    @Override
    void update(String companyId, Job job) {
        notificationService.sendNotification(NotificationType.PUSH, userId, "New job from " + companyId + " " + job.getTitle());
    }

    @Override
    void updateGroup(String groupId, String postId) {
        notificationService.sendNotification(NotificationType.PUSH, userId, "New post from " + groupId);
    }
}

class FollowerGroupObserver extends JobObserver {

    private String userId;

    FollowerGroupObserver(String userId) {
        this.userId = userId;
    }
    private NotificationService notificationService; // singleton bean

    @Override
    void updateGroup(String groupId, String postId) {
        notificationService.sendNotification(NotificationType.PUSH, userId, "New post from " + groupId);
    }
}

// publisher
class JobPosting{ // use observer pattern
    Map<String, List<JobObserver>> jobObserverMap; // companyId, list of observers
    Map<String, List<FollowerGroupObserver>> groupObserverMap; // groupId, list of observers
    void addObserver(String companyId, JobObserver jobObserver) {
        List<JobObserver> jobObservers = jobObserverMap.get(companyId);
        jobObservers.add(jobObserver);
        jobObserverMap.put(companyId, jobObservers);
    }

    void removeObserver(String companyId, JobObserver jobObserver) {
        List<JobObserver> jobObservers = jobObserverMap.get(companyId);
        jobObservers.remove(jobObserver);
        jobObserverMap.put(companyId, jobObservers);
    }

    void addNewJob(String companyId, Job job) {
        // save job to DB
        // notify all observers
        notify(companyId, job);
    }

    void notify(String companyId, Job job) {
        List<JobObserver> jobObservers = jobObserverMap.get(companyId);
        for (JobObserver jobObserver : jobObservers) {
            jobObserver.update(companyId, job);
        }
    }
}



class GroupService {
    Group createGroup(String userId, Group group) {
        return null;
    }

    Group updateGroup(String userId, Group group) {
        return null;
    }

    Group deleteGroup(String userId, String groupId) {
        return null;
    }

    Group getGroup(String groupId) {
        return null;
    }

    void addPost(String groupId, String postId) { // use observer pattern
        // use same concept as job posting observer pattern
    }
}


class FollowService {
    Follow followUser(String userId, String followUserId) {
        return null;
    }

    Follow followCompany(String userId, String companyId) {
        return null;
    }

    Follow followGroup(String userId, String groupId) {
        return null;
    }

    Follow unfollowUser(String userId, String followUserId) {
        return null;
    }

    Follow unfollowCompany(String userId, String companyId) {
        return null;
    }

    Follow unfollowGroup(String userId, String groupId) {
        return null;
    }

    List<String> getFollowersByUser(String userId) {
        return null;
    }

    List<String> getFollowersByCompany(String userId) {
        return null;
    }

    List<String> getFollowersByGroup(String userId) {
        return null;
    }
}


class ConnectionService {

    private Map<String, List<String>> connectionMap;

    ConnectionService(Map<String, List<String>> connectionMap) {
        this.connectionMap = connectionMap;
    }

    ConnectionRequest sendConnectionRequest(String fromUserId, String toUserId) {
        ConnectionRequest connectionRequest = ConnectionRequest.builder()
                .fromUserId(fromUserId).toUserId(toUserId).requestStatus(RequestStatus.PENDING).build();
        // save connection request to DB
        // notify toUserId
        return connectionRequest;
    }

    ConnectionRequest acceptConnectionRequest(String ConnectionRequestId) {
        // update connection request status to ACCEPTED
        ConnectionRequest connectionRequest = getConnectionRequest(ConnectionRequestId);
        connectionRequest.setRequestStatus(RequestStatus.ACCEPTED);
        // save connection request to DB


        // add connection to both users
        Connection connection = Connection.builder().fromUserId(connectionRequest.getFromUserId())
                .toUserId(connectionRequest.getToUserId()).build();
        Connection connection1 = Connection.builder().fromUserId(connectionRequest.getToUserId())
                .toUserId(connectionRequest.getFromUserId()).build();
        // save to DB
        connectionMap.get(connectionRequest.getFromUserId()).add(connectionRequest.getToUserId());

        // notify fromUserId
        return connectionRequest;
    }

    ConnectionRequest rejectConnectionRequest(String fromUserId, String toUserId) {
        return null;
    }

    ConnectionRequest getConnectionRequest(String requestId) {
        return null;
    }

    List<String> getPendingConnectionRequests(String userId) {
        return null;
    }

    List<String> getConnections(String userId) {
        return connectionMap.get(userId);
    }
}

class ActivityLog {
    String userId;
    String activity;
    String date;
}

enum ActivityType {
    SEARCH,
    CONNECTION_REQUEST,
    CONNECTION_ACCEPT,
    CONNECTION_REJECT
}

class ActivityLogService {
    void logActivity(String userId, String activity) {
    }
}

class TimelineService {
    Map<String, Queue<Post>> timelineMap;
    List<Post> getTimeline(String userId) {
        return timelineMap.get(userId) == null ? List.of() : timelineMap.get(userId).stream().toList();
    }

    void addPostToTimeline(String userId, Post post) {
        Queue<Post> timeline = timelineMap.get(userId);
        timeline.add(post);
        timelineMap.put(userId, timeline);
    }
}







