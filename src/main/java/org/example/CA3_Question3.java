package org.example;
import java.io.FileNotFoundException;

import java.util.*;
import java.io.File;
public class CA3_Question3 {

    public static ArrayList<String> readFile(String fileName)
    {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(fileName);
            Scanner input = new Scanner(file);

            input.useDelimiter("[^A-Za-z0-9_]+");

            while (input.hasNext()) {
                String line = input.nextLine();
                lines.add(line);
            }
            input.close();

        } catch (Exception ex) {
            System.out.println(">:3\nYou have been silly-ied!!!!\nThere is no such file as " + fileName + "!!!\nThe silly little :3's that live in this very computer must have stolen it :O !!!!!\nYou must go search for it, before they do their little silly dance and eated it all up.\nNom nom nom :3.");
        }

        return lines;
    }

    public static void main(String[] args) {
        ArrayList<String> lines = readFile("src/main/java/org/example/Ca3_Question1.java");

        HashMap<String, ArrayList<Integer>> identifiers = new HashMap<>();

        // 48-57 0-9
        // 65-90 A-Z
        // 95 _
        //97-122 a-z

        int lineNum = 0;

        for(String line : lines){
            lineNum++;
            String[] words = line.split(" ");
            for(String word : words) { //goes through every word in each line
                boolean isIdentifier = word.length() > 0; //default value for if it is an identifier, ignores blank spaces
                for(int i=0; i<word.length();i++){  // loops through every character in each word to see if they are a valid character
                    int ascii = (int)word.charAt(i); //converts char to an int to get ascii value for easier comparison
                    //          between 0 and 9      or   between A-Z               or is an _       or        between a-z
                    if(!((ascii >= 48 && ascii <=57) || (ascii >= 65 && ascii <=90) || (ascii == 95) || (ascii >= 97 && ascii <=122))){ //checks if not a valid value
                        isIdentifier = false;
                        break;
                    }
                }

                if(isIdentifier){
                    if(!identifiers.containsKey(word)){
                        identifiers.put(word, new ArrayList<>());
                    }
                    identifiers.get(word).add(lineNum);
                }
            }
        }

        for(String identifier : identifiers.keySet()){
            System.out.println(identifier + " - " + identifiers.get(identifier).toString());
        }



    }
}

