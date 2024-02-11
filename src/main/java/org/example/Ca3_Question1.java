package org.example;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.*;

/**
 *  Name: Ruby White :3
 *  Class Group: GD2B
 */

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Ca3_Question1 {
    public static void main(String[] args) {
        Deque<Integer> driveWay = new ArrayDeque<>();
        Deque<Integer> street = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            addCar(driveWay);
        }



        Scanner key = new Scanner(System.in);
        boolean running = true;
        while(running) {
            printBoth(driveWay, street);
            System.out.print("Enter the plate of the car you would like to add/remove (negative number to remove): ");
            int carToRemove = key.nextInt();

            if(carToRemove<0) {
                carToRemove = Math.abs(carToRemove);
                boolean run = true;
                int step = 1;
                while (run) {
                    System.out.println("\nStep " + step + ":");
                    if (removeCar(driveWay, street, carToRemove)) {
                        run = false;
                    }
                    printBoth(driveWay, street);
                    step++;
                }
                for (int car : street) {
                    System.out.println("\nStep " + step + ":");
                    removeCar(street, driveWay);
                    printBoth(driveWay, street);
                    step++;
                }
            }
            else if(carToRemove>0){
                boolean carInDrive = false;
                for(int car : driveWay){
                    if(car == carToRemove){
                        carInDrive = true;
                    }
                }
                if(!carInDrive)
                    driveWay.push(carToRemove);
                else
                    System.out.println("Car already in drive, cannot add it again!");
            }
            else{
                running = false;
            }
        }
    }

    public static void addCar(Deque<Integer> to){
        if(to.peek() != null)
            to.push(to.peek()+1);
        else
            to.push(1);
    }

    public static void printStreet(Deque<Integer> street){
        System.out.print("\n");
        for(int car : street){
            System.out.print("----");
        }
        System.out.print("\n");
        for(int car : street){
            if(car<10)
                System.out.print(" 0" + car + " ");
            else
                System.out.print(" " + car + " ");
        }
        System.out.print("\n");
        for(int car : street){
            System.out.print("----");
        }
        System.out.println("\n");
    }
    public static void printDriveWay(Deque<Integer> driveWay){
        for (int car : driveWay){
            System.out.println("|\t" + car + "\t|");
        }
    }

    public  static void printBoth(Deque<Integer> driveWay, Deque<Integer> street){
        printStreet(street);
        printDriveWay(driveWay);
    }

    public static void removeCar(Deque<Integer> from, Deque<Integer> to){
        to.push(from.peek());
        from.pop();
    }
    public static boolean removeCar(Deque<Integer> from, Deque<Integer> to, int specificCar){
        if(from.peek() != specificCar) {
            to.push(from.peek());
            from.pop();
            return false;
        }
        else{
            from.pop();
            return true;
        }
    }
}