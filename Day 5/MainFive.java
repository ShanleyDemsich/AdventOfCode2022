import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainFive {

    // create a list of each box stack
    public static List<List<Character>> createBoxStacks(BufferedReader br) throws Exception {
        // create the list of stacked boxes
        List<List<Character>> boxes = new ArrayList<List<Character>>();

        // create the stacked box lists
        List<Character> stack1 = new ArrayList<>();
        List<Character> stack2 = new ArrayList<>();
        List<Character> stack3 = new ArrayList<>();
        List<Character> stack4 = new ArrayList<>();
        List<Character> stack5 = new ArrayList<>();
        List<Character> stack6 = new ArrayList<>();
        List<Character> stack7 = new ArrayList<>();
        List<Character> stack8 = new ArrayList<>();
        List<Character> stack9 = new ArrayList<>();

        String str;

        // continue while there are still lines to be read
        while ((str = br.readLine()) != null && Character.isLetter(str.charAt(1))) {
            // check for any boxes in current row and add them to their box stacks when necessary
            if (Character.isLetter(str.charAt(1)))
                stack1.add(str.charAt(1));
            if (Character.isLetter(str.charAt(5)))
                stack2.add(str.charAt(5));
            if (Character.isLetter(str.charAt(9)))
                stack3.add(str.charAt(9));
            if (Character.isLetter(str.charAt(13)))
                stack4.add(str.charAt(13));
            if (Character.isLetter(str.charAt(17)))
                stack5.add(str.charAt(17));
            if (Character.isLetter(str.charAt(21)))
                stack6.add(str.charAt(21));
            if (Character.isLetter(str.charAt(25)))
                stack7.add(str.charAt(25));
            if (Character.isLetter(str.charAt(29)))
                stack8.add(str.charAt(29));
            if (Character.isLetter(str.charAt(33)))
                stack9.add(str.charAt(33));
        }
        boxes.add(stack1);
        boxes.add(stack2);
        boxes.add(stack3);
        boxes.add(stack4);
        boxes.add(stack5);
        boxes.add(stack6);
        boxes.add(stack7);
        boxes.add(stack8);
        boxes.add(stack9);

        return boxes;
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
