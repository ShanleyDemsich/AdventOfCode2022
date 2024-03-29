import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainFive {

    // create a list of each box stack
    public static List<List<Character>> createBoxStacks(BufferedReader br) throws Exception {
        // create the list of the box stacks
        List<List<Character>> stacks = new ArrayList<>();
        for (int i=0; i<9; i++){
            stacks.add(new ArrayList<>());
        }

        String str;

        // continue while there are still lines to be read
        while ((str = br.readLine()) != null && Character.isLetter(str.charAt(1))) {
            // check for any boxes in current row and add them to their box stacks when necessary
            if (Character.isLetter(str.charAt(1)))
                stacks.get(0).add(str.charAt(1));
            if (Character.isLetter(str.charAt(5)))
                stacks.get(1).add(str.charAt(5));
            if (Character.isLetter(str.charAt(9)))
                stacks.get(2).add(str.charAt(9));
            if (Character.isLetter(str.charAt(13)))
                stacks.get(3).add(str.charAt(13));
            if (Character.isLetter(str.charAt(17)))
                stacks.get(4).add(str.charAt(17));
            if (Character.isLetter(str.charAt(21)))
                stacks.get(5).add(str.charAt(21));
            if (Character.isLetter(str.charAt(25)))
                stacks.get(6).add(str.charAt(25));
            if (Character.isLetter(str.charAt(29)))
                stacks.get(7).add(str.charAt(29));
            if (Character.isLetter(str.charAt(33)))
                stacks.get(8).add(str.charAt(33));
        }
        return stacks;
    }

    // create a list of the box maneuvers the crane elf needs to perform
    public static List<List<Integer>> isolateManeuvers(BufferedReader br) throws IOException {
        List<List<Integer>> maneuvers = new ArrayList<>();

        String str;
        int i = 0;

        // continue while there are still lines to be read
        while ((str = br.readLine()) != null) {
            // separate the string on each space
            String[] segments = str.split(" ");
            // check if this is a maneuver line, if so add the maneuver to the list of maneuver
            if (segments[0].contains("move")){
                maneuvers.add(new ArrayList<>());
                maneuvers.get(i).add(Integer.parseInt(segments[1]));
                maneuvers.get(i).add(Integer.parseInt(segments[3]));
                maneuvers.get(i).add(Integer.parseInt(segments[5]));
                i++;
            }
        }
        return maneuvers;
    }

    // Maneuvering one box at a time based on the crate rearrangement procedure
    public static List<Character> maneuverSingleBoxes(List<List<Character>> boxStacks, List<List<Integer>> maneuvers) {
        int i;
        for (i=0; i < maneuvers.size(); i++){
            int numBoxes = maneuvers.get(i).get(0);
            int from = maneuvers.get(i).get(1) - 1;
            int to = maneuvers.get(i).get(2) - 1;
            while (numBoxes != 0) {
                boxStacks.get(to).add(0, (boxStacks.get(from).get(0)));
                boxStacks.get(from).remove(0);
                numBoxes--;
            }
        }
        return findTopBoxes(boxStacks);
    }

    // maneuvering multiple boxes at a time based on the crate rearrangement procedure
    public static List<Character> maneuverMultipleBoxes(List<List<Character>> boxStacks, List<List<Integer>> maneuvers) {
        int i;
        for (i=0; i < maneuvers.size(); i++){
            int numBoxes = maneuvers.get(i).get(0);
            int from = maneuvers.get(i).get(1) - 1;
            int to = maneuvers.get(i).get(2) - 1;
            int placement = 0;
            while (numBoxes != 0) {
                boxStacks.get(to).add(placement, (boxStacks.get(from).get(0)));
                boxStacks.get(from).remove(0);
                numBoxes--;
                placement++;
            }
        }
        return findTopBoxes(boxStacks);
    }

    // Return a list of all the boxes that are the top of their stack
    public static List<Character> findTopBoxes(List<List<Character>> boxStacks) {
        List<Character> topBoxes = new ArrayList<>();

        int i;
        for (i=0; i < 9; i++) {
            topBoxes.add(boxStacks.get(i).get(0));
        }
        return topBoxes;
    }

    public static void main(String[] args) throws Exception {
        // Day 5: File path is passed as parameter
        File file = new File("crateRearrangementProcedure.txt");

        // Day 5: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // create the box stacks
        List<List<Character>> boxStacks = createBoxStacks(br);

        // isolate the box maneuver instructions
        List<List<Integer>> maneuvers = isolateManeuvers(br);

        // Day 5 Part 1:
//        List<Character> topBoxes = maneuverSingleBoxes(boxStacks, maneuvers);
//        System.out.println(topBoxes);

        // Day 5 Part 2:
        List<Character> topBoxesTwo = maneuverMultipleBoxes(boxStacks, maneuvers);
        System.out.println(topBoxesTwo);
    }
}
