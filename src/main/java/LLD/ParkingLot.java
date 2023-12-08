package LLD;

import java.time.LocalDateTime;
import java.util.List;

public class ParkingLot {
    List<ParkingFloor> parkingFloors;

    Ticket parkVehicle(Vehicle v){
        return null;
    }

    public void unPark(Ticket t){

    }
}

class Vehicle{
    String registrationNumber;
}

class ParkingFloor{
    int floor;
    List<ParkingSpot> spots;

    public void addSpot(ParkingSpot spot){
    }

    public void removeSpot(ParkingSpot spot){
    }
}

class ParkingSpot{
    int id;
    int floor;
    ParkStatus parkStatus;
}

enum ParkStatus{
    AVAILABLE,
    PARKED
}

class Ticket{
    Vehicle vehicle;
    ParkingSpot spot;
    LocalDateTime dateTime;
}

