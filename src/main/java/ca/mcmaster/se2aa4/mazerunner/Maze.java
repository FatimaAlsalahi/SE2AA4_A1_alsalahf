package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {


    private List<List<Character>> mazeData;


    //change this
    public void loadFromFile(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        mazeData = new ArrayList<>();
        while ((line = reader.readLine()) != null) {

            line = line.trim();

            List<Character> row = new ArrayList<>();
            if (line.isEmpty()) {
                for (int i = 0; i < getWidth(); i++) {
                    row.add(' ');
                }
            }
            else {
                for (char c : line.toCharArray()) {
                row.add(c);
                }
            }
            mazeData.add(row);
        }
        reader.close();
    }

    public void printMaze() {
    System.out.println("loaded maze: ");
        for (List<Character> row : mazeData) {
            for (char c : row) {
                // Print space if it's ' ', else print '#'
                System.out.print(c == ' ' ? " " : "#");
            }
            System.out.println(); // This goes here to move to the next line after printing a row
        }
    }

    public int[] findEntryPoint(Maze maze) {
        if (maze.getHeight() == 0 || maze.getWidth() == 0) {
            return null;  // No maze data loaded
        }

        for (int row = 0; row < maze.getHeight(); row++) {
            if (maze.isPassable(row, 0)) {  // Look for the leftmost passable cell
                return new int[]{row, 0};  // Found entry at (row, 0)
            }
        }
        return null;  // No entry point found
    }

    public int getHeight() {
        return mazeData.size();
    }

    public int getWidth() {
        return mazeData.isEmpty() ? 0 : mazeData.get(0).size(); // Width of the first row
    }

    public boolean isPassable(int row, int col) {
        if (row < 0 || row >= mazeData.size() || col < 0 || col >= mazeData.get(row).size()) {
            return false; // Out of bounds
        }

        // If cell is an empty space or a blank (passable)
        return mazeData.get(row).get(col) == ' ' || mazeData.get(row).get(col) == '\t';
    }
}