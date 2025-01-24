package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Compass {
    private int directionIndex;
    //initialize
    //protected int step;
    private int row;
    private int col;

    private static final int[][] DIRECTIONS = {
        {-1,0},
        {0,1},
        {1,0},
        {0,-1}
    };

    public Compass(int initialRow, int initialCol, int initialDirectionIndex) {
        this.row = initialRow;
        this.col = initialCol;
        this.directionIndex = initialDirectionIndex;
    }

    public int[] getCurrentPosition() {
        return new int[]{row, col};
    }

    public int[] getCurrentDirection() {
        return DIRECTIONS[directionIndex];
    }

    // Move forward in the current direction
    public void moveForward() {
        row += DIRECTIONS[directionIndex][0];
        col += DIRECTIONS[directionIndex][1];
    }

    // Turn right (clockwise)
    public void turnRight() {
        directionIndex = (directionIndex + 1) % 4;
    }

    // Turn left (counterclockwise)
    public void turnLeft() {
        directionIndex = (directionIndex + 3) % 4; // Same as -1 modulo 4
    }

}