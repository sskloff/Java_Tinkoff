package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearchTask extends RecursiveTask<List<String>> {

    private final Path directoryPath;
    private final Predicate<Path> predicate;

    public FileSearchTask(Path path, Predicate<Path> predicate) {
        this.directoryPath = path;
        this.predicate = predicate;
    }

    @Override
    protected List<String> compute() {
        List<String> answer = new ArrayList<>();
        List<FileSearchTask> directories = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directoryPath)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    if (predicate.test(path)) {
                        answer.add(path.toString());
                    }
                } else if (Files.isDirectory(path)) {
                    FileSearchTask directory = new FileSearchTask(path, predicate);
                    directory.fork();
                    directories.add(directory);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        for (FileSearchTask directory : directories) {
            answer.addAll(directory.join());
        }
        return answer;
    }
}
