package LLD;

import java.time.LocalDateTime;
import java.util.List;

/**
 https://astikanand.github.io/techblogs/high-level-system-design/design-bookmyshow
 https://www.geeksforgeeks.org/design-bookmyshow-a-system-design-interview-question/
 Our ticket booking service should meet the following requirements:
 Functional Requirements:
 Our ticket booking service should be able to list down different cities where its affiliate cinemas are located.
 Once the user selects the city, the service should display the movies released in that particular city.
 Once the user selects the movie, the service should display the cinemas running that movie and its available shows.
 The user should be able to select the show at a particular cinema and book their tickets.
 The service should be able to show the user the seating arrangement of the cinema hall and the user should be able to select multiple seats according to their preference.
 The user should be able to distinguish available seats from the booked ones.
 Users should be able to put a hold on the seats for five minutes before they make a payment to finalize the booking.
 The user should be able to wait if there is a chance that seats might become available – e.g. when holds by other users expire.
 Waiting customers should be serviced fairly in a first come first serve manner.
 Non-Functional Requirements:
 The system would need to be highly concurrent. There will be multiple booking requests for the same seat at any particular point in time. The service should handle this gracefully and fairly.
 The core thing of the service is ticket booking which means financial transactions. This means that the system should be secure and the database ACID compliant.
 * */
public class BookMyShow {
    static class User{
        long id; // PK
        String name;
        String userName;
        String password;
        String mobile;
    }
}

class City{
    long id;//pk
    String name;
    String pinCode;
    String state;
}

class Cinema{
    long id; //pk
    long cityId; //fk
    String name;
    String address;
}

class Screen{
    long id;
    long cinemaId;
    int totalSeats;
}

class movie {
    long id;
    String name;
    String genre;
    LocalDateTime duration;
    MovieLanguage movieLanguage;
}

enum MovieLanguage{
    HINDI,
    ENGLISH,
    TELUGU
}

class Show{
    long id;
    long cinemaId;
    long screenId;
    long movieId;
    long cityId;
    long startTime;
}

class Booking{
    long id; //pk
    long userId; //fk
    long showId;
}

enum SeatType{
    PREMIUM,
    SILVER,
    GOLDEN;
}

class ScreenSeat{
    long id;
    int seatNumber;
    SeatType seatType;
    int screenId;
}

enum BookingStatus{
    AVAILABLE,
    NOT_AVAILABLE,
    BOOKED;
}

class ShowSeat{
    long id;
    BookingStatus status;
    int bookingId;
    int screenSeatId;
    int showId;
}

class BookingTicket{
    long id;
    List<ShowSeat> screenSeatId;
    double amount;
    LocalDateTime dateTime;
}
enum PaymentMode {
}

enum PaymentStatus {
}
class PaymentDetails{
    long id;
    double amount;
    long userId;
    PaymentMode  mode;
    PaymentStatus paymentStatus;
}


abstract class BrowseService{
    abstract List<City> getCities();
    abstract List<Long> getCinema(long cityId);
    abstract List<Long> getScreen(long cinemaId);

    abstract List<Show> getShowsByCity(long cityId);
    abstract List<Show> getShowsByCinema(long cinemaId);
    abstract List<Show> getShowsForScreen(long screenId);
    abstract List<Show> getShowsForScreen(long screenId, long startTime);
}

abstract class BookingService{
    abstract boolean bookTicket(long userId, long showId, List<ScreenSeat> seats);
    abstract boolean updateBookingStatus(long userId, long showId, List<ScreenSeat> seats);
}

abstract class PaymentService{
    abstract boolean makePayment(PaymentDetails paymentDetails);
    abstract List<PaymentDetails> paymentNotify(PaymentDetails paymentDetails);
}

abstract class SeatManageService{
    abstract boolean lockSeats(long showId, List<ScreenSeat> seats);
    abstract boolean unlockSeats(long showId, List<ScreenSeat> seats);
    abstract List<ShowSeat> checkAvail(long showId);
}

abstract class TicketingService{
    abstract Ticket generateTicket(long userId, List<ShowSeat> seats, PaymentDetails paymentDetails);
}


