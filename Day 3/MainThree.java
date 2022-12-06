import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MainThree {

    /*
    Finds and returns the priority number of an ascii value character a-z (1-26) or A-Z (27-52)
     */
    public static int findPriorityNumber (int ascii) {
        int priorityNum = 0;

        // if a lowercase character a-z
        if (ascii >= 97 && ascii <= 122) {
            priorityNum += ascii - 96;
        }
        // if an uppercase character A-Z
        else if (ascii >= 65 && ascii <= 90) {
            priorityNum += ascii - 38;
        }

        return priorityNum;
    }

    /*
    Calculate the total priority score sum of all the items that appear in both compartments of a rucksack.
     */
    public static int calculatePrioritySum(BufferedReader br) throws Exception {
        String str;
        String[] compartments;
        int compartmentSize, ascii;
        int prioritySum = 0;

        // continue while there are still lines to be read
        // find the item that exists in both compartments of the rucksack and add that item's priority number to the sum
        while ((str = br.readLine()) != null) {
            compartmentSize = str.length() / 2; // grab the middle of the rucksack
            // split the rucksack items into two compartments
            compartments = new String[]{str.substring(0, compartmentSize), str.substring(compartmentSize)};
            // iterate over the items in compartment one and check if any of the items are also in compartment two
            for (int i = 0; i < compartments[0].length(); i++) {
                if (compartments[1].contains(compartments[0].charAt(i)+"")) {
                    ascii = compartments[0].charAt(i);
                    prioritySum += findPriorityNumber(ascii);
                    break;
                }
            }
        }
        return prioritySum;
    }

    /*
    Create a dictionary of elf groups of size three where each elf group's ID is the key and those three elves'
    rucksacks are the value.
     */
    public static Map<Integer, String[]> createElfRucksackGroup (BufferedReader br) throws Exception {
        String str;
        String[] rucksacks = new String[]{"","",""};
        int i = 0;
        int elfGroupID = 0;
        // create a dictionary, key as an elf group of three's ID and the value as an array of that group's three rucksacks
        Map<Integer, String[]> elfGroupRucksacks = new HashMap<>();

        // continue while there are still lines to be read
        while ((str = br.readLine()) != null) {
            if (i >= 3) {
                elfGroupRucksacks.put(elfGroupID, rucksacks.clone());
                elfGroupID++; // set the next group's id
                i = 0; // reset the array index back to 0
            }
            // if there hasn't been three rucksacks added to the array rucksacks, add the next rucksack to the array
            rucksacks[i] = str;
            i++;
        }
        // add the last group of elves to the dictionary
        elfGroupRucksacks.put(elfGroupID, rucksacks);

        return elfGroupRucksacks;
    }

    /*
    Find which item all three elves in a group are carrying, this is the elves' ID badges. Calculate the priority sum
    of all the badge types.
     */
    public static int calculateBadgePrioritySum (Map<Integer, String[]> elfGroupRucksacks) {
        int ascii;
        int badgePrioritySum = 0;

        // iterate over the dictionary of elf rucksack groups
        for (int i = 0; i < elfGroupRucksacks.size(); i++) {
            String[] rucksacks = elfGroupRucksacks.get(i);
            // iterate over the items in the first elf's rucksack
            for (int c = 0; c < rucksacks[0].length(); c++) {
                // check if all three rucksacks contain the same item, this must be the elves' badges
                if (rucksacks[1].contains(rucksacks[0].charAt(c)+"") && rucksacks[2].contains(rucksacks[0].charAt(c)+"")) {
                    ascii = rucksacks[0].charAt(c);
                    badgePrioritySum += findPriorityNumber(ascii);
                    break;
                }
            }
        }
        return badgePrioritySum;
    }

    public static void main(String[] args) throws Exception {
        // Day 3: File path is passed as parameter
        File file = new File("rucksacks.txt");

        // Day 3: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

//        // Day 3 Part 1: Calculate your final score based on the provided encrypted strategy
//        int prioritySum = calculatePrioritySum(br);
//        System.out.println(prioritySum);

        // Day 3 Part 2: Create the elf groups of three rucksack dictionary
        Map<Integer, String[]> elfGroupRucksacks = createElfRucksackGroup(br);

        // Day 3 Part 2: Calculate your actual final score based on the elf's description
        int badgePrioritySum = calculateBadgePrioritySum(elfGroupRucksacks);
        System.out.println(badgePrioritySum);
    }
}

