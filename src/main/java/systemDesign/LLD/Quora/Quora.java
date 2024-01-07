package systemDesign.LLD.Quora;

import systemDesign.LLD.utility.NotificationService;
import systemDesign.LLD.utility.NotificationType;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**

 functional requirements:
    0. user registration and profile management
    1. user can post questions
    2. user can answer questions
    3. user can follow other users
    4. user can follow topics
    5. user can upvote/downvote answers
    9. user can search for questions
    10. user can search for topics

 classes:
    User
    Question
    Answer
    Topic
    Comment
    Vote
        - upvote
        - downvote
    Search
        - Prediction
    Notification
    Feed
    Follow
 */
public class Quora {
}

class User{
    String id;
    String name;
    String email;
    String password;
}

class Question{
    String id;
    String title;
    String description;
    User user;
    List<Answer> answers;
    List<Comment> comments;
    List<Topic> topics;
    List<Vote> votes;
}

class Answer{
    String id;
    String description;
    User user;
    List<Comment> comments;
    List<Vote> votes;
}

class Topic{
    String id;
    String name;
    List<Question> questions;
}

class Comment{
    String id;
    Question question;
    Answer answer;
    String description;
    User user;
    List<Vote> votes;

}

enum VoteType{
    UPVOTE,
    DOWNVOTE
}
class Vote{
    String id;
    User user;
}

class Upvote extends Vote{
    Answer answer;
    Question question;

    Comment comment;
}

class Downvote extends Vote{
    Answer answer;
    Question question;
    Comment comment;
}

class Feed{
    String id;
    String description;
    User user;
}

class Follow{
    String id;
    User user;
    List<User> followers;
    List<Topic> topics;
}

// <------- Services -------->


interface Observer{
    void update(String topicId, String newQuestion);
}
class TopicObserver implements Observer{

    NotificationService notificationService;
    String userId;
    TopicObserver(String userId){
        this.userId = userId;
    }
    @Override
    public void update(String topicId, String newQuestion) {
        notificationService.sendNotification(NotificationType.EMAIL, userId, "New question in topic " + topicId + ": " + newQuestion);
    }
}

interface Subject{
    void attach(String topic, Observer observer);
    void detach(String topic, Observer observer);
    void notify(String topic, String newQuestion);
}
class TopicSubject implements Subject{
    Map<String, List<Observer>> observers; // topicId -> List<Observer>
    @Override
    public void attach(String topic,Observer observer){
        observers.get(topic).add(observer);
    }

    @Override
    public void detach(String topic, Observer observer){
        observers.get(topic).remove(observer);
    }
    @Override
    public void notify(String topic, String newQuestion){
        for (Observer observer : observers.get(topic)) {
            observer.update(topic, newQuestion);
        }
    }
}



class QuestionService{
    FollowService followService;
    TopicSubject topicSubject;
    void saveQuestion(Question question){}

    public void askNewQuestion(String topic, String newQuestion) {
        System.out.println("New question in topic " + topic + ": " + newQuestion);
        topicSubject.notify(topic, newQuestion);
    }
    public Question createQuestion(Question question){
        saveQuestion(question);
        // get observer of topic
        for (Topic topic : question.topics) { // observer pattern
            askNewQuestion(topic.id, question.title);
        }

        // get followers of user
           List<User> userFollower = followService.getFollerers(question.user);
           List<User> followers = Stream.of(userFollower).flatMap(Collection::stream).toList();
            // create feed
            for (User follower : followers) {
                // save feed to timeline
                // send notification
            }
        return question;
    }
    public Question updateQuestion(Question question){
        return null;
    }
    public Question deleteQuestion(Question question){
        return null;
    }
    public Question getQuestion(String id){
        return null;
    }
    public List<Question> getQuestionsByUser(User user){
        return null;
    }
    public List<Question> getQuestionsByTopic(Topic topic){
        return null;
    }
    public List<Question> getQuestionsBySearch(String search){
        return null;
    }
}

class AnswerService{
    public Answer createAnswer(Answer answer){
        return null;
    }
    public Answer updateAnswer(Answer answer){
        return null;
    }
    public Answer deleteAnswer(Answer answer){
        return null;
    }
    public Answer getAnswer(String id){
        return null;
    }
    public List<Answer> getAnswersByUser(User user){
        return null;
    }
    public List<Answer> getAnswersByQuestion(Question question){
        return null;
    }
    public List<Answer> getAnswersBySearch(String search){
        return null;
    }
}

class TopicService{
    TopicSubject topicSubject;
    public Topic createTopic(Topic topic){
        return null;
    }
    public Topic updateTopic(Topic topic){
        return null;
    }
    public Topic deleteTopic(Topic topic){
        return null;
    }
    public Topic getTopic(String id){
        return null;
    }
    public List<Topic> getTopicsByUser(User user){
        return null;
    }
    public List<Topic> getTopicsBySearch(String search){
        return null;
    }

    void subscribeTopic(String topicId, String userId){
        // add userId to topic
        // add topic to userId
        topicSubject.attach(topicId, new TopicObserver(userId));
    }
}

class CommentService{
    public Comment createComment(Comment comment){
        return null;
    }
    public Comment updateComment(Comment comment){
        return null;
    }
    public Comment deleteComment(Comment comment){
        return null;
    }
    public Comment getComment(String id){
        return null;
    }
}

class VoteService{
    public Vote vote(Vote vote){
        //save vote
        if (vote instanceof Upvote){
            // add vote to answer
            // add vote to question
            // add vote to comment
        } else if (vote instanceof Downvote){
            // remove vote from answer
            // remove vote from question
            // remove vote from comment
        }
        return vote;
    }
}

class TimelineService{
    Map<String, Queue<Question>> timeline; // userId -> Question
    public void saveTimeline(String userId, Question question){
        timeline.get(userId).add(question);
    }
    public void deleteTimeline(String userId, Question question){
        timeline.get(userId).remove(question);
    }
}

class FollowService{
    public Follow follow(Follow follow){
        return null;
    }
    public Follow unFollow(Follow follow){
        return null;
    }
    public Follow getFollow(String id){
        return null;
    }
    public List<User> getFollerers(User user){
        return null;
    }
    public List<User> getFollerers(Topic topic){
        return null;
    }
}

class SearchService{ // elastic search with PredictionBuilder
    public List<Question> searchQuestion(String search){
        return null;
    }
    public List<Topic> searchTopic(String search){
        return null;
    }
    public List<User> searchUser(String search){
        return null;
    }
}







