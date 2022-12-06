import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainFour {

    public static int countAssignmentOverlaps (BufferedReader br) throws Exception {
        String str;
        int assignmentOverlaps = 0;
        String[] cleaningAssignments, elfOne, elfTwo;
        int eOneStart, eOneEnd, eTwoStart, eTwoEnd;

        // continue while there are still lines to be read
        while ((str = br.readLine()) != null) {
            cleaningAssignments = str.split(",");
            elfOne = cleaningAssignments[0].split("-");
            elfTwo = cleaningAssignments[1].split("-");

            eOneStart = Integer.parseInt(elfOne[0]);
            eOneEnd = Integer.parseInt(elfOne[1]);
            eTwoStart = Integer.parseInt(elfTwo[0]);
            eTwoEnd = Integer.parseInt(elfTwo[1]);

            // if one of the elves' assignments in a cleaning pair are entirely contained within their partners assignments
            if ((eOneStart < eTwoStart && eOneEnd > eTwoEnd) || (eOneStart > eTwoStart && eOneEnd < eTwoEnd)) {
                assignmentOverlaps++;
            }
        }

        return assignmentOverlaps;
    }

    public static void main(String[] args) throws Exception {
        // Day 4: File path is passed as parameter
        File file = new File("sectionAssignments.txt");

        // Day 4: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Day 4 Part 1: Create the elf cleaning pairs dictionary
//        Map<Integer, String[]> elfCleaningPairs = createElfCleaningPairs(br);

        int count = countAssignmentOverlaps(br);
        System.out.println(count);

//        // Day 4 Part 1: Find how many cleaning elf assignment pairs fully contain one of the partner's assignments
//        int assignmentContainment = findAssignmentContainment(br);
//        System.out.println(assignmentContainment);

        // Day 4 Part 2:
//        System.out.println();
    }
}
