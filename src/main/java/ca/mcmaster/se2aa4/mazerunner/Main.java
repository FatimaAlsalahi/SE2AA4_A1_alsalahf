package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("** Starting Maze Runner");
        Options options = new Options();
        options.addOption("i", "input", true, "Maze file to read");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);
            String inputFile = cmd.getOptionValue("i");

            if (inputFile == null) {
                logger.error("No input file specified.");
                return;
            }

            logger.info("**** Reading the maze from file " + inputFile);
            Maze maze = new Maze();
            maze.loadFromFile(inputFile);  // Load the maze

            maze.printMaze();  // Print the maze for debugging

            logger.info("**** Computing path");
            PathFinder pathFinder = new PathFinder();
            String path = pathFinder.findPath(maze);  // Get the path using the PathFinder

            logger.info("Computed Path: " + path);  // Output the path

        } catch(Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
            e.printStackTrace();
        }
        logger.info("** End of MazeRunner");
    }
}
