package edu.project2;

public final class Maze {
    private final int width;
    private final int height;
    private final Cell[][] grid;

    public Maze(int width, int height, Cell[][] grid) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }
        this.width = width;
        this.height = height;
        this.grid = grid;
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public Cell[][] grid() {
        return grid;
    }
}
