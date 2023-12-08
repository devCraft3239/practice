package code.linkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class LinkedListsIntersection {
    public  static Map<String, String> map;
    static
    {
        map =  new HashMap<>();
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            if(line.contains("-")){
                String[] tokens = line.split("->");
                map.put(tokens[0], tokens[1]);
            }else if(line.contains(",")){
                String[] tokens = line.split(",");
                System.out.println(DoLinkedListsIntersect(Arrays.asList(tokens)));
            }
        }
    }

    public static boolean DoLinkedListsIntersect(Collection<String> collection){
        Set<String> set = new HashSet();
        List<String> tokens =(List) collection;
        for (String token: tokens.toArray(new String[0])) {
            if(set.contains(token)) {
                return Boolean.TRUE;
            }
            set.add(token);
            while(map.containsKey(token)){
                String val =  map.get(token);
                if(set.contains(val)){
                    return Boolean.TRUE;
                }
                set.add(val);
                token = val;
            }
        }
        return Boolean.FALSE;
    }
}
