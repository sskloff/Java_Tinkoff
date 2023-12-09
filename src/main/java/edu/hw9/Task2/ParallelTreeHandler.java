package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class ParallelTreeHandler {

    private ParallelTreeHandler() {
    }

    public static List<String> findDirectoriesWithMoreThan1000Files(Path path) {
        List<String> answer;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            answer = pool.invoke(new DirectorySearchTask(path));
        }
        return answer;
    }

    public static List<String> findFilesByPredicate(Path path, Predicate<Path> predicate) {
        List<String> answer;
        try (ForkJoinPool pool = new ForkJoinPool()) {
            answer = pool.invoke(new FileSearchTask(path, predicate));
        }
        return answer;
    }

}
