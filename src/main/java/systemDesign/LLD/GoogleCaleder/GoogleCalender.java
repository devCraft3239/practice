package systemDesign.LLD.GoogleCaleder;

import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import systemDesign.LLD.utility.User;
import systemDesign.LLD.utility.UserService;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 HLD: <a href="https://app.diagrams.net/#G12MUnOI_3lpCZ7k0U9xGVIW_eR1VBMpO3">...</a>

 Design a calendar Application (similar on lines with Google's Calendar)
 It should support the following functionalities:

 1. Ability to create, update, delete an Event
    a. An event would typically consist of {start, end, location, Owner, user-list, title}.
    b. Events can either be like meetings(with a dedicated location and appropriate guest-list) or as well be like holidays, birthdays, reminders etc.
    c. An event once created, can be either accepted or rejected by the constituent users - if neither it should be in neutral state.
 2. Get Calendar for a user Ui
 3. Get Event details.
 4. For a given set of users[U1, U2,....Un] identity a common free slot of time.

    functional requirement:
    /events -> get/add/update/delete
    /users -> get/add/update/delete
    /calender -> get
    /freeSlots -> get free slots for a given set of users

    use-case diagram:
    user:
    1. add/update/delete events
    2. get calender
    3. check free slots for a given set of users
    4. respond to event invitation

    system:
     notify users about events
     reminder for events on scheduled time

    class diagram:
     User
    eventType
    eventStatus
    event
        Meeting
        Holiday
        Birthday
        Reminder
    Calender
    Slot
    Notification





 * */
public class GoogleCalender {
}

enum EventType {
    MEETING,
    HOLIDAY,
    BIRTHDAY,
    REMINDER
}
enum EventStatus{
    CREATED,
    CANCELED,
    REMOVED,
}

enum RsVpStatus {
    ACCEPTED,
    REJECTED,
    TENTATIVE,
    NEUTRAL
}

class Event {
    String id;
    String title;
    User owner;
    EventStatus eventStatus;
}

@AllArgsConstructor
class Meeting extends Event {
    List<User> users;
    String location;
    String description;

    LocalDateTime startTime;
    LocalDateTime endTime;

    Map<User,RsVpStatus> rsvp;
}

@AllArgsConstructor
class Holiday extends Event {
   boolean isFullDayEvent;
   LocalDateTime eventDate;
}

@AllArgsConstructor
class Birthday extends Event {
    boolean isFullDayEvent;
    LocalDateTime eventDate;
}

@AllArgsConstructor
class Reminder extends Event {
    LocalDateTime startTime;
    Integer repeatInterval;
    Integer repeatCount;
}

class EventFactory {
    public Event createEvent(EventType eventType, EventRequest eventRequest) {
        switch (eventType) {
            case MEETING:
                return new Meeting(eventRequest.users, eventRequest.location, eventRequest.description, eventRequest.startTime, eventRequest.endTime, eventRequest.users.stream().collect(Collectors.toMap(user -> user, user -> RsVpStatus.NEUTRAL)));
            case HOLIDAY:
                return new Holiday(true, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
            case BIRTHDAY:
                return new Birthday(true, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
            case REMINDER:
                return new Reminder(eventRequest.startTime, eventRequest.repeatInterval, eventRequest.repeatCount);
            default:
                return null;
        }
    }
}

class Calender {

    String userId;
    List<Event> events;
}


class Slot {
    String title;
    LocalDateTime startTime;
    LocalDateTime endTime;
    boolean isFree;
}

// <-- Request Dto -->
class EventRequest {
    String id;
    String title;
    User owner;

    String location;
    LocalDateTime startTime;

    LocalDateTime endTime;

    List<User> users;

    String description;

    Integer repeatInterval;

    Integer repeatCount;

    EventType eventType;

}

// <-- Services -->
class EventService {

    EventFactory eventFactory;

    CalenderService calenderService;

    public void saveEvent(Event event) {
        // save event
    }
    public Event getEvent(String id) {
        return null;
    }

    public Event createEvent(EventRequest eventRequest) {
        // create event
        Event event = eventFactory.createEvent(eventRequest.eventType, eventRequest);
        event.owner = eventRequest.owner;
        event.title = eventRequest.title;
        saveEvent(event);

        // add event to calendar of owner
        calenderService.addEventToCalender(event, event.owner.id);

        // notify users
        for (User user : eventRequest.users) {
            // add event to calender of user
            calenderService.addEventToCalender(event, user.id);
            // send notification
        }
        return event;
    }

    public Event updateEvent(EventRequest eventRequest) {
        Event event = getEvent(eventRequest.id);
        saveEvent(event);

        // notify owner
            // send notification
        // notify users
        if (event instanceof Meeting) {
            // notify users
            for (User user : ((Meeting) event).users) {
                // add event to calender of user
                // send notification
            }
        }
        return event;
    }

    public Event deleteEvent(String id) {
        Event event = getEvent(id);
        // update event status
        event.eventStatus = EventStatus.REMOVED;
        saveEvent(event);
        // notify owner
            // remove event from calender of owner
            // send notification
        if (event instanceof Meeting) {
            // notify users
            for (User user : ((Meeting) event).users) {
                // remove event from calender of user
                // send notification
            }
        }
        return event;
    }

    public List<Event> getEvents(String userId) {
        return null;
    }
}

class RsvpService{
    private EventService eventService;
    public void updateRsvp(String eventId, User user, RsVpStatus rsVpStatus) {
        // get event
        Event event = eventService.getEvent(eventId);
        // update rsvp status
        if (event instanceof Meeting) {
            ((Meeting) event).rsvp.put(user, rsVpStatus);
        }
        // notify owner
            // send notification
    }
}

enum CalenderInterval {
    DAY,
    WEEK,
    MONTH,
    YEAR
}

class CalenderService {

    Map<String, List<Event>> eventsMap;

    public void addEventToCalender(Event event, String userId) {
        // add event to calender
        List<Event> events = eventsMap.get(userId);
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
        eventsMap.put(event.owner.id, events);
    }

    public void removeEventFromCalender(Event event, String userId) {
        // remove event from calender
        List<Event> events = eventsMap.get(userId);
        if (events == null) {
            return;
        }
        events.remove(event);
        eventsMap.put(event.owner.id, events);
    }

    private List<Event> getEventsForStartTimeAndEndTime(List<Event> events, LocalDateTime startTime, LocalDateTime endTime) {
        List<Event> meetingEvents =  events.stream().filter(event -> event instanceof Meeting && ((Meeting) event).startTime.isAfter(startTime) && ((Meeting) event).endTime.isBefore(endTime)).toList();
        List<Event> reminderEvents =  events.stream().filter(event -> event instanceof Reminder && ((Reminder) event).startTime.isAfter(startTime) && ((Reminder) event).startTime.plusMinutes(10).isBefore(endTime)).toList();
        List<Event> birthdayEvents =  events.stream().filter(event -> event instanceof Birthday && ((Birthday) event).eventDate.isAfter(startTime) && ((Birthday) event).eventDate.plusDays(1).isBefore(endTime)).toList();
        List<Event> holidayEvents =  events.stream().filter(event -> event instanceof Holiday && ((Holiday) event).eventDate.isAfter(startTime) && ((Holiday) event).eventDate.plusDays(1).isBefore(endTime)).toList();
        return Stream.of(meetingEvents, reminderEvents, birthdayEvents, holidayEvents).flatMap(List::stream).toList();
    }

    public Calender getCalenderForStartTimeAndEndTime(User user, LocalDateTime startTime, LocalDateTime endTime){
        // get events for given date
        Calender calender = new Calender();
        calender.userId = user.id;
        List<Event> events = eventsMap.get(user.id);
        // check for startTime and endTime events only
        calender.events = getEventsForStartTimeAndEndTime(events, startTime, endTime);
        return calender;
    }
    public Calender getCalenderForDate(User user, LocalDateTime calenderDate) {
        // get start of the day
        LocalDateTime startTime = calenderDate.withHour(0).withMinute(0).withSecond(0).withNano(0);
        // get end of the day
        LocalDateTime endTime = calenderDate.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
        // get events for given date
        Calender calender = new Calender();
        calender.userId = user.id;
        List<Event>  events = eventsMap.get(user.id);
        // check for startTime and endTime events only
        calender.events = getEventsForStartTimeAndEndTime(events, startTime, endTime);
        return calender;
    }

    public Calender getCalenderForToday(User user) {
        Calender calender = new Calender();
        calender.userId = user.id;
        List<Event>  events = eventsMap.get(user.id);
        // check for today events only
        calender.events = getEventsForStartTimeAndEndTime(events, LocalDateTime.now(), LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999));
        return calender;
    }

    public Calender getCalenderForWeek(User user) {
        // get for current week
        return null;
    }

    public Calender getCalenderForMonth(User user) {
        // get events for current month
        return null;
    }

    public Calender getCalenderForYear(User user) {
        // get events for current year
        return null;
    }

    public Calender getCalenderForDuration(User user, CalenderInterval interval){
        switch (interval) {
            case DAY:
                return getCalenderForToday(user);
            case WEEK:
                return getCalenderForWeek(user);
            case MONTH:
                return getCalenderForMonth(user);
            case YEAR:
                return getCalenderForYear(user);
            default:
                return null;
        }
    }
}

class ReminderService {
    public void sendReminder(Event event) {
        // send reminder notification
    }
}

class SlotService {
    CalenderService calenderService;
    UserService userService;
    public List<Slot> getFreeSlots(List<String> users) {
        // check at 30 min interval for next 6 hours
        LocalDateTime startTime = LocalDateTime.now();
        // adjust start time to next start of hour
        startTime = startTime.plusMinutes(60 - startTime.getMinute());
        LocalDateTime endTime = startTime.plusHours(6);
        List<Slot> slots = new ArrayList<>();
        while (startTime.isBefore(endTime)) {
            boolean isFree = true;
            Slot slot = new Slot();
            slot.startTime = startTime;
            slot.endTime = startTime.plusMinutes(30);
            // check if slot is free for all users
            for (String user : users) {
                // get calender for user
                Calender calender = calenderService.getCalenderForStartTimeAndEndTime(userService.getUser(user), startTime, endTime);
                if (calender.events != null && calender.events.size() > 0) {
                    isFree = false;
                    break;
                }
            }
            if (isFree){
                slot.isFree = true;
                slots.add(slot);
            }
            startTime = startTime.plusMinutes(30);
        }
        return null;
    }

    public List<Slot> getSlotsInfoForDuration(String usersId, LocalDateTime startTime, LocalDateTime endTime) {
        // get calender for users
        Calender calender = calenderService.getCalenderForStartTimeAndEndTime(userService.getUser(usersId), startTime, endTime);
        // get slots for given duration
        return calender.events.stream().filter(event -> event instanceof Meeting).map(event -> {
            Slot slot = new Slot();
            slot.startTime = ((Meeting) event).startTime;
            slot.endTime = ((Meeting) event).endTime;
            slot.isFree = false;
            return slot;
        }).toList();
    }
}
