package edu.project2;

import edu.project2.generators.GeneratorDFS;
import edu.project2.solvers.SolverBFS;
import edu.project2.solvers.SolverDFS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Stack;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    public static Maze prepareMaze() {
        Cell[][] grid = new Cell[3][3];
        grid[0][0] = Cell.fabricCellCreator(false, false, true, false);
        grid[0][1] = Cell.fabricCellCreator(false, false, true, true);
        grid[0][2] = Cell.fabricCellCreator(true, false, false, true);
        grid[1][0] = Cell.fabricCellCreator(true, false, true, false);
        grid[1][1] = Cell.fabricCellCreator(false, false, true, true);
        grid[1][2] = Cell.fabricCellCreator(false, true, false, true);
        grid[2][0] = Cell.fabricCellCreator(false, true, true, false);
        grid[2][1] = Cell.fabricCellCreator(false, false, true, true);
        grid[2][2] = Cell.fabricCellCreator(false, false, false, true);
        return new Maze(3, 3, grid);
    }

    String[][] prepareMazeRender() {
        String[][] preparedMazeRender = new String[7][7];
        Arrays.fill(preparedMazeRender[0], "▓▓");
        Arrays.fill(preparedMazeRender[6], "▓▓");
        preparedMazeRender[1] = new String[]{"▓▓", "░░", "░░", "░░", "░░", "░░", "▓▓"};
        preparedMazeRender[2] = new String[]{"▓▓", "▓▓", "▓▓", "▓▓", "▓▓", "░░", "▓▓"};
        preparedMazeRender[3] = new String[]{"▓▓", "░░", "░░", "░░", "░░", "░░", "▓▓"};
        preparedMazeRender[4] = new String[]{"▓▓", "░░", "▓▓", "▓▓", "▓▓", "▓▓", "▓▓"};
        preparedMazeRender[5] = new String[]{"▓▓", "░░", "░░", "░░", "░░", "░░", "▓▓"};
        return preparedMazeRender;
    }

    String[][] preparePathRender() {
        String[][] preparedPathRender = new String[7][7];
        Arrays.fill(preparedPathRender[0], "▓▓");
        Arrays.fill(preparedPathRender[6], "▓▓");
        preparedPathRender[1] = new String[]{"▓▓", "▫▫", "▷▷", "▫▫", "▷▷", "▫▫", "▓▓"};
        preparedPathRender[2] = new String[]{"▓▓", "▓▓", "▓▓", "▓▓", "▓▓", "∇∇", "▓▓"};
        preparedPathRender[3] = new String[]{"▓▓", "▫▫", "◁◁", "▫▫", "◁◁", "▫▫", "▓▓"};
        preparedPathRender[4] = new String[]{"▓▓", "∇∇", "▓▓", "▓▓", "▓▓", "▓▓", "▓▓"};
        preparedPathRender[5] = new String[]{"▓▓", "▫▫", "▷▷", "▫▫", "░░", "░░", "▓▓"};
        return preparedPathRender;
    }

    @Test
    @DisplayName("Отрисовка лабиринта")
    void prettyMazeShouldBeEqualToPreparedMazeRender() {
        //given
        Maze maze = MazeTest.prepareMaze();
        String[][] preparedMazeRender = prepareMazeRender();

        //when
        String[][] prettyMaze = Renderer.renderMaze(maze);

        //then
        assertThat(prettyMaze).isEqualTo(preparedMazeRender);
    }

    @Test
    @DisplayName("Нахождение пути через SolverDFS и его отрисовка")
    void prettyPathByDFSShouldBeEqualToPreparedPathRender() {
        //given
        Maze maze = prepareMaze();
        SolverDFS solver = new SolverDFS(maze);
        String[][] preparedRender = preparePathRender();

        //when
        Stack<Coordinate> stack = solver.solve(
            new Coordinate(0, 0), new Coordinate(2, 1)
        );
        String[][] prettyPath = Renderer.renderPath(maze, stack);

        //then
        assertThat(prettyPath).isEqualTo(preparedRender);
    }

    @Test
    @DisplayName("Нахождение пути через SolverBFS и его отрисовка")
    void prettyPathByBFSShouldBeEqualToPreparedPathRender() {
        //given
        Maze maze = prepareMaze();
        SolverBFS solver = new SolverBFS(maze);
        String[][] preparedRender = preparePathRender();

        //when
        Stack<Coordinate> stack = solver.solve(
            new Coordinate(0, 0), new Coordinate(2, 1)
        );
        String[][] prettyPath = Renderer.renderPath(maze, stack);

        //then
        assertThat(prettyPath).isEqualTo(preparedRender);
    }

    @Test
    @DisplayName("Переданы некорректные размеры лабиринта")
    void whenEnteredIncorrectMazeDimensionsThenTrowIllegalArgumentException() {
        //given
        boolean flag = false;

        //when
        try {
            GeneratorDFS generator = new GeneratorDFS(-1, 3);
        } catch (IllegalArgumentException e) {
            flag = true;
        }

        //then
        assertTrue(flag);
    }

    @Test
    @DisplayName("Переданы некорректные координаты для решения лабиринта")
    void whenEnteredIncorrectStartOrEndCoordinatesThenTrowIllegalArgumentException() {
        //given
        boolean flag = false;
        Maze maze = prepareMaze();
        SolverDFS solver = new SolverDFS(maze);

        //when
        try {
            solver.solve(new Coordinate(4, 4), new Coordinate(1, 1));
        } catch (IllegalArgumentException e) {
            flag = true;
        }

        //then
        assertTrue(flag);
    }
}
