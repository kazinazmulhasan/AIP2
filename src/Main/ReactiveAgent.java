/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import java.math.*;

/**
 *
 * @author laurawardwell
 */
public class ReactiveAgent {

    int r = 0;
    int c = 0;
    int faces = 0;
    static int prevc = 0;
    static int prevr = 0;
    static int deathByWumpus=0;
    static int wumpusKilled=0;
    static int deathByPit=0;
    static int cellsTravelled=0;
    static char[][] world = new char[][]{
        {'#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#'},
        {'#', '#', '#', '#', '#'},};

    public ReactiveAgent(int r, int c, int faces, board board1) { //constructor
        this.r = r;
        this.c = c;
        this.faces = faces;
        prevc = c;
        prevr = r;
        world[r][c] = 'A';
        move(r, c, faces);
        System.out.println("Num of time killed by wumpus: "+ deathByWumpus);

    }

    public static void move(int r, int c, int faces) {
        printboard();
        System.out.println("---------------------------");
        if (map[r][c] == 'G') {
            System.out.println("You found the gold, teleport to safety!");
        } else if (map[r][c] == 'W') {
            System.out.println("Death by Wumpus");
            deathByWumpus++;
            world[r][c] = 'W';
            respawn(r, c, faces);
        } else if (faces == 1) { //North
            int tempc = c - 1;
            if (tempc < 0) {
                System.out.println("Bump");
                int prob = (int) (Math.random());
                if (prob >= .5) {
                    faces = 4;
                }
                if (prob < .5) {
                    faces = 2;
                }
                move(r, c, faces);
            } else {
                world[r][c] = '_';
                prevc = c;
                prevr=r;
                c = tempc;
                world[r][c] = 'A';
                double prob = (Math.random());
                if (prob < .33) {
                    faces = 2;
                }
                if (prob < .67 & prob >= .33) {
                    faces = 4;
                }
                if (prob >= .67) {
                    faces = 1;
                }
                move(r, c, faces);
            }
        } else if (faces == 2) { //East
            int tempr = r + 1;
            if (tempr > world.length - 1) {
                System.out.println("Bump");
                int prob = (int) (Math.random());
                if (prob >= .5) {
                    faces = 1;
                }
                if (prob < .5) {
                    faces = 3;
                }
                move(r, c, faces);

            } else {
                world[r][c] = '_';
                prevr = r;
                prevc=c;
                r = tempr;
                world[r][c] = 'A';
                double prob = (Math.random());
                if (prob < .33) {
                    faces = 2;
                }
                if (prob < .67 & prob >= .33) {
                    faces = 1;
                }
                if (prob >= .67) {
                    faces = 3;
                }
                move(r, c, faces);
            }
        } else if (faces == 3) { //South
            int tempc = c + 1;
            if (tempc > world.length - 1) {
                System.out.println("Bump");
                int prob = (int) (Math.random());
                if (prob >= .5) {
                    faces = 2;
                }
                if (prob < .5) {
                    faces = 4;
                }
                move(r, c, faces);
            } else {
                world[r][c] = '_';
                prevc = c;
                prevr=r;
                c = tempc;
                world[r][c] = 'A';
                double prob = (Math.random());
                if (prob < .33) {
                    faces = 2;
                }
                if (prob < .67 & prob >= .33) {
                    faces = 4;
                }
                if (prob >= .67) {
                    faces = 3;
                }
                move(r, c, faces);
            }
        } else if (faces == 4) { //West
            int tempr = r - 1;
            if (tempr < 0) {
                System.out.println("Bump");
                double prob = (Math.random());
                if (prob >= .5) {
                    faces = 1;
                }
                if (prob < .5) {
                    faces = 3;
                }
                move(r, c, faces);
            } else {
                world[r][c] = '_';
                prevr = r;
                prevc=c;
                r = tempr;
                world[r][c] = 'A';
                double prob = (Math.random());
                if (prob < .33) {
                    faces = 1;
                }
                if (prob < .67 & prob >= .33) {
                    faces = 3;
                }
                if (prob >= .67) {
                    faces = 4;
                }
                move(r, c, faces);
            }
        }

    }

    public static void printboard() {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {
                System.out.print(world[i][j]);

            }
            System.out.println();
        }
        
    }

    public static void respawn(int r, int c, int faces) {
        int newr = prevr;
        int newc = prevc;
        world[newr][newc] = 'A';
        if (world[r][c] == 'W') {
            shoot(r,c,faces);
        }
        move(newr, newc, faces);

    }

    public static void shoot(int r, int c, int faces) {
        if (world[r][c] == 'W') {
            world[r][c] = '_';
            map[r][c]='_';
            System.out.println("SCREAM");
        } else if (world[r][c] == 'B') {
            System.out.println("The wumpus is still alive.");
        } else if (faces == 1) { //North
            int tempc = c - 1;
            if (tempc < 0) {
                System.out.println("The wumpus is still alive.");
            }
            shoot(r, tempc, faces);
        } else if (faces == 2) { //East
            int tempr = r + 1;
            if (tempr > world.length - 1) {
                System.out.println("The wumpus is still alive.");
            }
            shoot(tempr, c, faces);
        } else if (faces == 3) { //South
            int tempc = c + 1;
            if (tempc > world.length - 1) {
                System.out.println("The wumpus is still alive.");
            }
            shoot(r, tempc, faces);
        } else if (faces == 4) { //West
            int tempr = r - 1;
            if (tempr < 0) {
                System.out.println("The wumpus is still alive.");
            }
            shoot(tempr, c, faces);
        }
    }
}
