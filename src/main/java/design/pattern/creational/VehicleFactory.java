package design.pattern.creational;

public class VehicleFactory {
}

enum CarType {
    MINI,
    SEDAN,
    SUV
}

enum BRAND {
    MARUTI,
    HONDA,
    HYUNDAI
}

interface Car {
    void assemble();
}

class SedanCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Assemble Sedan Car");
    }
}

class MiniCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Assemble Mini Car");
    }
}

class SUVCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Assemble SUV Car");
    }
}

interface AbstractCarFactory {
    Car buildCar(CarType carType);
}

class MarutiCarFactory implements AbstractCarFactory{
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType) {
            case MINI:
                car = new MiniCar();
                break;
            case SEDAN:
                car = new SedanCar();
                break;
            case SUV:
                car = new SUVCar();
                break;
        }
        return car;
    }
}

class HondaCarFactory implements AbstractCarFactory{
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType) {
            case MINI:
                car = new MiniCar();
                break;
            case SEDAN:
                car = new SedanCar();
                break;
            case SUV:
                car = new SUVCar();
                break;
        }
        return car;
    }
}

class HyundaiCarFactory implements AbstractCarFactory{
    public Car buildCar(CarType carType) {
        Car car = null;
        switch (carType) {
            case MINI:
                car = new MiniCar();
                break;
            case SEDAN:
                car = new SedanCar();
                break;
            case SUV:
                car = new SUVCar();
                break;
        }
        return car;
    }
}

class CarFactory {
    public static Car buildCar(BRAND brand, CarType carType) {
        AbstractCarFactory carFactory = null;
        Car car = null;
        switch (brand) {
            case MARUTI:
                carFactory = new MarutiCarFactory();
                car = carFactory.buildCar(carType);
                break;
            case HONDA:
                carFactory = new HondaCarFactory();
                car = carFactory.buildCar(carType);
                break;
            case HYUNDAI:
                carFactory = new HyundaiCarFactory();
                car = carFactory.buildCar(carType);
                break;
        }
        return car;
    }
}

class Client {
    public static void main(String[] args) {
        Car car = CarFactory.buildCar(BRAND.MARUTI, CarType.SEDAN);
        car.assemble();
        Car car1 = CarFactory.buildCar(BRAND.HONDA, CarType.SUV);
        car1.assemble();
    }
}


