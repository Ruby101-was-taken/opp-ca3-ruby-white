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
        System.out.print("Enter command: ");
        String enteredText = input.nextLine();

        enteredText = enteredText.replace(" ", ""); //make a single line
        String[] command = enteredText.split("\\("); //will not work without \\ since java does not realise it's a string, it's so silly :P
        command[0] = command[0].toLowerCase();

        if (command.length != 2){
            if(enteredText.toLowerCase().equals("?"))
                return "help";
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
            else if(enteredText.toLowerCase().equals("help();")){
                return "help";
            }
            else if(enteredText.toLowerCase().equals("show();")){
                return "show";
            }
            incorrectFormattingQuotes();
            return "error";
        }


        String finalCommand = command[0] + " " + command[1].substring(1, command[1].length()-3);

        return finalCommand;
    }

    public static void help(){
        System.out.println("==========\nInput\t\t\t\tOutcome\n'help()/?'\t\t\tPrints this menu again.\n'show()/?'\t\t\tShows both queues.\n'quit()'\t\t\tCloses the program\n'takeOff(flightID)'\tPuts the flight given into the take off queue. eg. 'takeOff(\"flight-100\");'\n'land(flightID)'\tPuts the flight given into the land queue. eg. 'land(\"flight-220\");'\n'next()'\t\t\tLands the plane at the start of the landing queue, if there are no planes left to land, it will let the plane take off at the start of the take off queue.\n-----\nCommands require a semi-colon at the end, and brackets, even if there are no arguments.\n==========");
    }

    public static void main(String[] args) {

        LinkedList<String> landing = new LinkedList<>();
        LinkedList<String> takingOff = new LinkedList<>();

        boolean run = true;
        help();
        while(run){
            String command = getUserCommand();

            boolean validCommand = false,  error = false;

            System.out.println("=====");

            if(command.equals("quit")) {
                run = false;
                break;
            }
            else if(command.equals("help")) {
                help();
                validCommand = true;
            }
            else if(command.equals("show")) {
                System.out.println("Landing:\tStart ->" + landing + "<- Ending");
                System.out.println("Taking Off:\tStart ->" + takingOff + "<- Ending");
                validCommand = true;
            }
            else if(command.equals("next")) {

                if(landing.size()>0){
                    System.out.println(landing.getFirst() + " is landing.");
                    landing.removeFirst();
                }
                else if(takingOff.size()>0){
                    System.out.println(takingOff.getFirst() + " is taking off.");
                    takingOff.removeFirst();
                }
                else
                    System.out.println("There are no planes in any queues.\nTo check viewed planes, use the 'show()' command.");

                validCommand = true;
            }
            String[] commandParts = command.split(" ");

            if (commandParts[0].equals("takeoff")) {
                if(takingOff.contains(commandParts[1]) || landing.contains(commandParts[1])){
                    System.out.println("Plane");
                }
                takingOff.addLast(commandParts[1]);
                validCommand = true;
            }
            else if(commandParts[0].equals("land")) {
                landing.addLast(commandParts[1]);
                validCommand = true;
            }

            System.out.println("");
            error = command.equals("error");

            if(!error)
                System.out.println((validCommand) ? "Valid Command.\n=====" : "Unkwown Command.\nType ? or help(); for a list of commands.\n=====");

        }

        System.out.println("Quiting...");

    }

}
