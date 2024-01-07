package systemDesign.LLD.parkingLot;

import systemDesign.LLD.utility.Payment;
import systemDesign.LLD.utility.PaymentType;
import systemDesign.LLD.utility.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 <a href="https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-a-parking-lot.md">...</a>

 functional requirement:
    - multiple floor
    - multiple entry and exit points
    - collect parking ticket from entry points
    - pay parking fee at exit points
    - accepted payment methods: cash, credit card
    - parking lot capacity
    - diff parking spots: compact, large, handicapped, motorcycle, etc.
    - vehicle types: car, truck, van, motorcycle, etc.
    - ParkingDisplayBoard
    - per hour parking fee model

 use-case diagram:
    Admin:
        - add/remove parking floor
        - add/remove parking spot
        - add/remove entry/exit points
        - add/remove parking display board
        - add/remove parking attendant
        - add/remove parking rate
        - add/remove payment strategies
        - add/remove vehicle type

    ParkingAttendant:
        - processTicket
        - processPayment
        - assignVehicleToSpot
        - takeCustomerPayment
        - processPayment
    User:
        - takeTicket
        - payForParking
        - leaveParking

 class-case diagram:
    user:
        - person
        - ParkingAttendant
        - Admin
    Vehicle
        - Car
        - Truck
        - Van
        - Motorcycle
    Floor
    ParkingSpot
        - CompactSpot
        - LargeSpot
        - MotorCycleSpot
    ParkingDisplayBoard
    ParkingRate
        - BikeParkingRate
        - CarParkingRate
        - TruckParkingRate
    ParkingTicket
    PaymentStrategy
        - CashPayment
        - CreditCardPayment
        - UPIPayment


 */
public class ParkingLot {
}


enum VehicleType {
    CAR, TRUCK, ELECTRIC, VAN, MOTORBIKE
}

class Vehicle{
    String licenseNumber;
    VehicleType type;
    int spotsNeeded;
}

class Bus extends Vehicle{
    public Bus(){
        spotsNeeded = 5;
        type = VehicleType.TRUCK;
    }
}

class Car extends Vehicle{
    public Car(){
        spotsNeeded = 2;
        type = VehicleType.CAR;
    }
}

class Motorcycle extends Vehicle{
    public Motorcycle(){
        spotsNeeded = 1;
        type = VehicleType.MOTORBIKE;
    }
}

class VehicleFactory{ // factory pattern
    public static Vehicle getVehicle(VehicleType type){
        return switch (type) {
            case CAR -> new Car();
            case TRUCK -> new Bus();
            case MOTORBIKE -> new Motorcycle();
            default -> null;
        };
    }
}

class ParkingFloor{
    int floorNumber;
    ParkingSpot[] spots;
    int totalParkingSpot;
    int parkingSpotPerRow;
}
enum ParkingSpotType {
    HANDICAPPED(1), COMPACT(2), LARGE(5), MOTORBIKE(1), ELECTRIC(5);
    int numberOfSpots;
    ParkingSpotType(int numberOfSpots){
        this.numberOfSpots = numberOfSpots;
    }
}

class ParkingSpot{
    int number;
    boolean free;
    String vehicleNumber;
    ParkingSpotType type;
    int floorNumber;
}

class CompactSpot extends ParkingSpot{
    public CompactSpot(){
        super();
        type = ParkingSpotType.COMPACT;
    }
}

class LargeSpot extends ParkingSpot{
    public LargeSpot(){
        super();
        type = ParkingSpotType.LARGE;
    }
}

class MotorCycleSpot extends ParkingSpot{
    public MotorCycleSpot(){
        super();
        type = ParkingSpotType.MOTORBIKE;
    }
}

class ParkingSpotFactory{ // factory pattern
    public static ParkingSpot getParkingSpot(ParkingSpotType type){
        return switch (type) {
            case COMPACT -> new CompactSpot();
            case LARGE -> new LargeSpot();
            case MOTORBIKE -> new MotorCycleSpot();
            default -> null;
        };
    }
}

class ParkingDisplayBoard{
    int floorNumber;
    int freeSpotCountCompact;
    int freeSpotCountLarge;
    int freeSpotCountMotorcycle;
}

enum ParkingFeeStrategyType{
    HOURLY, DAILY, MONTHLY
}
interface ParkingRateStrategy{ // strategy pattern
    public double calculateParkingFee(VehicleType vehicleType);
}
class HourlyParkingRateStrategy implements ParkingRateStrategy{
    @Override
    public double calculateParkingFee(VehicleType vehicleType){
        return 0.0;
    }
}

class DailyParkingRateStrategy implements ParkingRateStrategy{
    public double calculateParkingFee(VehicleType vehicleType){
        return 0.0;
    }
}


class ParkingRateStrategyFactory{  // factory pattern
    public static ParkingRateStrategy getParkingRateStrategy(ParkingFeeStrategyType type){
        return switch (type) {
            case HOURLY -> new HourlyParkingRateStrategy();
            case DAILY -> new DailyParkingRateStrategy();
            default -> null;
        };
    }
}

interface ParkingRate{
    Map<ParkingFeeStrategyType, Double> getParkingRate();
}

class BikeParkingRate implements ParkingRate{ // strategy pattern

    // per hour
    @Override
    public Map<ParkingFeeStrategyType, Double> getParkingRate(){
        return Map.of(ParkingFeeStrategyType.HOURLY, 10.0, ParkingFeeStrategyType.DAILY, 100.0);
    }
}

class CarParkingRate implements ParkingRate{
    // per hour
    @Override
    public Map<ParkingFeeStrategyType, Double> getParkingRate(){
        return Map.of(ParkingFeeStrategyType.HOURLY, 20.0, ParkingFeeStrategyType.DAILY, 200.0);
    }
}

class TruckParkingRate implements ParkingRate{
    // per hour
    @Override
    public Map<ParkingFeeStrategyType, Double> getParkingRate(){
        return Map.of(ParkingFeeStrategyType.HOURLY, 30.0, ParkingFeeStrategyType.DAILY, 300.0);
    }
}

class ParkingRateFactory{ // factory pattern
    public static ParkingRate getParkingRate(VehicleType type){
        return switch (type) {
            case CAR -> new CarParkingRate();
            case TRUCK -> new TruckParkingRate();
            case MOTORBIKE -> new BikeParkingRate();
            default -> null;
        };
    }
}

class ParkingTicket{
    int ticketNumber;
    int floorNumber;
    int spotNumber;
    Vehicle vehicle;
    long startTime;
    long endTime;
}

//  <-------- Service -------->
class UserService{
    public void addUser(User user){

    }
    public void removeUser(User user){

    }

    public void updateUser(User user){

    }

    public User getUser(String username){
        return null;
    }

    boolean isAdmin(User user){
        return false;
    }

}

class FloorService{
    int totalFloors;
    public void addFloor(ParkingFloor floor){
        // add floor to db
    }
    public void removeFloor(ParkingFloor floor){
        // remove floor from db
    }
    public void updateFloor(ParkingFloor floor){
        // update floor in db
    }
    public ParkingFloor getFloor(int floorNumber){
        // get floor from db
        return null;
    }
}

class ParkingSpotService{

    FloorService floorService;
    ParkingDisplayBoardService parkingDisplayBoardService;
    List<ParkingSpot> parkingSpots;
    public void addParkingSpot(ParkingSpot spot){

    }
    public void removeParkingSpot(ParkingSpot spot){

    }
    public void updateParkingSpot(ParkingSpot spot){

    }
    public ParkingSpot getParkingSpot(int floorNumber, int spotNumber){
        return null;
    }

    public boolean isSpotFree(int floorNumber, int spotNumber){
        return false;
    }

    public List<ParkingSpot> getParkingAvailableSpots(int floorNumber){
        return parkingSpots.stream().filter(spot -> spot.floorNumber == floorNumber && spot.free).toList();
    }
    public List<ParkingSpot> getSpotByFloorAvailableAndType(int floorNumber, ParkingSpotType type){
        return getParkingAvailableSpots(floorNumber).stream().filter(spot -> spot.type == type).toList();
    }

    public void assignVehicleToSpot(int floorNumber, int spotNumber, Vehicle vehicle){
        ParkingSpot spot = getParkingSpot(floorNumber, spotNumber);
        spot.free = false;
        spot.vehicleNumber = vehicle.licenseNumber;
    }

    public ParkingSpot assignVehicleToSpot(int floorNumber, Vehicle vehicle){
        VehicleType type = vehicle.type;
        ParkingSpotType spotType = switch (type) {
            case CAR -> ParkingSpotType.COMPACT;
            case TRUCK -> ParkingSpotType.LARGE;
            case MOTORBIKE -> ParkingSpotType.MOTORBIKE;
            default -> null;
        };
        List<ParkingSpot> availableSpots = getSpotByFloorAvailableAndType(floorNumber, spotType);
        if (!availableSpots.isEmpty()){
            ParkingSpot parkingSpot = availableSpots.get(0);
            assignVehicleToSpot(floorNumber, parkingSpot.number, vehicle);

            // update parking display board
            ParkingDisplayBoard board = parkingDisplayBoardService.getParkingDisplayBoard(floorNumber);
            switch (spotType) {
                case COMPACT -> board.freeSpotCountCompact--;
                case LARGE -> board.freeSpotCountLarge--;
                case MOTORBIKE -> board.freeSpotCountMotorcycle--;
            }
            parkingDisplayBoardService.updateParkingDisplayBoard(board);
            return availableSpots.get(0);
        }else {
            throw new RuntimeException("No spot available on floor "+floorNumber+" for vehicle type "+type);
        }
    }

    public void freeSpot(int floorNumber, int spotNumber){
        ParkingSpot spot = getParkingSpot(floorNumber, spotNumber);
        spot.free = true;
        spot.vehicleNumber = null;
    }
}

class ParkingDisplayBoardService{
    private Map<Integer, ParkingDisplayBoard> parkingDisplayBoardMap;

    public void addParkingDisplayBoard(ParkingDisplayBoard board){

    }
    public void removeParkingDisplayBoard(ParkingDisplayBoard board){

    }
    public void updateParkingDisplayBoard(ParkingDisplayBoard board){

    }
    public ParkingDisplayBoard getParkingDisplayBoard(int floorNumber){
        return null;
    }
}

class ParkingRateService{
    public void addParkingRate(ParkingRate rate){
    }
    public void removeParkingRate(ParkingRate rate){
    }
    public void updateParkingRate(ParkingRate rate){
    }
    public ParkingRate getParkingRate(VehicleType type){
        return ParkingRateFactory.getParkingRate(type);
    }
}


class ParkingTicketService{
    ParkingSpotService parkingSpotService;
    FloorService floorService;

    systemDesign.LLD.utility.PaymentService paymentService;
    public void addParkingTicket(ParkingTicket ticket){
    }
    public void removeParkingTicket(ParkingTicket ticket){

    }
    public void updateParkingTicket(ParkingTicket ticket){

    }
    public ParkingTicket getParkingTicket(int ticketNumber){
        return null;
    }

    public ParkingTicket generateParkingTicket(Vehicle vehicle){
        ParkingTicket ticket = new ParkingTicket();
        ticket.vehicle = vehicle;
        ticket.startTime = System.currentTimeMillis();
        ticket.ticketNumber = 1;
        // assign spot
        for (int i = 1; i <= floorService.totalFloors; i++) {
            try {
                ticket.floorNumber = i;
                ticket.spotNumber =  parkingSpotService.assignVehicleToSpot(i, vehicle).number;
                break;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return ticket;
    }

    public void updateParkingTicketRelease(ParkingTicket ticket, PaymentType paymentType){
        ticket.endTime = System.currentTimeMillis();
        // update parking spot
        parkingSpotService.freeSpot(ticket.floorNumber, ticket.spotNumber);

        // update parking display board
        ParkingDisplayBoard board = parkingSpotService.parkingDisplayBoardService.getParkingDisplayBoard(ticket.floorNumber);
        VehicleType type = ticket.vehicle.type;
        ParkingSpotType spotType = switch (type) {
            case CAR -> ParkingSpotType.COMPACT;
            case TRUCK -> ParkingSpotType.LARGE;
            case MOTORBIKE -> ParkingSpotType.MOTORBIKE;
            default -> null;
        };
        switch (Objects.requireNonNull(spotType)) {
            case COMPACT -> board.freeSpotCountCompact++;
            case LARGE -> board.freeSpotCountLarge++;
            case MOTORBIKE -> board.freeSpotCountMotorcycle++;
        }
        parkingSpotService.parkingDisplayBoardService.updateParkingDisplayBoard(board);

        // update payment
        paymentService.processPayment(paymentType, Payment.builder().build());
    }

}