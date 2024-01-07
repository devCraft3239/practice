package systemDesign.LLD.LogisticSystem.service;

import systemDesign.LLD.LogisticSystem.model.Order;
import systemDesign.LLD.LogisticSystem.model.Vehicle;

class VehicleService{
    public void addVehicle(Vehicle vehicle){

    }

    public void assignVehicle(Order order){
        order.setVehicle(new Vehicle());
    }
}