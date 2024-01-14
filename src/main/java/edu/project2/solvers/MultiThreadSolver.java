package edu.project2.solvers;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.generators.GeneratorDFS;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultiThreadSolver implements Solver {
    private final int width;
    private final int height;
    private final Cell[][] grid;
    private final boolean[][] visited;
    private final ForkJoinPool pool = ForkJoinPool.commonPool();
    private static final ArrayList<Coordinate> PATH = new ArrayList<>();

    public MultiThreadSolver(Maze maze) {
        this.width = maze.width();
        this.height = maze.height();
        this.grid = maze.grid();
        visited = new boolean[maze.height()][maze.width()];
    }

    @Override
    public Stack<Coordinate> solve(Coordinate start, Coordinate end) {
        Stack<Coordinate> stack = new Stack<>();
        if (start.col() >= width
            || start.row() >= height
            || end.col() >= width
            || end.row() >= width
        ) {
            throw new IllegalArgumentException();
        }
        stack.push(end);
        pool.invoke(new TaskDFS(start, end));
        stack.addAll(PATH);
        return SolverBFS.reverseStack(stack);
    }

    public class TaskDFS extends RecursiveTask<Boolean> {

        private final Coordinate current;
        private final Coordinate end;

        public TaskDFS(Coordinate current, Coordinate end) {
            this.current = current;
            this.end = end;
        }

        @Override
        protected Boolean compute() {
            ArrayList<Coordinate> coordinates = new ArrayList<>();
            coordinates.add(current);
            visited[current.row()][current.col()] = true;
            if (current.equals(end)) {
                return true;
            }
            List<TaskDFS> tasks = new ArrayList<>();


            List<int[]> unvisitedDirections = GeneratorDFS.getUnvisitedDirections(
                width, height, current, visited
            );
            List<int[]> directions = new ArrayList<>();
            if (unvisitedDirections != null) {
                directions = SolverDFS.getAvailableDirections(current, unvisitedDirections, grid);
            }


            for (int[] direction : directions) {
                TaskDFS task = new TaskDFS(
                    new Coordinate(current.row() + direction[0], current.col() + direction[1]),
                    end
                );
                task.fork();
                tasks.add(task);
            }
            for (TaskDFS task : tasks) {
                if (task.join()) {
                    PATH.addAll(coordinates);
                    return true;
                }
            }
            return false;
        }
    }
}
