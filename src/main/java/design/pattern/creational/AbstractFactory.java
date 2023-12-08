package design.pattern.creational;

/**
 provide one more layer of abstraction layer to factory design.
 Abstract Factory patterns work around a super-factory which creates other factories.
 This factory is also called as factory of factories.
 * */
public class AbstractFactory {}


class USAFactory{
    public static Car buildCar(CarType carType){
        Car car = null;
        switch (carType){
            case MINI:
                car = new MiniCar(Location.USA);
                break;
            case SEDAN:
                car = new SedanCar(Location.USA);
                break;
            case LUXURY:
                car = new LuxuryCar(Location.USA);
                break;
        }
        return car;
    }
}

class IndiaFactory{
    public static Car buildCar(CarType carType){
        Car car = null;
        switch (carType){
            case MINI:
                car = new MiniCar(Location.INDIA);
                break;
            case SEDAN:
                car = new SedanCar(Location.INDIA);
                break;
            case LUXURY:
                car = new LuxuryCar(Location.INDIA);
                break;
        }
        return car;
    }
}

class DefaultFactory{
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
        }
        return car;
    }
}

class CarFactoryAbstract{
    private CarFactoryAbstract(){}

    public static Car buildCar(CarType carType){
        Car car = null;
        Location location = Location.DEFAULT; // Read location property somewhere from configuration
        switch (location){
            case USA:
                car = USAFactory.buildCar(carType);
                break;
            case INDIA:
                car = IndiaFactory.buildCar(carType);
                break;
            default:
                car = DefaultFactory.buildCar(carType);
        }
        return car;
    }
}