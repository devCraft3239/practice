package code.string;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/*
* [0, 0, 0] - Black
* [255 255, 255] - White
* [255, 0, 0] - Red
* [0 255, 0] - Green
* [0 0, 255] - Blue
*
* given a string -> "000000000000000000000000"
* */
public class ClosestColor {
    private static Map<String, RGB> colorMap;
    static
    {
        colorMap = new HashMap<String, RGB>(){{
            put("Black", new RGB(0,0,0));
            put("White", new RGB(255,255,255));
            put("Red", new RGB(255,0,0));
            put("Green", new RGB(0,255,0));
            put("Blue", new RGB(0,0,0));
        }};
    }
    public static void main(String[] args) {
        String str = "000000001111111100000110";
        System.out.println(findClosestColor(str));
    }

    public static String findClosestColor(String str){
        int r = Integer.parseInt(str.substring(0,8), 2);
        int g = Integer.parseInt(str.substring(8,16), 2);
        int b = Integer.parseInt(str.substring(16), 2);
        RGB givenColor =  new RGB(r,g,b);
        AtomicReference<String> minDisColor= new AtomicReference<>("");
        AtomicReference<Double> minDis = new AtomicReference<>(Double.MAX_VALUE);
        colorMap.entrySet().forEach(stringRGBEntry -> {
            double dis = calDistance(stringRGBEntry.getValue(), givenColor);
//            System.out.println(stringRGBEntry.getKey()+":"+dis);
            if(minDis.get() > dis){
                minDis.set(dis);
                minDisColor.set(stringRGBEntry.getKey());
            }
        });
        return minDisColor.get();
    }

    public static double calDistance(RGB color1, RGB color2){
        return Math.sqrt(Math.pow(color1.r -color2.r,2)+ Math.pow(color1.g -color2.g,2)+Math.pow(color1.b -color2.b,2));
    }
}

class RGB{
    int r;
    int g;
    int b;

    RGB(int r, int g, int b){
        this.r = r;
        this.g= g;
        this.b =b;
    }
}
