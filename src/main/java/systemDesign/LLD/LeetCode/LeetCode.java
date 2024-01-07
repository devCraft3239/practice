package systemDesign.LLD.LeetCode;

import systemDesign.LLD.utility.User;

import java.util.List;
import java.util.Map;

/**
 User should be able to submit the solution of given problem.
 User can participate in any competition.
 Competition should start and end in given time frame.
 After end time, there will be no more submission.
 For each submission there will be set of test cases should execute in backend.
 There should be language restriction for each given question.
 After competition, user can see the leader board.
 Evolution of scores agains each submission.
 Practice session for user.
 Report generation, both real time and once competition is done.
 And Many More, Feel free to add more in comments.

  functional requirements:
     user signup/login
     problems  add/update/delete
     competition add/update/delete
     submission add/update/delete
     test cases add/update/delete
     leaderboard
        report generation
          practice session
            language restriction

    use case diagram:
    admin:
          add/update/delete problems
          add/update/delete competition
          add/update/delete test cases
          add/update/delete leaderboard
          add/update/delete report generation
          add/update/delete practice session
          add/update/delete language restriction
    user:
            signup/login
            add/update/delete submission
            participate competition
            Score test cases

    class diagram:
    User
    Problem
    Competition
    Submission
    TestCases
    LeaderBoard
    PracticeSession



 */
public class LeetCode {
}

enum Difficulty {
    EASY, MEDIUM, HARD
}

enum Language {
    JAVA, C, C_PLUS_PLUS, PYTHON
}

enum Topic {
    ARRAY, STRING, LINKED_LIST, TREE, GRAPH, DYNAMIC_PROGRAMMING, BACKTRACKING, DESIGN, BIT_MANIPULATION, MATH, GEOMETRY, RECURSION, SORTING, SEARCHING, HEAP, HASH_TABLE, STACK, QUEUE, SLIDING_WINDOW, GREEDY, BINARY_SEARCH, TWO_POINTERS, UNION_FIND, LINE_SWEEP, TOPOLOGICAL_SORT, BINARY_INDEXED_TREE, SEGMENT_TREE, BINARY_SEARCH_TREE, REJECTION_SAMPLING, RESERVOIR_SAMPLING, ORDER_STATISTICS, DIVIDE_AND_CONQUER, BRAIN_TEASER, MINIMAX, RECURSIVE, ITERATION, BREADTH_FIRST_SEARCH, DEPTH_FIRST_SEARCH}

class SampleExample{
    String input;
    String output;
    String description;
}
class Problem {
    int id;
    String title;
    String description;
    Difficulty difficulty;
    List<Language> languages;
    List<Topic> topics;
    List<SampleExample> sampleExamples;
    String hint;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

enum CompetitionStatus {
    NOT_STARTED, RUNNING, ENDED
}
class Competition {
    int id;
    String title;
    String description;
    List<String> participants;
    String startDate;
    String endDate;
    CompetitionStatus status;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

class CompetitionProblem {
    int id;
    int competitionId;
    int problemId;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

enum SubmissionStatus {
    SAVED,ACCEPTED, WRONG_ANSWER, RUNTIME_ERROR, TIME_LIMIT_EXCEEDED, COMPILATION_ERROR, MEMORY_LIMIT_EXCEEDED, SUBMITTED
}
class Submission {
    int id;
    int problemId;
    int userId;
    String code;
    String language;
    SubmissionStatus status;
    Map<TestCases, Boolean> testCasesResultMap;
    Double score;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

class TestCases {
    int id;
    int problemId;
    String input;
    String output;
    Double weightage;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

class ProblemTestCases {
    int id;
    int problemId;
    int testCaseId;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

class LeaderBoard {
    int id;
    int competitionId;
    Map<String, Double> userScoreMap;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}


class PracticeSession {
    int id;
    String title;
    String description;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

class PracticeSessionProblem {
    int id;
    int practiceSessionId;
    int problemId;
    String createdDate;
    String updatedDate;
    String createdBy;
    String updatedBy;
}

// <---- Requests ---->

// <---- Services ---->

class ProblemService {
    public void addProblem(Problem problem) {}
    public void updateProblem(Problem problem) {}
    public void deleteProblem(int problemId) {}

    public Problem getProblemById(int problemId) {return null;}

    public List<Problem> getAllProblems() {return null;}
    public List<Problem> getProblemsByDifficulty(Difficulty difficulty) {return null;}

    public List<Problem> getProblemsByTopic(Topic topic) {return null;}
    public List<Problem> getProblemsByDifficultyAndTopic(Difficulty difficulty, Topic topic) {return null;}
    // we can make use of dynamic predicate builder to build query based on input
}

class CompetitionService {
    public void addCompetition(Competition competition) {}
    public void updateCompetition(Competition competition) {}
    public void deleteCompetition(int competitionId) {}

    public Competition getCompetitionById(int competitionId) {return null;}

    public List<Competition> getAllCompetitions() {return null;}
    public List<Competition> getCompetitionsByDifficulty(Difficulty difficulty) {return null;}

    public List<Competition> getCompetitionsByTopic(Topic topic) {return null;}
    public List<Competition> getCompetitionsByDifficultyAndTopic(Difficulty difficulty, Topic topic) {return null;}

    public void addProblemToCompetition(int competitionId, int problemId) {}
    public void removeProblemFromCompetition(int competitionId, int problemId) {}
    public List<Problem> getProblemsByCompetitionId(int competitionId) {return null;}

    public void addParticipantToCompetition(int competitionId, int userId) {}

    public void removeParticipantFromCompetition(int competitionId, int userId) {}

    public List<User> getParticipantsByCompetitionId(int competitionId) {return null;}

    public void startCompetition(int competitionId) {}

    public void endCompetition(int competitionId) {}

    public void updateCompetitionStatus(int competitionId, CompetitionStatus status) {}

}

class SubmissionService {
    private ProblemService problemService;

    public Submission addSubmission(Submission submission) {return null;}
    public Submission updateSubmission(Submission submission) {return null;}
    public void deleteSubmission(int submissionId) {}
    public Submission addSubmissionToProblem(int problemId, int submissionId) {return null;}
    public Submission updateSubmissionStatus(int submissionId, SubmissionStatus status) {return null;}
    public double calculateScore(int submissionId) {return 0.0;};
}

class TestCasesService {
    public void addTestCases(TestCases testCases) {}
    public void updateTestCases(TestCases testCases) {}
    public void deleteTestCases(int testCasesId) {}

    public TestCases getTestCasesById(int testCasesId) {return null;}

    public List<TestCases> getAllTestCases() {return null;}
    public List<TestCases> getTestCasesByDifficulty(Difficulty difficulty) {return null;}

    public List<TestCases> getTestCasesByTopic(Topic topic) {return null;}
    public List<TestCases> getTestCasesByDifficultyAndTopic(Difficulty difficulty, Topic topic) {return null;}

    public void addTestCasesToProblem(int problemId, int testCasesId) {}
    public void removeTestCasesFromProblem(int problemId, int testCasesId) {}
    public List<Problem> getProblemsByTestCasesId(int testCasesId) {return null;}
}

class Service {
    private ProblemService problemService;
    private CompetitionService competitionService;
    private SubmissionService submissionService;
    private TestCasesService testCasesService;

    public void addProblem(Problem problem) {}
    public void updateProblem(Problem problem) {}
    public void deleteProblem(int problemId) {}

    public Problem getProblemById(int problemId) {return null;}

    public List<Problem> getAllProblems() {return null;}
    public List<Problem> getProblemsByDifficulty(Difficulty difficulty) {return null;}

    public List<Problem> getProblemsByTopic(Topic topic) {return null;}
    public List<Problem> getProblemsByDifficultyAndTopic(Difficulty difficulty, Topic topic) {return null;}

    public void addCompetition(Competition competition) {}
    public void updateCompetition(Competition competition) {}
    public void deleteCompetition(int competitionId) {}

    public Competition getCompetitionById(int competitionId) {return null;}

    public List<Competition> getAllCompetitions() {return null;}
    public List<Competition> getCompetitionsByDifficulty(Difficulty difficulty) {return null;}

    public List<Competition> getCompetitionsByTopic(Topic topic) {return null;}
    public List<Competition> getCompetitionsByDifficultyAndTopic(Difficulty difficulty, Topic topic) {return null;}

    public void addProblemToCompetition(int competitionId, int problemId) {}
    public void removeProblemFromCompetition(int competitionId, int problemId) {}
    public List<Problem> getProblemsByCompetitionId(int competitionId) {return null;}

    public void addParticipantToCompetition(int competitionId, int userId) {}

    public void removeParticipantFromCompetition(int competitionId, int userId) {}

    public List<User> getParticipantsByCompetitionId(int competitionId) {return null;}

    public void startCompetition(int competitionId) {}

    public void endCompetition(int competitionId) {}

    public void updateCompetitionStatus(int competitionId, CompetitionStatus status) {}

    public Submission addSubmission(Submission submission) {return null;}
    public Submission updateSubmission(Submission submission) {return null;}
    public void deleteSubmission(int submissionId) {}
    public Submission addSubmissionToProblem(int problemId, int submissionId) {return null;}
    public Submission updateSubmissionStatus(int submissionId, SubmissionStatus status) {return null;}
    public double calculateScore(int submissionId) {return 0.0;};
    public void addTestCases(TestCases testCases) {}

    public void updateTestCases(TestCases testCases) {}

    public void deleteTestCases(int testCasesId) {}
}







