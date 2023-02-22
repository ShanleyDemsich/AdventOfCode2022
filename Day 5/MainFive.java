import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    public static void main(String[] args) throws Exception {
        // Day 5: File path is passed as parameter
        File file = new File("crateRearrangementProcedure.txt");

        // Day 5: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Day 5 Part 1:
        System.out.println(createBoxStacks(br));

        // Day 5 Part 2:
//        System.out.println();
    }
}
