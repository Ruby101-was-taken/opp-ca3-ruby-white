package org.example;

import java.util.*;

public class CA3_Question7
{

    /*
    Will repeatedly ask the user to enter the commands in the format
    buy qty price
    or
    sell qty price
    or
    quit
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command="";

        HashMap<String, LinkedList<Block>> stocks = new HashMap<>();

        //LinkedList<Block> stocks = new LinkedList<>();


        do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                String company = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();
                if(!stocks.containsKey(company)){
                    stocks.put(company, new LinkedList<>());
                }
                stocks.get(company).addLast(new Block(company, qty, price));
                //stocks.addLast(new Block(company, qty, price));

            }
            else if(command.equalsIgnoreCase("sell"))
            {
                String company = in.next();
                int qty = in.nextInt();
                double price = in.nextDouble();

                if(stocks.containsKey(company)) {
                    if (stocks.get(company).size() != 0) {
                        LinkedList<Block> stocksBackUp = cloneArray(stocks.get(company));
                        double profit = 0;
                        int sold = 0;
                        while (sold < qty) {
                            if (stocks.get(company).getFirst().quantity >= (qty - sold)) {
                                profit += (price - stocks.get(company).getFirst().price) * (qty - sold);
                                stocks.get(company).getFirst().quantity += (sold - qty);
                                if (stocks.get(company).getFirst().quantity == 0)
                                    stocks.get(company).removeFirst();

                                sold = qty;
                            } else {
                                sold += stocks.get(company).getFirst().quantity;
                                profit += (price - stocks.get(company).getFirst().price) * stocks.get(company).getFirst().quantity;
                                stocks.get(company).removeFirst();

                                if (stocks.size() == 0) {
                                    stocks.replace(company, cloneArray(stocksBackUp));
                                    qty = 0;
                                }
                            }
                        }
                        System.out.println((qty != 0) ? ("The gain is " + profit) : ("Cannot sell since you do not have enough stocks..."));
                    }
                }
                else System.out.println("Cannot sell with no stocks.");
            }
            else if(!command.equalsIgnoreCase("quit")){
                System.out.println("Invalid command!");
            }
        }while(!command.equalsIgnoreCase("quit"));
    }


    public static LinkedList<Block> cloneArray(Collection<Block> arrayToClone){
        LinkedList<Block> clone = new LinkedList<Block>();

        for (Block block : arrayToClone) {
            clone.push(block);
        }

        return clone;
    }
}



// Block class used from question 6 :3

