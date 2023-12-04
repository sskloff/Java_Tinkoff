package edu.project2;

import edu.project2.generators.GeneratorDFS;
import edu.project2.solvers.Solver;
import edu.project2.solvers.SolverBFS;
import edu.project2.solvers.SolverDFS;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private Main() {
    }

    static Scanner scanner = new Scanner(System.in);
    static int size;

    @SuppressWarnings({"RegexpSinglelineJava", "MagicNumber"})
    public static void main(String[] args) {
        System.out.println("Введите сторону лабиринта от 1 до 10 включительно");
        do {
            size = scanner.nextInt();
        } while (size < 1 || size > 10);
        GeneratorDFS generator = new GeneratorDFS(size, size);
        Maze maze = generator.generateMaze();
        Solver solverDFS = new SolverDFS(maze);
        Solver solverBFS = new SolverBFS(maze);
        Coordinate start;
        Coordinate end;

        while (true) {
            System.out.println("Введите координаты начальной точки");
            start = inputCoordinate();
            System.out.println("Введите координаты конечной точки");
            end = inputCoordinate();
            if (start.row() != end.row() || start.col() != end.col()) {
                break;
            } else {
                System.out.println("Начальная и конечная точки должны отличаться");
            }
        }

        //when
        System.out.println("\n");
        Stack<Coordinate> stack = solverBFS.solve(start, end);
        String[][] prettyPath = Renderer.renderPath(maze, stack);
        for (String[] s : prettyPath) {
            for (String s1 : s) {
                System.out.print(s1);
            }
            System.out.println();
        }

        //when
        System.out.println("\n");
        stack = solverDFS.solve(start, end);
        prettyPath = Renderer.renderPath(maze, stack);
        for (String[] s : prettyPath) {
            for (String s1 : s) {
                System.out.print(s1);
            }
            System.out.println();
        }

        scanner.close();
    }

    private static Coordinate inputCoordinate() {
        int x;
        int y;
        do {
            x = scanner.nextInt();
            y = scanner.nextInt();
        } while (x < 0 || y < 0 || x >= size || y >= size);
        return new Coordinate(x, y);
    }
}
