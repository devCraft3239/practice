package LLD;

import java.util.List;

public class LogisticSystem {
    static class User{
        String id;
        String name;
        Location location;
    }
    List<User> users;
    List<Order> orders;
    List<Vehicle> vehicles;
}



class Order{
    String id;
    Item item;
    LogisticSystem.User sender;
    Location destination;
    OrderStatus currentStatus;
    VehicleLogistic vehicle;

    public double calculateCharge(){
        return item.weight * 10 + calculateDis(sender.location, destination) * 10;
    }

    public double calculateDis(Location l1, Location l2){
        return 0.0;
    }

    class OrderStatus{
        Location currentLocation;
        LogisticStatus logisticStatus;
    }

    class LogisticStatus{

    }
}

class Item{
    String id;
    double weight;
    double value;
}

class Location{
    double lat;
    double log;
}

abstract class VehicleLogistic{
    String regNumber;
    int capacity;
    abstract int getCapacity();
}

class Bike extends VehicleLogistic{

    @Override
    int getCapacity() {
        return 0;
    }
}

class Truck extends VehicleLogistic{

    @Override
    int getCapacity() {
        return 0;
    }
}

class AwbDetails{
    long awbNumber;
    // extra
}

abstract class OrderService{
    abstract boolean createOrder();
    abstract String processOrder(Order order);
    abstract boolean cancelOrder();
}

abstract class TrackingService{

    abstract  Order.OrderStatus trackOrderStatus(AwbDetails awbDetails);
    abstract void updateOrderStatus(AwbDetails awbDetails);
}