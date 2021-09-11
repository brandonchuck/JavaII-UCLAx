package government;

// 435 members = 100 senators + 335 members of congress

import java.util.ArrayList;


// Congress Class
public class Congress {

    // private constructor
    private Congress(){

    }

    // private ArrayList of Congress members
    private static ArrayList<Congress> congressList = new ArrayList<>();


    // method for creating a new member of congress up to a maximum of 435 members.
    // must be static since constructor is private
    public static ArrayList<Congress> getMemberOfCongress(){
        if (congressList == null || congressList.size() < 435){
            congressList.add(new Congress());
            System.out.println("Size of Congress: " + congressList.size());
        } else {
            System.out.println("There can only be 435 members of Congress");
            System.out.println("Size of Congress: " + congressList.size());
        }
        return congressList;
    }

    // Prints memory address for each Congress member object
    public static void verifyCongress(){
        for (Congress c: congressList){
            System.out.println("Congress Member: " + c);
        }
        System.out.println("Size of Congress: " + congressList.size());
    }


    // Remove a member of Congress from list of congress members
    public static void impeachCongress(int member){
        congressList.remove(member); // remove member from list of Congress members
    }
}


