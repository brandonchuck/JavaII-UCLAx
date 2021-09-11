package government;

import government.*;

import java.util.Collection;
import java.util.HashMap;

public class CivicsMain {

    public static void main(String[] args) {

        // ********************************* POTUS Class Test *********************************

        // Create first POTUS
        POTUS p = POTUS.getInstance();
        System.out.println(p + "\n");

        // Verify singleton POTUS
        p = POTUS.getInstance();
        System.out.println(p + "\n");

        // Remove POTUS in memory
        POTUS.impeach(); // remove POTUS in memory

        // Create second POTUS
        p = POTUS.getInstance();
        System.out.println(p + "\n");

        // Verify singleton for second POTUS
        p = POTUS.getInstance();
        System.out.println(p + "\n");




        // ********************************* Justice Class Test *********************************
        // Instantiate Justices
        Justice.getJusticeInstance("Brandon");  // 1
        Justice.getJusticeInstance("Agatha");   // 2
        Justice.getJusticeInstance("Danny");    // 3
        Justice.getJusticeInstance("Caleb");    // 4
        Justice.getJusticeInstance("Mica");     // 5
        Justice.getJusticeInstance("Gerald");   // 6
        Justice.getJusticeInstance("Mary");     // 7
        Justice.getJusticeInstance("David");    // 8
        Justice.getJusticeInstance("Maria");    // 9

        // This justice will not be created
        Justice.getJusticeInstance("Thomas");   // *** 10 ***

        // Verify the memory addresses for each Justice object
        Justice.verifyJustices();

        // Remove Justices from the hashmap and show remaining Justices
        Justice.impeachJustice("Brandon");
        Justice.impeachJustice("Mica");

        System.out.println("======================================================================\n");





        // ********************************* Congress Class Test *********************************
        // Write for loop to instantiate all 435 members of congress

        int numMOC = 0;

        // 450 to demonstrate exceeding maximum members of Congress
        // This will also show only 435 members of Congress being instantiated
        // Shows size of Congress growing from 1 --> 435 members
        while (numMOC < 450){
            Congress.getMemberOfCongress();
            numMOC++;
        }

        // verify that there are only 435 members of congress
        Congress.verifyCongress();

        // impeach the specified member of congress based on index
        Congress.impeachCongress(1);

        System.out.println("\n\n======================================================================");

        // Verify that that the specified member of congress has been impeached
        // Look for change in size of congress and the the removal of the specified congress member's memory address
        Congress.verifyCongress();

    }
}
