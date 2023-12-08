package code.string;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//    Q: sort odd digit numbers based on their middle digit
//    {"10201","30213","90051","36103","92315"}
public class SortByMiddleDigit {
    public void sortBasedOnMiddleDigit(List<String> numbers) {
        Collections.sort(numbers, new SortByMiddleDigitComparator());
    }
    class SortByMiddleDigitComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int mid =  o1.length()/2;
            if(o1.charAt(mid) < o2.charAt(mid)) {
                return -1;
            }
            else if(o1.charAt(mid) == o2.charAt(mid)){
                return o1.compareTo(o2);
            }
            else {
                return 1;
            }
        }
    }
}
