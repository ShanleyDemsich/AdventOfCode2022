import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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
    public static List<Character> maneuverBoxes(List<List<Character>> boxStacks, List<List<Integer>> maneuvers) {
        int i;
        for (i=0; i < maneuvers.size(); i++){
            List<Integer> currentMove = maneuvers.get(i);
        }
        return findTopBoxes(boxStacks);
    }

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

        // Day 5 Part 1:
        // create the box stacks
        List<List<Character>> boxStacks = createBoxStacks(br);
        // isolate the box maneuver instructions
        List<List<Integer>> maneuvers = isolateManeuvers(br);
        // maneuver the boxes and return the top boxes from each stack
//        String topBoxes = maneuverBoxes(boxStacks, maneuvers);
//        System.out.println(topBoxes);
        System.out.println(findTopBoxes(boxStacks));


        // Day 5 Part 2:
//        System.out.println();
    }
}
