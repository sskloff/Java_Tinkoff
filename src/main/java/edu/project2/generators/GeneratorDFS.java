package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GeneratorDFS implements Generator {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final Boolean[][] visited;
    public static final int[][] DIRECTIONS = {
        {-1, 0}, // вверх
        {0, -1}, // влево
        {0, 1}, // вправо
        {1, 0} // вниз
    };
    public final Random random = new Random();

    public GeneratorDFS(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.grid = new Cell[height][width];
        this.visited = new Boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell();
                visited[i][j] = false;
            }
        }
    }

    @Override
    public Maze generateMaze() {
        Stack<Coordinate> stack = new Stack<>();
        stack.push(new Coordinate(0, 0));
        while (!stack.empty()) {
            Coordinate current = stack.pop();
            visited[current.row()][current.col()] = true;
            List<int[]> directions = getUnvisitedDirections(width, height, current, visited);
            if (directions != null) {
                stack.push(current);
                int[] randomDirection = directions.get(random.nextInt(directions.size()));
                Coordinate next = new Coordinate(
                    current.row() + randomDirection[0],
                    current.col() + randomDirection[1]
                );
                breakWall(current, next, randomDirection);
                stack.push(next);
            }
        }
        return new Maze(width, height, grid);
    }

    public static List<int[]> getUnvisitedDirections(
        int width, int height, Coordinate current, Boolean[][]visited
    ) {
        List<int[]> availableDirections = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            int row = current.row() + direction[0];
            int col = current.col() + direction[1];
            if (row >= 0 && col >= 0 && row < width && col < height && !visited[row][col]) {
                availableDirections.add(direction);
            }
        }
        if (availableDirections.isEmpty()) {
            return null;
        }
        return availableDirections;
    }

    public void breakWall(Coordinate current, Coordinate next, int[] direction) {
        if (Arrays.equals(direction, new int[] {-1, 0})) {
            grid[current.row()][current.col()].setTop(true);
            grid[next.row()][next.col()].setBottom(true);
        } else if (Arrays.equals(direction, new int[] {0, -1})) {
            grid[current.row()][current.col()].setLeft(true);
            grid[next.row()][next.col()].setRight(true);
        } else if (Arrays.equals(direction, new int[] {0, 1})) {
            grid[current.row()][current.col()].setRight(true);
            grid[next.row()][next.col()].setLeft(true);
        } else if (Arrays.equals(direction, new int[] {1, 0})) {
            grid[current.row()][current.col()].setBottom(true);
            grid[next.row()][next.col()].setTop(true);
        }
    }
}
