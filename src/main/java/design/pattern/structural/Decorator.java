package design.pattern.structural;
/**
 * one of the structural design pattern
 'decorate with additional functionality of an object at runtime' without affecting other instances of same class.
 At the same time other instances of the same class will not be affected by this.
 * */
public class Decorator {}
interface Car{
    void assemble();
}

class BasicCar implements Car{
    @Override
    public void assemble() {
        System.out.println("Assembled basic car");
    }
}

abstract class CarDecorator implements Car{
    @Override
    public void assemble() {

    }

    abstract void additionalFeature();
}

class SportDecorator extends CarDecorator{

    @Override
    void additionalFeature() {
        System.out.println("added sport feature.");
    }
}

class LuxuryDecor extends CarDecorator{
    @Override
    void additionalFeature() {
        System.out.println("added luxury feature.");
    }
}

class DecoratorRunner{
    public static void main(String[] args) {
    }
}
