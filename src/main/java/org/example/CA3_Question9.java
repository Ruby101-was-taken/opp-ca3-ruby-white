package org.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 *  Name:
 *  Class Group:
 */

/*
Direction enum used to indicate direction.
 */
enum DIRECTION {NORTH, SOUTH,EAST,WEST};

public class CA3_Question9
{
    public static void display(int[][] image)
    {
        for (int y = 0; y < image.length; y++)
        {
            for (int x = 0; x < image[0].length; x++)
            {
                System.out.print(((image[y][x] != 1) ? ((image[y][x] != 2) ? "□" : "x") : "■") + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
         int w = 8, h = 8;
         int wH = (int)w/2, hH = (int)h/2;

        int[][] maze = new int[h][w];

        for(int y=0; y<h; y++){
            for (int x = 0; x < w; x++) {
                maze[y][x] = 1;
            }
        }

        Random rand = new Random();


        int startX = 1, endX = w-1;
        int startY = hH, endY = hH+2;

        int paths = 4;
        for (int i = 0; i < paths; i++) {

//            System.out.println("\n=====\nLINE: " + i + "\n=====\n");

            for (int x = startX; x < endX; x++) {
                maze[startY-1][x] = 0;
//                System.out.println("\nhor (" + startX + ", " + (startY-1) + ") (" + endX + ", " + (startY-1) + ")");
//                display(maze);
            }


            startY = rand.nextInt(2, h-1);
            if(startY > endX){
                int temp = startY;
                startY = endY;
                endY = temp;
            }

            for (int y = startY; y < endY; y++) {
                maze[y][endX-1] = 0;
//                System.out.println("\nvert (" + (endX-1) + ", " + startY + ") (" + (endX-1) + ", " + endY + ")");
//                display(maze);
            }

            startX = rand.nextInt(2, w-1);
            if(startX > endX){
                int temp = startX;
                startX = endX;
                endX = temp;
            }


        }


        for (int x = startX; x < w; x++) {
            maze[startY-1][x] = 0;
//            System.out.println("\nhor (" + startX + ", " + (startY-1) + ") (" + endX + ", " + (startY-1) + ")");
//            display(maze);
        }



        display(maze);

        System.out.println("\n======\n");


        Deque<MazePoint> points = new ArrayDeque<MazePoint>();


        points.push(new MazePoint(1, hH, DIRECTION.NORTH));

        boolean solved = false;

        /*
        key:
        0 = nothing
        1 = wall
        2 = correct path
        3 = incorrect path
         */

        while(!solved){
            MazePoint point = points.pop();

            boolean pointFound = false;

            if(point.y<h-1){
                if(maze[point.y+1][point.x] == 0){
                    points.push(new MazePoint(point.x, point.y+1, DIRECTION.SOUTH));
                    if(point.y+1 == h-1) {
                        solved = true;
                        maze[h-1][point.x] = 2;
                    }
                    pointFound = true;
                }
            }
            if(point.y>0){
                if(maze[point.y-1][point.x] == 0){
                    points.push(new MazePoint(point.x, point.y-1, DIRECTION.NORTH));
                    if(point.y-1 == 0) {
                        solved = true;
                        maze[0][point.x] = 2;
                    }
                    pointFound = true;
                }
            }
            if(point.x<w-1){
                if(maze[point.y][point.x+1] == 0){
                    points.push(new MazePoint(point.x+1, point.y, DIRECTION.EAST));
                    if(point.x+1 == w-1) {
                        solved = true;
                        maze[point.y][w-1] = 2;
                    }
                    pointFound = true;
                }
            }
            if(point.x>0){
                if(maze[point.y][point.x-1] == 0){
                    points.push(new MazePoint(point.x-1, point.y, DIRECTION.WEST));
                    if(point.x-1 == 0) {
                        solved = true;
                        maze[point.y][0] = 2;
                    }
                    pointFound = true;
                }
            }

            maze[point.y][point.x] = 2;

            if(!pointFound){
                DIRECTION reverse = point.lastDir;
                while(maze[point.y][point.x]==2){
                    switch (reverse){
                        case NORTH:
                            maze[point.y][point.x] = 3;
                            point = new MazePoint(point.x-1, point.y, DIRECTION.NORTH);
                            break;
                        case SOUTH:
                            maze[point.y][point.x] = 3;
                            point = new MazePoint(point.x+1, point.y, DIRECTION.SOUTH);
                            break;
                        case EAST:
                            maze[point.y][point.x] = 3;
                            point = new MazePoint(point.x, point.y+1, DIRECTION.EAST);
                            break;
                        case WEST:
                            maze[point.y][point.x] = 3;
                            point = new MazePoint(point.x, point.y-1, DIRECTION.WEST);
                            break;
                    }
                }
            }
        }


        display(maze);
    }
}


class MazePoint extends Point{
    public DIRECTION lastDir;

    public MazePoint(int x, int y, DIRECTION lastDir) {
        super(x, y);
        this.lastDir = lastDir;
    }
}