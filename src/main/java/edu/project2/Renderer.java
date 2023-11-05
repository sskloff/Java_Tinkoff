package edu.project2;

import java.util.Arrays;
import java.util.Stack;

public class Renderer {

    private Renderer() {
    }

    public static String[][] renderMaze(Maze maze) {
        String[][] prettyMaze = new String[maze.height() * 2 + 1][maze.width() * 2 + 1];
        Arrays.fill(prettyMaze[0], "▓▓");
        for (int j = 1; j < prettyMaze.length; j++) {
            prettyMaze[j][0] = "▓▓";
        }
        for (int i = 0; i < maze.height(); i++) {
            for (int j = 0; j < maze.width(); j++) {
                prettyMaze[i * 2 + 1][j * 2 + 1] = "░░";
                prettyMaze[i * 2 + 2][j * 2 + 2] = "▓▓";
                if (maze.grid()[i][j].getRight()) {
                    prettyMaze[i * 2 + 1][j * 2 + 2] = "░░";
                } else {
                    prettyMaze[i * 2 + 1][j * 2 + 2] = "▓▓";
                }
                if (maze.grid()[i][j].getBottom()) {
                    prettyMaze[i * 2 + 2][j * 2 + 1] = "░░";
                } else {
                    prettyMaze[i * 2 + 2][j * 2 + 1] = "▓▓";
                }
            }
        }
        return prettyMaze;
    }

    public static String[][] renderPath(Maze maze, Stack<Coordinate> stack) {
        String[][] prettyMaze = renderMaze(maze);
        while (!stack.empty()) {
            Coordinate current = stack.pop();
            prettyMaze[current.row() * 2 + 1][current.col() * 2 + 1] = "▫▫";
            if (!stack.empty()) {
                Coordinate next = stack.peek();
                if (next.row() > current.row()) {
                    prettyMaze[current.row() * 2 + 2][current.col() * 2 + 1] = "∆∆";
                }
                if (next.row() < current.row()) {
                    prettyMaze[current.row() * 2][current.col() * 2 + 1] = "∇∇";
                }
                if (next.col() > current.col()) {
                    prettyMaze[current.row() * 2 + 1][current.col() * 2 + 2] = "◁◁";
                }
                if (next.col() < current.col()) {
                    prettyMaze[current.row() * 2 + 1][current.col() * 2] = "▷▷";
                }
            }
        }
        return prettyMaze;
    }
}
