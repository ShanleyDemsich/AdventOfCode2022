import java.io.*;
import java.util.*;

public class MainOne {

    /*
    Helper method to find the elf with the highest calories
     */
    public static int findMaxCals(Map<Integer, Integer> inventory) {
        return Collections.max(inventory.values());
    }

    /*
    Find and return the top number of given elves' snack calories sum.
     */
    public static int findTopCals(Map<Integer, Integer> inventory, int top) {
        Map<Integer, Integer> sortedInventory = new LinkedHashMap<>();

        // sort the dictionary based on the values in descending order
        inventory.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sortedInventory.put(x.getKey(), x.getValue()));

        int topCalories = 0;
        int elfCount = 0;

        // create an iterator to allow you to iterate over the sorted inventory
        Iterator<Map.Entry<Integer, Integer>> iterator = sortedInventory.entrySet().iterator();

        // find the top number of elves' snack calories
        while (elfCount < top){

            // find the current max calories in inventory and add to the topCalories sum
            int currentMax = iterator.next().getValue();
            topCalories = topCalories + currentMax;

            elfCount++;
        }
        return topCalories;
    }

    /*
    Create and populate the elf inventory dictionary where each elf is given an id as a key in the dictionary
    and each elf's total snack calories are stored as the value. We are given a .txt file with a list of calories in a
    snack an elf is carrying. Each elf's group of snack calories is separated by an empty line.
     */
    public static Map<Integer, Integer> createElfInventory(BufferedReader br) throws IOException {

        // Declare a string variable
        String str;
        // Declare an integer sum used to find an elf's total calories
        int sum = 0;
        // Declare a 1-indexed elfID
        int elfID = 1;

        // Create a dictionary inventory
        Map<Integer, Integer> inventory = new HashMap<Integer, Integer>();

        // Continue while there are still lines to be read
        while ((str = br.readLine()) != null){
            // if the next line is a non-empty string, read the line as an integer and add it to the current snack sum
            if(!str.equals("")){
                sum = sum + Integer.parseInt(str);
            }
            else{
                // if the current line is a string, then we have just completed a summation of the current elf's snack calories
                inventory.put(elfID, sum); // add new elf to the inventory dictionary
                sum = 0; // reset sum to 0 for the next elf
                elfID++; // create next elf's ID
            }
        }
        return inventory;
    }

    /*
    Based on the encrypted strategy guide the elf gave you calculate your final score. You earn a score for your selection
    (1 for rock, 2 for paper, and 3 for scissors) and also based on the outcome of the round (6 if you win, 3 for a
    draw, and 0 is you lose).
     */
    public static int calculateScore (BufferedReader br) throws Exception {
        // Declare a string variable
        String str;
        // Declare an integer sum used to find your RPS score
        int score = 0;

        // continue while there are still lines to be read, check what the strategy guide suggests at that round and
        // update the score based on the outcome
        while ((str = br.readLine()) != null) {
                if (str.contains("A") && str.contains("X")) {
                    score += 1 + 3;
                }
                else if (str.contains("A") && str.contains("Y")) {
                score += 2 + 6;
                }
                else if (str.contains("A") && str.contains("Z")) {
                    score += 3;
                }
                else if (str.contains("B") && str.contains("X")) {
                score += 1;
                }
                else if (str.contains("B") && str.contains("Y")) {
                    score += 2 + 3;
                }
                else if (str.contains("B") && str.contains("Z")) {
                score += 3 + 6;
                }
                else if (str.contains("C") && str.contains("X")) {
                    score += 1 + 6;
                }
                else if (str.contains("C") && str.contains("Y")) {
                    score += 2;
                }
                else if (str.contains("C") && str.contains("Z")) {
                    score += 3 + 3;
                }
            }
        return score;
    }

    /*
    Based on the encrypted strategy guide the elf gave you calculate your final score. You earn a score for your selection
    (1 for rock, 2 for paper, and 3 for scissors) and also based on the outcome of the round (6 if you win, 3 for a
    draw, and 0 is you lose). This strategy plan tells you when you need to lose (X), when you need to draw (Y), and
    when you need to win (Y).
     */
    public static int calculateActualScore (BufferedReader br) throws Exception {
        // Declare a string variable
        String str;
        // Declare an integer sum used to find your RPS score
        int score = 0;

        // continue while there are still lines to be read, check what the strategy guide suggests at that round and
        // update the score based on the outcome
        while ((str = br.readLine()) != null) {
            // check if you are supposed to lose (X), draw (Y), or win (Z)
            if (str.contains("X")) {
                // to lose you play scissors
                if (str.contains("A")) {
                    score += 3;
                }
                // to lose you play rock
                else if (str.contains("B")) {
                    score += 1;
                }
                // to lose you play paper
                else if (str.contains("C")) {
                    score += 2;
                }
            } else if (str.contains("Y")) {
                // to draw you play rock
                if (str.contains("A")) {
                    score += 1 + 3;
                }
                // to draw you play paper
                else if (str.contains("B")) {
                    score += 2 + 3;
                }
                // to draw you play scissors
                else if (str.contains("C")) {
                    score += 3 + 3;
                }
            } else if (str.contains("Z")) {
                // to win you play paper
                if (str.contains("A")) {
                    score += 2 + 6;
                }
                // to win you play scissors
                else if (str.contains("B")) {
                    score += 3 + 6;
                }
                // to win you play rock
                else if (str.contains("C")) {
                    score += 1 + 6;
                }
            }
        }
        return score;
    }

    public static void main(String[] args) throws Exception {
        // Day 1: File path is passed as parameter
        File file = new File("elfCalorieInventory.txt");

        // Day 1: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Day 1 Part: Create the elf inventory with elfID as key and that elf's total snack calories as value
        Map<Integer, Integer> inventory = createElfInventory(br);

        // Day 1 Part 1: Print the inventory dictionary
        System.out.println(inventory);

        // Day 1 Part 1: Find and print the maximum calories the elf with the highest snack calories carries
        System.out.println(findMaxCals(inventory));

        // Day 1 Part 2: Print the total snack calories of the top three elves with the top three snack calorie totals
        System.out.println(findTopCals(inventory, 3));

    }
}

