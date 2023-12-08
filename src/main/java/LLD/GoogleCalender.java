package LLD;

import java.time.LocalDateTime;
import java.util.*;

/**
 HLD: https://app.diagrams.net/#G12MUnOI_3lpCZ7k0U9xGVIW_eR1VBMpO3

 Design a calendar Application (similar on lines with Google's Calendar)
 It should support the following functionalities:

 1. Ability to create, update, delete an Event
     a. An event would typically consist of {start, end, location, Owner, user-list, title}.
     b. Events can either be like meetings(with a dedicated location and appropriate guest-list) or as well be like holidays, birthdays, reminders etc.
     c. An event once created, can be either accepted or rejected by the constituent users - if neither it should be in neutral state.
 2. Get Calendar for a user Ui
 3. Get Event details.
 4. For a given set of users[U1, U2,....Un] identity a common free slot of time.
 Expectations

 Code should a demo able, either by using a main driver program or test cases.
 Create the sample data yourself. you can put it into a file, test case or main driver program itself.
 Avoid writing monolithic code.
 Code should be readable, modular, testable, extensible with proper naming conventions. It should be easy to add/remove functionality without rewriting entire codebase.
 Code should handle edge cases properly and fail gracefully.
 Don't use any external data store, all the data should be loaded in application itself.
 Don't spend a lot of time in parsing the input.
 * */
public class GoogleCalender {

    static class  User{
        String id;
        String email;
        String name;
    }
}

abstract class Event{
    String id;
    String title;
    GoogleCalender.User owner;
}

class Meeting extends Event{
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<GoogleCalender.User> guests;
    Map<String, RsvpResponse> rsvpResponses;
}

class Holiday extends Event{
    boolean isFullDayEvent;
}

class Reminder extends Event{
    LocalDateTime remindTime;
    List<GoogleCalender.User> guests;
}

enum EventType{
    Meeting,
    Holiday,
    Reminder;
}

class EventRequest{
    String id;
    String title;
    GoogleCalender.User owner;
    LocalDateTime startTime;
    LocalDateTime endTime;
    List<GoogleCalender.User> guests;
    boolean isFullDayEvent;
    LocalDateTime remindTime;
}
class NotificationRequest{
    String id;
    String userId;
    String eventId;
    EventType eventType;
}

class EventFactory{
    Event buildEvent(EventType eventType, EventRequest eventRequest){
        return null;
    }
}

enum RsvpResponse{
    ACCEPTED,
    DECLINED,
    TENTATIVE;
}

class UserSchedule{
    String userId;
    String eventId;
    LocalDateTime startTime;
    LocalDateTime endTime;
}

class EventManagerService{
    // create/update/delete Event API
    public Event updateEvent(EventType eventType, EventRequest eventRequest){
        for (GoogleCalender.User guest: eventRequest.guests) {
            new ScheduleManagerService().isSlotAvail(guest.id, eventRequest.startTime, eventRequest.endTime);
        }
        Event e = new EventFactory().buildEvent(eventType, eventRequest);
        for (GoogleCalender.User guest: eventRequest.guests) {
            new ScheduleManagerService().updateUserCalender(guest.id, e.id, eventRequest.startTime, eventRequest.endTime);
            new NotificationManagerService().sendEventNotification(new NotificationRequest());
        }
        return e;
    }

    public Event getEvent(String eventId){
        // getEventById
        return null;
    }

    // update rsvp
    public Event rsvpResponse(String userId, String eventId, RsvpResponse rsvpResponse){
        Meeting e = (Meeting) getEvent(eventId);
        e.rsvpResponses.put(userId, rsvpResponse);
        return e;
    }
}

class ScheduleManagerService{
    public void updateUserCalender(String userId, String eventId, LocalDateTime startTime, LocalDateTime endTime){

    }

    public Optional<Event> isSlotAvail(String userId, LocalDateTime startTime, LocalDateTime endTime){
        // query to userSchedule mapping to check for given interval
        return Optional.ofNullable(null);
    }

}

class NotificationManagerService{
    public void sendEventNotification(NotificationRequest notificationRequest){
    }
}