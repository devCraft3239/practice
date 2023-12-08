package p1;

public class A {
    private static Object a;
    Object b;
    protected Object c;
    public Object d;
}

class AA{
    public static void main(String[] args) {
        A obj =  new A();
//        System.out.println(obj.a); private to class
        System.out.println(obj.b);
        System.out.println(obj.c);
        System.out.println(obj.d);
    }
}
