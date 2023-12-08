package code.mix;

public class ExceptionTest {
   public static void main(String[] args) {
      System.out.println(exceptionTest());
   }
   public static int exceptionTest() {
      int i = 6;
      try {
         throw new NullPointerException();
      } catch (Exception e) {
         i = 10;
         return i;
      } finally {
         i = 20;
         System.out.println("In finally block");
      }
   }


}

class Test{
   public static void main(String[] args) {
      runtimeOrComileTime();
   }
   public static void runtimeOrComileTime() {
      int[] n1 = new int[0];
//      boolean[] n2 = new boolean[-200];
//      double[] n3 = new double[2241423798];
      char[] ch = new char[20];
   }
}
class InterviewBit{
   public static void main(String[] args)
   {
      System.out.println('b' + 'i' + 't');
   }
}