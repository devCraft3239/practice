package systemDesign.LLD.BookMyShow;

import lombok.Data;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systemDesign.LLD.utility.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 functional requirements:
    1. /cities - add/remove city
    2. /cinemas - add/remove cinema
    3. /movies/id -- add/remove/update movie
    4. /shows/id -- show seats
    5. /bookings
    6. /notifications
    7. /payments

    use-cases:
    admin:
        1. add/remove city
        2. add cinema
        3. add movie
        4. add show
        5. add seats
        6. add payment method
        7. add notification
    user:
        1. search movie
          2. search cinema
             3. search show
                4. book show
                   5. make payment

    class level design:
    1. City
    2. Cinema
        2.1 multiplex
        2.2 single screen
    3. Screen
    4. Movie
    5. Show
        - search by movie name, city name, cinema name, genre, language
    6. Seat
        6.1 Balcony
        6.2 Gold
        6.3 Silver
    6.a. showSeat
    7. Booking
    8. Payment
        8.1. CreditCard
        8.2. UPI
        8.3. Cash
    9. Notification
        9.1. Email - singleton
        9.2. SMS
        9.3. Push

    convert class level -> models : OOP + SOLID + Design Patterns:
    convert use-cases -> services -> controllers
 */

public class BookMyShow {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class City {
    String name;
    String state;
    String country;

    String pincode;
}

class Cinema {
    String name;
    String address;
    Integer cityId;
}

class Screen {
    Integer id;
    Integer cinemaId;
    Integer totalSeats;
}

@Data
class Movie {
    Integer id;
    String name;
    String language;
    String genre;
    String duration;
}

class Show {
    Integer id;
    Integer screenId;
    Integer movieId;

    Integer cityId; // denormalization

    Integer cinemaId; // denormalization

    String startTime;
    String endTime;

    String genre;

    String language;
}

enum SeatType {
    BALCONY,
    GOLD,
    SILVER
}

class Seat {
    Integer id;
    Integer screenId;
    SeatType type;
}

enum ShowSeatStatus {
    AVAILABLE,
    BOOKED,
    HOLD
}

class ShowSeat {
    Integer id;
    Integer showId;
    Integer seatId;
    ShowSeatStatus status;
}

enum BookingStatus {
    CONFIRMED,
    CANCELLED,
    PENDING
}
class Booking {
    Integer id;
    List<Integer> showSeatIds;
    Integer userId;
    Integer paymentId;
    PaymentType paymentType;

    BookingStatus status;
}

//  < ------- DTO -------->

// <------- Services -------->

class CityService {
    public City getCity(Integer id) {
        return null;
    }

    public City createCity(City city) {
        return null;
    }

    public City updateCity(City city) {
        return null;
    }

    public City deleteCity(Integer id) {
        return null;
    }
}

class CinemaService {
    public Cinema getCinema(Integer id) {
        return null;
    }

    public Cinema createCinema(Cinema cinema) {
        return null;
    }

    public Cinema updateCinema(Cinema cinema) {
        return null;
    }

    public Cinema deleteCinema(Integer id) {
        return null;
    }
}

class ScreenService {
    public Screen getScreen(Integer id) {
        return null;
    }

    public Screen createScreen(Screen screen) {
        return null;
    }

    public Screen updateScreen(Screen screen) {
        return null;
    }

    public Screen deleteScreen(Integer id) {
        return null;
    }
}

class MovieService {
    public Movie getMovie(Integer id) {
        return null;
    }

    public Movie createMovie(Movie movie) {
        return null;
    }

    public Movie updateMovie(Movie movie) {
        return null;
    }

    public Movie deleteMovie(Integer id) {
        return null;
    }

    public List<Movie> searchMovie(String name) {
        return null;
    }
}


class ShowService {
    List<Show> shows;

    // todo: can make use of pre-computed maps for faster search
    Map<String, List<Show>> showByMovieName;
    Map<String, List<Show>> showByCityName;
    Map<String, List<Show>> showByCinemaName;
    Map<String, List<Show>> showByGenre;
    Map<String, List<Show>> showByLanguage;

    public Show getShow(Integer id) {
        return null;
    }

    public Show createShow(Show show) {
        return null;
    }

    public Show updateShow(Show show) {
        return null;
    }

    public Show deleteShow(Integer id) {
        return null;
    }

    // search show by movie name
    // search show by city name
    // search show by cinema name
    // search show by genre
    // search show by language

    public List<Show> searchShow(String movieName, String cityName, String cinemaName, String genre, String language) {
        return shows.stream()
                .filter(SearchPredicateBuilder.builder()
                        .withMovieName(movieName)
                        .withCityName(cityName)
                        .withCinemaName(cinemaName)
                        .withGenre(genre)
                        .withLanguage(language)
                        .build())
                .collect(Collectors.toList());
    }
}

class SeatService {
    public Seat getSeat(Integer id) {
        return null;
    }

    public Seat createSeat(Seat seat) {
        return null;
    }

    public Seat updateSeat(Seat seat) {
        return null;
    }

    public Seat deleteSeat(Integer id) {
        return null;
    }
}

class ShowSeatService {
    public ShowSeat getShowSeat(Integer id) {
        return null;
    }

    public ShowSeat createShowSeat(ShowSeat showSeat) {
        return null;
    }

    public ShowSeat updateShowSeat(ShowSeat showSeat) {
        return null;
    }

    public ShowSeat deleteShowSeat(Integer id) {
        return null;
    }

    public List<ShowSeat> getShowSeats(Integer showId) {
        // return all show seats for a show
        return null;
    }
}

class BookingService {

    private ShowSeatService showSeatService;
    private PaymentService paymentService;

    private NotificationService notificationService;

    public Booking getBooking(int id) {
        return null;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE) // isolation level: serializable, repeatable read, read committed, read uncommitted
    public Booking createBooking(Booking booking) {
        // check if seats are available
        // book seats
        // make payment
        // send notification
        for (Integer showSeatId: booking.showSeatIds) {
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);
            synchronized (ShowSeat.class){
                if (showSeat.status.equals(ShowSeatStatus.BOOKED)) {
                    throw new RuntimeException("seat is already booked");
                }
                showSeat.status = ShowSeatStatus.BOOKED;
                showSeatService.updateShowSeat(showSeat);
            }
        }
        // make payment
        Payment payment = PaymentFactory.getPayment(booking.paymentType);
        paymentService.createPayment(payment);
        booking.status =  BookingStatus.CONFIRMED;

        // email notification
        notificationService.sendNotification(NotificationType.EMAIL, String.valueOf(booking.userId), "Booking confirmed");
        // todo: save booking to db
        return booking;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Booking cancelBooking(Integer id) {
        Booking booking = getBookingById(id);
        // cancel booking
        booking.status = BookingStatus.CANCELLED;
        // todo : save booking to db
        // release seats
        for (Integer showSeatId: booking.showSeatIds) {
            ShowSeat showSeat = showSeatService.getShowSeat(showSeatId);
            synchronized (ShowSeat.class){
                if (showSeat.status.equals(ShowSeatStatus.AVAILABLE)) {
                    throw new RuntimeException("seat is already available");
                }
                showSeat.status = ShowSeatStatus.AVAILABLE;
                showSeatService.updateShowSeat(showSeat);
            }
        }
        // todo: initiate refund via payment service
        paymentService.refundPayment(PaymentFactory.getPayment(booking.paymentType));
       // todo: send notification
        return booking;
    }

    public Booking getBookingById(Integer bookingId) {
        // todo: fetch bookings from db for a user
        return null;
    }

    public List<Booking> getBookingForUser(Integer userId) {
        // todo: fetch bookings from db for a user
        return null;
    }
}

class SearchPredicateBuilder{
    Predicate<Show> predicate;

    private MovieService movieService;

    private CityService cityService;

    private CinemaService cinemaService;

    private SearchPredicateBuilder() {
        predicate = show -> true;
    }

    public static SearchPredicateBuilder builder() {
        return new SearchPredicateBuilder();
    }

    public SearchPredicateBuilder withMovieName(String name) {
        if(name == null) return this;
        Integer movieId = movieService.searchMovie(name).stream()
                .sorted(Comparator.comparing(Movie::getId).reversed())
                .toList().get(0).id;
        predicate = predicate.and(show -> show.movieId.equals(movieId));
        return this;
    }

    public SearchPredicateBuilder withCityName(String name) {
        if(name == null) return this;
//        predicate = predicate.and(show -> show.city.name.equals(name)); predicate by city name
        return this;
    }

    public SearchPredicateBuilder withCinemaName(String name) {
        if(name == null) return this;
//        predicate = predicate.and(show -> show.cinema.name.equals(name)); // predicate by cinema name
        return this;
    }

    public SearchPredicateBuilder withGenre(String genre) {
        if(genre == null) return this;
        predicate = predicate.and(show -> show.genre.equals(genre));
        return this;
    }

    public SearchPredicateBuilder withLanguage(String language) {
        if(language == null) return this;
        predicate = predicate.and(show -> show.language.equals(language));
        return this;
    }

    public Predicate<Show> build() {
        return predicate;
    }
}


