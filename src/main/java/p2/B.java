package p2;

import p1.A;

public class B extends A{
    public static void main(String[] args) {
        A obj =  new B();
//        System.out.println(obj.a); private cannot be acc
//        System.out.println(obj.b); // package private can be access within package
//        System.out.println(obj.c); // protected package
        System.out.println(obj.d); // public

        B obj2 = new B();
        System.out.println(obj2.c); // protected through inheritance only within child class
    }
}

class C{
    public static void main(String[] args) {
        B obj =  new B();
//        System.out.println(obj.c); // can be access withing child class only through inheritance
        System.out.println(obj.d);
    }
}
