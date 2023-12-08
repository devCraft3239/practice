package code.dp;

public class BinaryStringWithoutConsecutive1 {
    public static void main(String[] args) {
        System.out.println(numberOfBinaryStringWithoutConsecutiveOne(3));
    }

    public static int  numberOfBinaryStringWithoutConsecutiveOne(int n){
        int[] a = new int[n];
        int[] b = new int[n];
        a[0] = b[0] =1;
        for (int i = 1; i < n; i++) {
            a[i] =  a[i-1] // adding 0 at the end
                    +b[i-1]; // adding 1 at the end
            b[i] = a[i-1]; // since can add 0 at end, so number of string will be same as previous a[i];
        }
        return a[n-1]+b[n-1];
    }
}
