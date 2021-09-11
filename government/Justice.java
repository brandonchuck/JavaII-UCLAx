package government;

import java.util.ArrayList;
import java.util.HashMap;


public class Justice {

    // Hashmap to store each Justice; there is only 1 hashtable that contains the Justices
    // Justices are added given a name (value) in the version I've created.
    private static HashMap<Justice, String> justiceMap = new HashMap<>(); // cannot add justices via .put() to hashmap because it is private


    // 9 Justices total
    // Private Constructor
    private Justice(){

    }


    // create new justice
    // must be a static method since the constructor is private
    public static HashMap<Justice, String> getJusticeInstance(String name){
        if (justiceMap.size() < 9){
            justiceMap.put(new Justice(), name);
            System.out.println("Number of Justices: " + justiceMap.size()); // print number of justices
            System.out.println(justiceMap.values() + "\n");
        } else {
            System.out.println("There can only be 9 justices.\n");
        }
        return justiceMap;
    }


    // Verify the memory addresses for each of the 9 Justices, and verify there can only be 9 created
    public static void verifyJustices(){
        for (Justice j: justiceMap.keySet()){
            System.out.println(justiceMap.get(j) + ": " + j);
        }
    }


    // Impeach a justice
    public static void impeachJustice(String justiceName){

        ArrayList<Justice> toRemove = new ArrayList<>(); // list of justices to remove. Work around since cannot alter elements of a list while looping though it

        if (justiceMap.containsValue(justiceName)){
            for (Justice j: justiceMap.keySet()){
                if (justiceMap.get(j).equals(justiceName)){
                    toRemove.add(j);
                }
            }
        }

        for (Justice j: toRemove){
            if (justiceMap.containsKey(j)){
                justiceMap.remove(j);
            }
        }

        System.out.println(justiceMap.values()); // prints array of justices
    }

}

