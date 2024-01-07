package systemDesign.LLD.CarRental;

import com.sun.jdi.request.InvalidRequestStateException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import systemDesign.LLD.utility.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static systemDesign.LLD.CarRental.VehicleType.CAR;

/**
 functional requirements:
    1. rent different types of vehicles like motorcycles, cars, trucks, etc.
    2. unique barcode for each vehicle having all information about the vehicle
    3. customers can search and book vehicles
    4. customers can cancel their booking
    5. customers can pay for their booking
    6. customers can pick up their vehicle from one location and drop it off at a different location
    7. customers can specify the duration for which they want to book the vehicle
    8. collect late fees from customers if they drop off the vehicle after the due date
    9. send notifications to customers about their booking, payment, cancellation, due date reminders, etc.
    10.Provide an admin dashboard for the rental agency to add and manage cars, update rental rates, and view sales reports.

 use case diagram:
    Admin:
        1. add/update/delete vehicle
        2. add/update/delete vehicle type
        3. add/update/delete location (parking station)
        4. add/update/delete rental rate
        6. add/update/delete user
        8. add/update/delete payment type
        12.add/update/delete notification
        13.add/update/delete report
    User:
        1. search vehicle by searching parking station
        2. book vehicle
        3. cancel booking
        4. pick up vehicle
        5. drop off vehicle
        6. process payment
        7. get notification
        8. get report

    class diagram:
    vehicleType
    vehicle
        Cycle
        Motorcycle
        Car
    location
    rentalRate
        BikeRentalRate
        MotorcycleRentalRate
        CarRentalRate
    user
        Admin
        Customer
    BarCode
        - metadata
    Action
        - PickUp
        - DropOff
    payment
        CreditCard
        UPI
        Cash
    notification
        Email
        SMS


 */
public class CarRental {
}


enum VehicleType{
    CAR,
    MOTORCYCLE,
    BICYCLE
}

class Location{
    String id;
    String name;
    String address;
    String city;
    String state;
    String zipCode;
    String country;
}
enum VehicleStatus{
    AVAILABLE,
    RESERVED,
    LOST,
    NOT_SERVICEABLE,
    ON_RENT,
}

class Vehicle{
    String id;
    String barcode;
    VehicleStatus status;
    String parkingStationId;
}

class Cycle extends Vehicle{
}

class Motorcycle extends Vehicle{
}

class Car extends Vehicle{
}

class ParkingStation{
    String id;
    String name;
    Location location;
}

class RentalRate{
    String id;
    Map<Integer, Double> rentalRate;
}


class CycleRentalRate extends RentalRate{
}

class CarRentalRate extends RentalRate{
}

class BikeRentalRate extends RentalRate{
}

class RentalRateFactory{
    public static RentalRate getRentalRate(VehicleType vehicleType){
        return switch (vehicleType) {
            case CAR -> new CarRentalRate();
            case MOTORCYCLE -> new BikeRentalRate();
            case BICYCLE -> new CycleRentalRate();
            default -> null;
        };
    }
}

class VehicleFactory{
    public Vehicle getVehicle(VehicleType type){
        return switch (type) {
            case CAR -> new Car();
            case MOTORCYCLE -> new Motorcycle();
            case BICYCLE -> new Cycle();
            default -> null;
        };
    }
}

class BarCode{
    String id;
    String metadata;
}

class Action{
    String id;
    String name;
}

class PickUp extends Action{
}

class DropOff extends Action{
}

class VehicleService{

    private BookingService bookingService;

    public Vehicle getVehicle(String id){
        return null;
    }
    public Vehicle addVehicle(Vehicle vehicle){
        return null;
    }
    public Vehicle updateVehicle(Vehicle vehicle){
        return null;
    }
    public Vehicle deleteVehicle(String id){
        return null;
    }

    public VehicleStatus  getVehicleStatus(String vehicleId){
        return null;
    }

    public VehicleStatus  updateVehicleStatus(Vehicle vehicle, VehicleStatus status){
        return null;
    }

    public boolean isVehicleAvailable(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime){
        return vehicle.status ==  VehicleStatus.AVAILABLE &&
                !bookingService.getBooking(vehicle.id).startTime.isBefore(endTime);
    }
}

class LocationService{
    public Location getLocation(String id){
        return null;
    }
    public Location addLocation(Location location){
        return null;
    }
    public Location updateLocation(Location location){
        return null;
    }
    public Location deleteLocation(String id){
        return null;
    }
}


class RentalRateService{
    public RentalRate getRentalRate(String id){
        return null;
    }
    public RentalRate addRentalRate(RentalRate rentalRate){
        return null;
    }
    public RentalRate updateRentalRate(RentalRate rentalRate){
        return null;
    }
    public RentalRate deleteRentalRate(String id){
        return null;
    }

    public double calculateRentalAmount(RentalRate rentalRate, LocalDateTime startTime, LocalDateTime endTime){
        Duration duration = Duration.between(startTime, endTime);
        return rentalRate.rentalRate.get(duration.toHoursPart());
    }
}

class UserService{
    public User getUser(String id){
        return null;
    }
    public User addUser(User user){
        return null;
    }
    public User updateUser(User user){
        return null;
    }
    public User deleteUser(String id){
        return null;
    }
}
enum BookingStatus{
    CONFIRMED,
    CANCELLED,
    COMPLETED,
    PENDING
}

class Booking{
    String id;
    String userId;
    String vehicleId;
    String locationIdPickup;
    String locationIdDropOff;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String rentalRateId;
    String paymentId;
    String notificationId;
    BookingStatus status;
}

class BookingRequest{
    String userId;
    String vehicleId;
    String locationIdPickup;
    String locationIdDropOff;
    LocalDateTime startTime;
    LocalDateTime endTime;
    PaymentType paymentType;
}

enum BillingItem{
    RENTAL,
    LATE_FEE,
    DAMAGE_FEE,
    ADDITIONAL_SERVICE,
    TAX
}

class ParkingStationService{
    public ParkingStation getParkingStation(String id){
        return null;
    }
    public ParkingStation addParkingStation(ParkingStation parkingStation){
        return null;
    }
    public ParkingStation updateParkingStation(ParkingStation parkingStation){
        return null;
    }
    public ParkingStation deleteParkingStation(String id){
        return null;
    }

    public List<ParkingStation> getParkingStationByLocation(Location location){
        return null;
    }

    public List<Vehicle> getVehicles(String id){
        return null;
    }

    public List<Vehicle> addVehicles(String id, List<Vehicle> vehicles){
        return null;
    }

    public List<Vehicle> deleteVehicles(String id, List<Vehicle> vehicles){
        return null;
    }

    public List<Vehicle> updateVehicles(String id, List<Vehicle> vehicles){
        return null;
    }

    public List<Vehicle> getVehicleByType(String id, VehicleType type){
        if (type == CAR)
            return getVehicles(id).stream().filter(vehicle -> vehicle instanceof Car).toList();
        else if (type == VehicleType.MOTORCYCLE)
            return getVehicles(id).stream().filter(vehicle -> vehicle instanceof Motorcycle).toList();
        else if (type == VehicleType.BICYCLE)
            return getVehicles(id).stream().filter(vehicle -> vehicle instanceof Cycle).toList();
        return null;
    }
}

class CatalogService{

    private ParkingStationService parkingStationService;

    private VehicleService vehicleService;
    List<Vehicle> vehicles;

    public List<ParkingStation> searchParkingStation(Location location){
        return parkingStationService.getParkingStationByLocation(location);
    }

    public List<Vehicle> searchVehicle(VehicleType type, Location location, LocalDateTime startTime, LocalDateTime endTime){
        ParkingStation parkingStation = parkingStationService.getParkingStationByLocation(location).get(0);
        vehicles = parkingStationService.getVehicleByType(parkingStation.id, type);
        return vehicles.stream().filter(vehicle -> vehicleService.isVehicleAvailable(vehicle, startTime, endTime)).toList();
    }
}

class BookingService{

    private VehicleService vehicleService;

    private RentalRateService rentalRateService;

    private PaymentService paymentService;

    private NotificationService notificationService;
    public Booking getBooking(String id){
        return null;
    }

    public Booking saveBooking(Booking booking){
        return null;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking addBooking(BookingRequest bookingRequest){

        // check if vehicle is available
        Vehicle vehicle = vehicleService.getVehicle(bookingRequest.vehicleId);
        VehicleType vehicleType = vehicle instanceof Car ? VehicleType.CAR : vehicle instanceof Motorcycle ? VehicleType.MOTORCYCLE : VehicleType.BICYCLE;
        if(vehicle.status != VehicleStatus.AVAILABLE)
                throw new InvalidRequestStateException("Vehicle is not available");
        vehicleService.updateVehicleStatus(vehicle, VehicleStatus.RESERVED);

        // Calculate rental rate
        RentalRate rentalRate = RentalRateFactory.getRentalRate(vehicleType);
        double amount = rentalRateService.calculateRentalAmount(rentalRate, bookingRequest.startTime, bookingRequest.endTime);

        // check if payment is valid and make payment
        Payment payment = PaymentFactory.getPayment(bookingRequest.paymentType);
        payment.amount =  amount; payment.userId =  bookingRequest.userId;
        paymentService.processPayment(payment);

        // send notification
        Notification notifcation = NotificationFactory.getNotification(NotificationType.EMAIL);
        notifcation.message = "Booking confirmed";
        notificationService.sendNotification(NotificationType.PUSH, notifcation);

        Booking booking =  new Booking();
        booking.status = BookingStatus.CONFIRMED;
        booking.userId = bookingRequest.userId;
        booking.vehicleId = bookingRequest.vehicleId;
        booking.locationIdPickup = bookingRequest.locationIdPickup;
        booking.locationIdDropOff = bookingRequest.locationIdDropOff;
        booking.startTime = bookingRequest.startTime;
        booking.endTime = bookingRequest.endTime;
        booking.rentalRateId = rentalRate.id;
        booking.paymentId = payment.id;
        booking.notificationId = String.valueOf(notifcation.id);
        saveBooking(booking);
        return booking;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Booking updateBooking(BookingRequest bookingRequest, Booking booking){
        return null;
    }
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Booking cancelBooking(String id){
        Booking booking =  getBooking(id);
        // update booking status
        booking.status = BookingStatus.CANCELLED;
        saveBooking(booking);

        // update vehicle status
        Vehicle vehicle = vehicleService.getVehicle(booking.vehicleId);
        vehicleService.updateVehicleStatus(vehicle, VehicleStatus.AVAILABLE);

        // refund payment
        Payment payment = paymentService.getPayment(booking.paymentId);
        paymentService.refundPayment(payment);

        return booking;
    }
}

class ActionService{
    private BookingService bookingService;
    private ParkingStationService parkingStationService;

    private VehicleService vehicleService;

    public Action getAction(String id){
        return null;
    }
    public Action addAction(Action action){
        return null;
    }
    public Action updateAction(Action action){
        return null;
    }

    public Action deleteAction(String id){
        return null;
    }

    public void pickUpVehicle(String bookingId, String parkingStationId){
        Booking booking = bookingService.getBooking(bookingId);
        ParkingStation parkingStation = parkingStationService.getParkingStation(parkingStationId);
        // validate pick location
        if (!booking.locationIdPickup.equals(parkingStation.id))
            throw new InvalidRequestStateException("Invalid pick up location");

        // validate pick time
        if (booking.startTime.isAfter(LocalDateTime.now()))
                throw new InvalidRequestStateException("Invalid pick up location");

        vehicleService.updateVehicleStatus(vehicleService.getVehicle(booking.vehicleId), VehicleStatus.ON_RENT);

    }

    public void dropOffVehicle(String bookingId, String parkingStationId){
        Booking booking = bookingService.getBooking(bookingId);
        ParkingStation parkingStation = parkingStationService.getParkingStation(parkingStationId);
        // validate drop location
        if (!booking.locationIdDropOff.equals(parkingStationId))
            throw new InvalidRequestStateException("Invalid drop off location");
        // validate drop time
        if (booking.endTime.isAfter(LocalDateTime.now()))
            throw new InvalidRequestStateException("Invalid drop off time");
            // charge amount
        // check if vehicle is damaged
        vehicleService.updateVehicleStatus(vehicleService.getVehicle(booking.vehicleId), VehicleStatus.AVAILABLE);
    }
}
