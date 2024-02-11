package org.example;

import java.util.*;

/**
 *  Name: Ruby White :3
 *  Class Group: GD2B
 */

public class CA3_Question6
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

        LinkedList<Block> stocks = new LinkedList<>();


        do {
            System.out.print(">");
            command = in.next();
            if(command.equalsIgnoreCase("buy"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();

                stocks.addLast(new Block(qty, price));

            }
            else if(command.equalsIgnoreCase("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();

                if(stocks.size()!=0) {
                    LinkedList<Block> stocksBackUp = cloneArray(stocks);
                    double profit = 0;
                    int sold = 0;
                    while (sold < qty) {
                        if (stocks.getFirst().quantity >= (qty - sold)) {
                            profit += (price - stocks.getFirst().price) * (qty - sold);
                            stocks.getFirst().quantity += (sold - qty); //It is plus because it works that way, I could make it work with minus, or I could leave a comment saying that it doesn't, you can tell which option I have chosen, or some may even say that this is the path that i have chosen to walk on, so yes, the plus path is what I will walk on, and you cannot stop me hahahahaha. Sorry for the long comment but it was very nessecary so I could explain how I was feeling on the 08/02/2024, man it's 2024, I can't believe it, feels like 2020 was yesterday, but it also feel so long ago at the same time, isn't it crazy how life is like that. So much has happened since then, in just a measly 4 years. Woaw, anyways this comment is long enough, so I'll see you later
                            if (stocks.getFirst().quantity == 0)
                                stocks.removeFirst();

                            sold = qty;
                        } else {
                            sold += stocks.getFirst().quantity;
                            profit += (price - stocks.getFirst().price) * stocks.getFirst().quantity;

                            stocks.removeFirst();

                            if(stocks.size()==0){
                                stocks = cloneArray(stocksBackUp);
                                qty = 0;
                            }
                        }
                    }
                    System.out.println((qty!=0)? ("The gain is " + profit) : ("Cannot sell since you do not have enough stocks..."));
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



class Block{
    public int quantity;
    public double price;

    public String company;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Block(String company, int quantity, double price){
        this.company = company;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Block{" +
                "quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
