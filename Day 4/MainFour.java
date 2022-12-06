import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class MainFour {

    /*
    Create a dictionary of elf cleaning pairs each elf group's ID is the key and the keys are the two assignment
    cleaning section lists as the value.
     */
    public static Map<Integer, String[]> createElfCleaningPairs (BufferedReader br) throws Exception {
        String str;
        String[] cleaningAssignments = new String[]{"",""};
        int i = 0;
        int elfPairID = 0;
        // create a dictionary, key as an elf group of three's ID and the value as an array of that group's three rucksacks
        Map<Integer, String[]> elfPairs = new HashMap<>();

        // continue while there are still lines to be read
        while ((str = br.readLine()) !=null) {
            if (i >= 2) {
                elfPairs.put(elfPairID, cleaningAssignments.clone());
                elfPairID++; // set the next group's id
                i = 0; // reset the array index back to 0
            }
            // if there hasn't been three rucksacks added to the array rucksacks, add the next rucksack to the array
            cleaningAssignments[i] = str;
            i++;
        }
        // add the last group of elves to the dictionary
        elfPairs.put(elfPairID, cleaningAssignments);

        return elfPairs;
    }

    public static void main(String[] args) throws Exception {
        // Day 4: File path is passed as parameter
        File file = new File("sectionAssignments.txt");

        // Day 4: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

//        // Day 4 Part 1: Find how many cleaning elf assignment pairs fully contain one of the partner's assinments
//        int assignmentContainment = findAssignmentContainment(br);
//        System.out.println(assignmentContainment);

        // Day 4 Part 2:
//        System.out.println();
    }
}
