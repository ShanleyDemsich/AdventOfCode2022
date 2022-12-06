import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainFour {

    // count how many elf cleaning assignments are entirely contained within a partner's cleaning assignments
    public static int countCompleteAssignmentOverlaps (BufferedReader br) throws Exception {
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

            // if one of the elves' assignments in a cleaning pair is entirely contained within their partners assignments
            if ((eOneStart <= eTwoStart && eOneEnd >= eTwoEnd) || (eOneStart >= eTwoStart && eOneEnd <= eTwoEnd)) {
                assignmentOverlaps++;
            }
        }

        return assignmentOverlaps;
    }

    // count how many elf cleaning assignments are partially contained within a partner's cleaning assignments
    public static int countPartialAssignmentOverlaps (BufferedReader br) throws Exception {
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

            // if one of the elves' assignments in a cleaning pair is partially contained within their partners assignments
            if (!(eOneEnd < eTwoStart) && !(eTwoEnd < eOneStart)) {
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

        // Day 4 Part 1: Count how many cleaning assignments entirely overlap between cleaning elf partners
//        int completeAssignmentOverlaps = countCompleteAssignmentOverlaps(br);
//        System.out.println(completeAssignmentOverlaps);

        // Day 4 Part 2: Count how many cleaning assignments partially overlap
        int partialAssignmentOverlaps = countPartialAssignmentOverlaps(br);
        System.out.println(partialAssignmentOverlaps);
    }
}
