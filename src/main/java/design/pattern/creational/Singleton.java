package design.pattern.creational;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 Design_patterns:
    Creational:
        are all about 'class instantiation or object creation'. ex: singleton, factory, abstract_factory, builder
    structural:
        to form larger structures and provide new functionality. ex: decorator
    Behavioral:
        identifying common 'communication patterns' between objects. ex: observer
 * */

/**
 A class of which only a single instance can exist
 prob:
    Application needs one, and only one, instance of an object.
    Additionally, lazy initialization and global access are necessary.
 Check list
     constructors to be private.
     static attribute
    public static accessor function
    Do "lazy initialization".

 uses:
    java.lang.Runtime
    SpringBoot beans (default scope)
    Hardware resource access (DB connection, File-system, DriverClasses)
    Logger
    Configuration File
    Cache objects
 * */

class singletonEagerInitialization{
    private static singletonEagerInitialization instance = new singletonEagerInitialization();
    private singletonEagerInitialization(){}

    public static  singletonEagerInitialization getInstance() {
        return instance;
    }
}


public class Singleton {
    private static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){     // not a thread-safe
        if(instance == null)
            instance = new Singleton();
        return instance;
    }
}

class SingletonThreadSafe{
    private static SingletonThreadSafe instance;
    private SingletonThreadSafe(){}

    public static synchronized SingletonThreadSafe getInstance() { // write optimised but expensive read
        if(instance == null)
            instance = new SingletonThreadSafe();
        return instance;
    }
}

class SingletonDoubleLocking{
    //the values of the volatile variable will never be cached and all writes/reads will be done to and from the main memory only.
    private static volatile SingletonDoubleLocking instance;
    private SingletonDoubleLocking(){}

    public static SingletonDoubleLocking getInstance() {
        if(instance == null)
            // To make thread safe
            synchronized (SingletonDoubleLocking.class){
                // check again as multiple thread can reach above step
                if (instance == null)
                    instance = new SingletonDoubleLocking();
            }
        return instance;
    }
}


class ReflectionSingleton {
    // public instance initialized when loading the class
    public static ReflectionSingleton instance = new ReflectionSingleton();

    private ReflectionSingleton()
    {
        // private constructor
    }
}

/**
 Reflection gives us information about the class to which an object belongs and also the methods of that class that can be executed by using the object.
 Through reflection, we can invoke methods at runtime irrespective of the access specifier used with them.
 * */
class ReflectionRunner {

    public static void main(String[] args)
    {
        ReflectionSingleton instance1 = ReflectionSingleton.instance;
        ReflectionSingleton instance2 = null;
        try {
            Constructor[] constructors = ReflectionSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                // Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instance2 = (ReflectionSingleton)constructor.newInstance();
                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("instance1.hashCode():- "
                + instance1.hashCode());
        System.out.println("instance2.hashCode():- "
                + instance2.hashCode());
    }
}
/**
 Reflection:
    As enums don’t have any constructor so it is not possible for Reflection to utilize it.
 * */
//Java program for Enum type singleton
enum SingletonEnum
{
    INSTANCE;
}

/**
 Serialization/deSerialization:
    To overcome this issue, we have to implement method readResolve() method.
 * */
class SingletonReadResolved implements Serializable
{
    // public instance initialized when loading the class
    public static SingletonReadResolved instance = new SingletonReadResolved();

    private SingletonReadResolved()
    {
        // private constructor
    }

    // implement readResolve method
    protected Object readResolve()
    {
        return instance;
    }
}


/**
 clone:
    To overcome this issue, override clone() method and throw an exception from clone method that is CloneNotSupportedException.
 * */
class SingletonCloneNonSupported implements Cloneable
{
    // public instance initialized when loading the class
    public static SingletonCloneNonSupported instance = new SingletonCloneNonSupported();

    private SingletonCloneNonSupported()
    {
        // private constructor
    }

    // implement clone() method
    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }
}