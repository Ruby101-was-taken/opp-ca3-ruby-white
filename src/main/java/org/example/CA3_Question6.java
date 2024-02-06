package org.example;

import java.util.*;

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
            else if(command.equals("sell"))
            {
                int qty = in.nextInt();
                double price = in.nextDouble();


                double profit = 0;
                int sold = 0;
                while(sold < qty){
                    if(stocks.getFirst().quantity >= (qty-sold)){
                        profit += (price-stocks.getFirst().price)*(qty-sold);
                        stocks.getFirst().quantity-=(sold-qty);
                        if(stocks.getFirst().quantity==0)
                            stocks.removeFirst();

                        sold = qty;
                    }
                    else{
                        sold += stocks.getFirst().quantity;
                        profit += (price-stocks.getFirst().price)*stocks.getFirst().quantity;
                        stocks.removeFirst();
                    }
                }
                System.out.println("The gain is " + profit);

            }
        }while(!command.equalsIgnoreCase("quit"));
    }
}


class Block{
    public int quantity;
    public double price;

    public Block(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }
}
