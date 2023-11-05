package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.generators.GeneratorDFS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class SolverDFS implements Solver {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    public final Random random = new Random();
    Boolean[][] visited;

    public SolverDFS(Maze maze) {
        this.width = maze.width();
        this.height = maze.height();
        this.grid = maze.grid();
        visited = new Boolean[maze.height()][maze.width()];
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                visited[i][j] = false;
            }
        }
    }

    @Override
    public Stack<Coordinate> solve(Coordinate start, Coordinate end) {
        if (start.col() >= width
            || start.row() >= height
            || end.col() >= width
            || end.row() >= width
        ) {
            throw new IllegalArgumentException();
        }
        Stack<Coordinate> stack = new Stack<>();
        stack.push(new Coordinate(start.row(), start.col()));
        while (!stack.empty()) {
            Coordinate current = stack.pop();
            if (current.equals(end)) {
                stack.push(current);
                break;
            }
            visited[current.row()][current.col()] = true;
            List<int[]> unvisitedDirections = GeneratorDFS.getUnvisitedDirections(
                width, height, current, visited
            );
            List<int[]> directions = new ArrayList<>();
            if (unvisitedDirections != null) {
                directions = getAvailableDirections(current, unvisitedDirections);
            }
            if (!directions.isEmpty()) {
                stack.push(current);
                int[] randomDirection = directions.get(random.nextInt(directions.size()));
                Coordinate next = new Coordinate(
                    current.row() + randomDirection[0],
                    current.col() + randomDirection[1]
                );
                stack.push(next);
            }
        }
        return stack;
    }

    private List<int[]> getAvailableDirections(
        Coordinate current, List<int[]> unvisitedDirections
    ) {
        List<int[]> directions = new ArrayList<>();
        int row = current.row();
        int col = current.col();
        for (int[] direction : unvisitedDirections) {
            if ((Arrays.equals(direction, new int[] {-1, 0}) && grid[row][col].getTop())
                || (Arrays.equals(direction, new int[] {0, -1}) && grid[row][col].getLeft())
                || (Arrays.equals(direction, new int[] {0, 1}) && grid[row][col].getRight())
                || (Arrays.equals(direction, new int[] {1, 0}) && grid[row][col].getBottom())
            ) {
                directions.add(direction);
            }
        }
        return directions;
    }
}
