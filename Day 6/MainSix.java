import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainSix {

    public static int findDataMarker(BufferedReader br) throws IOException {
        Dictionary<Character, Integer> alphabet = createAlphabetDictionary();

        String str;


        int c = 0;
        // continue while there are still characters to be read
        while ((c = br.read()) != -1) {
            int marker = 0;
            int marker_characters = 0;

            int current_value = alphabet.get(c);
            // if we have not seen this character yet in the sequence
            if (current_value == 0){
                // increment current character's count
                current_value += 1;
                // increment the marker
                marker += 1;
            }
            if (current_value > 0){
                // increment the total number of characters needed before a marker is found
                marker_characters += 1;
            }
        }

        return 4;
    }

    public static Dictionary<Character, Integer> createAlphabetDictionary () {
        Dictionary<Character, Integer> alphabet = new Hashtable<>();

        alphabet.put('a', 0);
        alphabet.put('b', 0);
        alphabet.put('c', 0);
        alphabet.put('d', 0);
        alphabet.put('e', 0);
        alphabet.put('f', 0);
        alphabet.put('g', 0);
        alphabet.put('h', 0);
        alphabet.put('i', 0);
        alphabet.put('j', 0);
        alphabet.put('k', 0);
        alphabet.put('l', 0);
        alphabet.put('m', 0);
        alphabet.put('n', 0);
        alphabet.put('o', 0);
        alphabet.put('p', 0);
        alphabet.put('q', 0);
        alphabet.put('r', 0);
        alphabet.put('s', 0);
        alphabet.put('t', 0);
        alphabet.put('u', 0);
        alphabet.put('v', 0);
        alphabet.put('w', 0);
        alphabet.put('x', 0);
        alphabet.put('y', 0);
        alphabet.put('z', 0);

        return alphabet;
    }

    public static void main(String[] args) throws Exception {
        // Day 6: File path is passed as parameter
        File file = new File("datastream.txt");

        // Day 6: Create a buffered reader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Day 6 Part 1:
        System.out.println(br);

        // Day 6 Part 2:
//        System.out.println();

    }
}
