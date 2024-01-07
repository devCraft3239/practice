package systemDesign.LLD.LogisticSystem.model;

public class VehicleFactory {  //factory design pattern
    public static Vehicle getVehicle(VehicleType vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        if (vehicleType == VehicleType.BIKE) {
            return new Bike();
        } else if (vehicleType == VehicleType.TRUCK) {
            return new Truck();
        } else if (vehicleType == VehicleType.CAR) {
            return new Car();
        }
        return null;
    }
}
