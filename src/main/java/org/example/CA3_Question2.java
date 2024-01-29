package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;

public class CA3_Question2 {

    public static void main(String[] args) {
        //:3

        Random randint = new Random();

        Scanner key = new Scanner(System.in);

        Deque<Point> fillPoints = new ArrayDeque<Point>();

        int pointOne, pointTwo;

        System.out.print("Enter the x point on the canvas: ");
        pointOne = key.nextInt();
        System.out.print("Enter the y point on the canvas: ");
        pointTwo = key.nextInt();
        pointOne = (pointOne >= 0) ? pointOne : 0;
        pointTwo = (pointTwo >= 0) ? pointTwo : 0;

        int canvasW = (pointOne < 10) ? 10 : pointOne+1, canvasH = (pointTwo < 10) ? 10 : pointTwo+1;


        int canvas[][] = new int[canvasW][canvasH];

        int wallChance = 25; //chance for spot to become a "wall"

        for(int[] y : canvas){ //for loop to set a default value for each point
            for(int x=0; x<y.length; x++){
                int chance = randint.nextInt(0, 100);
                y[x] = (chance <= wallChance) ? -1 : 0;
            }
        }

        canvas[pointTwo][pointOne] = 0;

        printCanvas(canvas);
        System.out.println();

        fillPoints.push(new Point(pointOne,pointTwo));

        int fillStep = 1;

        while(fillPoints.size() > 0){
            Point point = fillPoints.pop();

            if(canvas[point.x][point.y] == 0) {
                if (point.x < canvasW - 1) {
                    if (canvas[point.x + 1][point.y] == 0) {
                        fillPoints.push(new Point(point.x + 1, point.y));
                    }
                }

                if (point.x > 0) {
                    if (canvas[point.x - 1][point.y] == 0) {
                        fillPoints.push(new Point(point.x - 1, point.y));
                    }
                }

                if (point.y < canvasW - 1) {
                    if (canvas[point.x][point.y + 1] == 0) {
                        fillPoints.push(new Point(point.x, point.y + 1));
                    }
                }

                if (point.y > 0) {
                    if (canvas[point.x][point.y - 1] == 0) {
                        fillPoints.push(new Point(point.x, point.y - 1));
                    }
                }
                canvas[point.x][point.y] = fillStep;
                fillStep++;
            }
        }
        System.out.println("Canavs Int Values:");
        printCanvasAsInt(canvas);

        System.out.println("\nCanvas:");
        printCanvas(canvas);
    }


    public static void printCanvas(int[][] canvas){
        for(int[] y : canvas){ //for loop to set print each
            for(int x : y){
                System.out.print(((x>=0) ? ((x==0) ? " " : "□" ):"■" )+ "\t");
            }
            System.out.println();
        }
    }
    public static void printCanvasAsInt(int[][] canvas){
        for(int[] y : canvas){ //for loop to set print each
            for(int x : y){
                System.out.print(x + "\t");
            }
            System.out.println();
        }
    }
}


class Point{
    public  int x;
    public  int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
