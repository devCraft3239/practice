package design.pattern.creational;

/**
 In Factory pattern, we create factory(interface) for object creation of similar type, hiding the creation logic complexity from client.
 we create object without exposing the creation logic to the client
 and provide a common interface/factory for creating object of same types.
 Imagine that you’re creating a Notification application.
 The first version of your app can only handle email notification, so the bulk of your code lives inside the EmailNotification class.
 After a while, your app becomes pretty popular. Each day you receive dozens of requests for SMS notification from user.
 intent:
    an interface for creating objects of a superclass but allows subclasses to decide the type of objects that will be created.

 examples:
 Consider an example of using multiple database servers like SQL Server, PostgresSQL and Oracle.
 Consider an example of bank operating diff a/c type like saving, current  and in future many more.
 * */

public class Factory {
}

enum Location {
    DEFAULT,
    USA,
    INDIA

}

abstract class Car{
    abstract void assemble();
    Location location;
}

class SedanCar extends Car{
    public SedanCar(Location location) {
        this.location =  location;
    }

    @Override
    public void assemble() {
        System.out.println("Driving Sedan Car");
    }
}

class MiniCar extends Car{
    public MiniCar(Location location) {
        this.location =  location;
    }

    @Override
    public void assemble() {
        System.out.println("Driving Mini Car");
    }
}

class LuxuryCar extends Car{
    public LuxuryCar(Location location) {
        this.location =  location;
    }

    @Override
    public void assemble() {
        System.out.println("Driving Luxury Car");
    }
}

enum CarType{
    MINI,
    SEDAN,
    LUXURY
}

class CarFactory{
    public static Car buildCar(CarType carType){
        Car car = null;
        switch (carType){
            case MINI:
                car = new MiniCar(Location.DEFAULT);
                break;
            case SEDAN:
                car = new SedanCar(Location.DEFAULT);
                break;
            case LUXURY:
                car = new LuxuryCar(Location.DEFAULT);
                break;
            default:
                System.out.println("Invalid Car Type");
        }
        return car;
    }
}
