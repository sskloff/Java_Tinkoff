package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.generators.GeneratorDFS;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SolverBFS implements Solver {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    boolean[][] visited;
    Coordinate[][] previousPositions;

    public SolverBFS(Maze maze) {
        this.width = maze.width();
        this.height = maze.height();
        this.grid = maze.grid();
        visited = new boolean[maze.height()][maze.width()];
        previousPositions = new Coordinate[maze.height()][maze.width()];
    }

    @Override
    public Stack<Coordinate> solve(Coordinate start, Coordinate end) {
        Queue<Coordinate> queue = new LinkedList<>();
        Stack<Coordinate> stack = new Stack<>();
        queue.add(start);
        visited[start.row()][start.col()] = true;
        while (!queue.isEmpty()) {
            Coordinate current = queue.poll();
            if (current.equals(end)) {
                Coordinate backtrack = end;
                while (!backtrack.equals(start)) {
                    stack.push(backtrack);
                    backtrack = previousPositions[backtrack.row()][backtrack.col()];
                }
                stack.push(start);
                stack = reverseStack(stack);
                break;
            }
            List<int[]> unvisitedDirections = GeneratorDFS.getUnvisitedDirections(
                width, height, current, visited
            );
            List<int[]> directions = new ArrayList<>();
            if (unvisitedDirections != null) {
                directions = SolverDFS.getAvailableDirections(current, unvisitedDirections, grid);
            }
            for (int[] direction : directions) {
                int newRow = current.row() + direction[0];
                int newCol = current.col() + direction[1];
                queue.add(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;
                previousPositions[newRow][newCol] = current;
            }
        }
        return stack;
    }

    public static Stack<Coordinate> reverseStack(Stack<Coordinate> stack) {
        Queue<Coordinate> queue = new LinkedList<>();
        while (!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        while (!queue.isEmpty()) {
            stack.add(queue.remove());
        }
        return stack;
    }
}
