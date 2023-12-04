package edu.project2.solvers;

import edu.project2.Coordinate;
import java.util.Stack;

public interface Solver {
    Stack<Coordinate> solve(Coordinate start, Coordinate end);
}
