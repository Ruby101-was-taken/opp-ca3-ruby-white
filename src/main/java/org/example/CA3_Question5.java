package org.example;

import java.util.LinkedList;
import java.util.Scanner;

public class CA3_Question5 {

    public static void incorrectFormatting(){
        System.out.println("==========\nThe command has the wrong formatting.\nCheck that all brackets are closed and that you have not used the in a plane name.\n==========");
    }
    public static void incorrectFormattingQuotes(){
        System.out.println("==========\nThe command has the wrong formatting.\nCheck that all brackets are closed and that you have not used and open bracket in the plane name.\nYou may also have forgotten to add quotes or a semi-colon.\n==========");
    }

    public static String getUserCommand() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plane ID: ");
        String enteredText = input.nextLine();

        enteredText = enteredText.replace(" ", ""); //make a single line
        String[] command = enteredText.split("\\("); //will not work without \\ since java does not realise it's a string, it's so silly :P
        command[0] = command[0].toLowerCase();

        if (command.length != 2){
            incorrectFormatting();
            return "error";
        }
        else if(!(command[1].charAt(0) == '"' && command[1].substring(command[1].length()-3, command[1].length()).equals("\");"))) {
            if(enteredText.toLowerCase().equals("next();")){
                return "next";
            }
            else if(enteredText.toLowerCase().equals("quit();")){
                return "quit";
            }
            else if(enteredText.toLowerCase().equals("help();") || enteredText.toLowerCase().equals("?")){
                return "help";
            }
            incorrectFormattingQuotes();
            return "error";
        }

        String finalCommand = command[0] + " " + command[1].substring(1, command[1].length()-3);

        return finalCommand;
    }

    public static void help(){
        System.out.println("==========\nInput\t\t\t\tOutcome\n'help()/?'\t\t\tPrints this menu again.\n'quit()'\t\t\tCloses the program\n'takeOff(flightID)'\tPuts the flight given into the take off queue. eg. 'takeOff(\"flight-100\");'\n'land(flightID)'\tPuts the flight given into the land queue. eg. 'land(\"flight-220\");'\n'next()'\t\t\tLands the plane at the start of the landing queue, if there are no planes left to land, it will let the plane take off at the start of the take off queue.\n-----\nCommands require a semi-colon at the end, and brackets, even if there are no arguments.\n==========");
    }

    public static void main(String[] args) {

        LinkedList<String> landing = new LinkedList<>();
        LinkedList<String> takingOff = new LinkedList<>();

        boolean run = true;
        help();
        while(run){
            String command = getUserCommand();

            if(command.equals("quit")) {
                run = false;
                break;
            } else if (command.equals("help")) {
                help();
            } else if (command.equals("next")) {
                System.out.println("sorry link, I can do next, come back when you're a little ... mmmmmmmm. richer");
            }

        }

        System.out.println("Quiting...");

    }

}
