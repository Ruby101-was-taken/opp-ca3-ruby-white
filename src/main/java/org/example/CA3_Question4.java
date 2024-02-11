package org.example;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

/**
 *  Name: Ruby White :3
 *  Class Group: GD2B
 */

public class CA3_Question4 {

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
            System.exit(0);
        }

        return lines;
    }

    public static void unOpenedTag(String tag){
        System.out.println("Tag " + tag + " was closed but never opened.");
        System.exit(0);
    }
    public static void wrongTagClosed(String tag, String unclosedTag){
        System.out.println("Tag " + tag + " was closed before tag " + unclosedTag + ".");
        System.exit(0);
    }
    public static void unClosedTagRemaining(String tag){
        System.out.println("Tag " + tag + " was opened yet never closed.");
        System.exit(0);
    }


    public static String getTagType(String fullTag){ // takes string formatted like "/p" and returns "p" or like "/ul" and returns "ul"
        if(fullTag.charAt(0) == '/')
            return fullTag.substring(1, fullTag.length());
        else
            return fullTag;
    }

    public static void main(String[] args) {
        ArrayList<String> lines = readFile("tags_invalid.html");


        Deque<String> unClosedTags = new ArrayDeque<>();

        for(String line : lines){
            line = line.replace('<', ' ');
            line = line.replace('>', ' ');
            String[] words = line.split(" ");
            for(String word : words) { //goes through every word in each line
                if(word.length() > 0) {
                    word = word.toLowerCase(); //makes the tag standard so that we don't need to worry about upper case characters :3
                    if(word.charAt(0) == '/') { //is closing tag
                        String tag = getTagType(word);
                        if (unClosedTags.size() == 0 || !unClosedTags.contains(tag)) { //if the first tag is a closing tag, return the error, or if the tag is never in the stack in the first place
                            unOpenedTag(getTagType(word));
                        } else if(!unClosedTags.peek().equals(tag)) { //if a tag is closed before the most recent
                            wrongTagClosed(tag, unClosedTags.peek());
                        }
                        else //closed tag is valid, therefore can remove tag from stack :3
                            unClosedTags.pop();
                    }
                    else{ //not a closing tag
                        unClosedTags.push(word);
                    }
                }
            }
        }

        if(unClosedTags.size() > 0){
            unClosedTagRemaining(unClosedTags.peek());
        }
        else {
            System.out.println("Parsing html tags successful!");
        }
    }
}
