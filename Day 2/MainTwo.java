// importing input output classes
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
// import the Collections class to use for searching for maximum value in a dictionary

// import the HashMap and Map classes for dictionary use

public class MainTwo {

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
        // Day 2: File path is passed as parameter
        File file = new File("rockPaperScissorsStrategy.txt");

        // Day 2: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

//        // Day 2 Part 1: Calculate your final score based on the provided encrypted strategy
//        int score = calculateScore(br);
//        System.out.println(score);

        // Day 2 Part 2: Calculate your actual final score based on the elf's description
        int actualScore = calculateActualScore(br);
        System.out.println(actualScore);
    }
}