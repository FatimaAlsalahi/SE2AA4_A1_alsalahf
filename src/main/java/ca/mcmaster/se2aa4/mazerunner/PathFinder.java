package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class PathFinder {

    private static final Logger logger = LogManager.getLogger(PathFinder.class);

    private String pathOutput;

    public String findPath(Maze maze) {

        StringBuilder path = new StringBuilder();
        int[] entryPoint = findEntryPoint(maze);
        if (entryPoint == null) {
            return path.toString(); // No valid entry point found
        }

        Compass compass = new Compass(entryPoint[0], entryPoint[1], 1); // Initial direction: East (right)

        while (true) {
            int[] currentPos = compass.getCurrentPosition();
            int row = currentPos[0];
            int col = currentPos[1];


            // Exit condition: when we reach the last column
            if (col == maze.getWidth() - 1) {
                path.append("E");  // Mark the exit
                break;
            }

            // Move forward if the next cell is passable
            if (maze.isPassable(row, col + 1)) { //check if moving right is possible
                compass.moveForward();
                path.append("F"); 
                
            } else {
                // If forward is blocked, try turning right and check if forward is possible
                compass.turnRight();
                path.append("R");
               
                // Check if we can move forward after turning right
                if (maze.isPassable(row + compass.getCurrentDirection()[0], col + compass.getCurrentDirection()[1])) {
                    compass.moveForward();
                    path.append("F");
                    
                } else {
                    // If still blocked, try turning left
                    compass.turnLeft();
                    path.append("L");
                    

                    // Check if we can move after turning left
                    if (maze.isPassable(row + compass.getCurrentDirection()[0], col + compass.getCurrentDirection()[1])) {
                        compass.moveForward();
                        path.append("F");
                        
                    } else {
                        logger.warn("Stuck at position (" + row + ", " + col + ") - Unable to proceed.");
                        break; //Exit
                    }
                }
            }
        }

        
        return path.toString();  // Return the path as a string (like "FFFFFRFF")
    }

    // Existing method to find the entry point
    public int[] findEntryPoint(Maze maze) {
        logger.info("Searching for entry point in the leftmost column.");

        // Search for a passable cell in the leftmost column (col == 0)
        for (int row = 0; row < maze.getHeight(); row++) {
            
            if (maze.isPassable(row, 0)) {  // Look for the leftmost passable cell
               
                return new int[]{row, 0};  // Found entry at (row, 0)
            }
        }

        logger.warn("No entry point found in the leftmost column.");
        return null;  // No entry point found
    }
}
