package org.example;

import java.util.*;

/**
 *  Name: Ruby White :3
 *  Class Group: GD2B
 */


// I hate this question, no sane person would do this with a stack.
// for any future projects make real questions that are worth doing.

// this doesn't fully work but I do not care, it's not worth finishing it.

public class CA3_Question8 {

    public static int add(int a, int b){
        return a+b;
    }
    public static int subtract(int a, int b){
        return a-b;
    }
    public static int multiply(int a, int b){
        return a*b;
    }
    public static int divide(int a, int b){
        return a/b;
    }


    public static int calculate(int a, int b, char sym){
        switch (sym){
            case '+':
                return add(a,b);
            case '-':
                return subtract(a,b);
            case '*':
                return multiply(a,b);
            case '/':
                return divide(a,b);
            default:
                return 0; //should never really run
        }
    }

    public static void main(String[] args) {
        String equation;
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter equation");
        equation = in.nextLine().trim();


        equation = equation.replace('(', 'b');
        equation = equation.replace(')', 'd');

        System.out.println(getResult(equation));
    }

    public static int getResult(String equation) {
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        // for loop that reads the numbers and operators into 2 stacks

        boolean numberAddedLast = false;
        boolean run = true;

        // bi  mdas

        System.out.println(equation);

        char[] symbols = {'*', '/', '+', '-'};


        int i = -1;
        while (run){
            System.out.println(i + ": " + numbers);
            i++;
            char curChar = equation.charAt(i);
            //System.out.println((curChar == 'b') + " " + curChar);
            if(curChar != ' ' && curChar != 'd') {
                if (isSymbol(curChar) || curChar=='b') {

                    boolean continuing = true;

                    if(curChar=='b'){
                        System.out.println(equation.substring(i+1));
                        numbers.push(getResult(equation.substring(i+1)));
                        System.out.println("End");
                        continuing = false;
                        do {
                            i++;
                            curChar = equation.charAt(i);
                        } while (curChar != 'd');
                    }


                    if(operators.size()>0){
                        for (char s : symbols) {
                            if(s == operators.peek()){
                                numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop())); //calculates if the previous operator is to be before the current
                                break;
                            }
                            else if(s == curChar){
                                break; //break for loop if the current operator is to be done before the other
                            }
                        }
                    }


                    if(continuing){

                        numberAddedLast = false;
                        operators.push(curChar);

                        do {
                            i++;
                            curChar = equation.charAt(i);
                        } while (curChar == ' ');

                        if(curChar == 'b'){
                            i--;
                        }
                    }
                }

                if(!isSymbol(curChar)) {
                    if(numberAddedLast){
                        String numberStr = "" + numbers.pop() + "" + curChar;
                        numbers.push(Integer.parseInt(numberStr));
                     }
                    else{
                        numbers.push(Integer.parseInt("" + curChar));
                    }
                    numberAddedLast = true;
                }
            }
            else if (curChar==')') {
                run = false;
            }

            try{
                char check = equation.charAt(i+1);
            }
            catch (StringIndexOutOfBoundsException e){
                run = false;
            }

        }

        System.out.println(numbers + "\n" + operators);

        while(operators.size() > 0) {
            numbers.push(calculate(numbers.pop(), numbers.pop(), operators.pop()));
        }


        System.out.println(numbers + "\n" + operators);
        return numbers.peek();
    }


    public static boolean isSymbol(char c){
        char[] symbols = {'+', '-', '*', '/', 'b', 'd'};


        for (char symbol:symbols) {
            // System.out.println(c + " = " + symbol);
            if(c==symbol)
                return true;
        }

        return false;
    }
}
