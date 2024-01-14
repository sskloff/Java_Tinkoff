package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearchTask extends RecursiveTask<List<String>> {

    private final Path directoryPath;
    private static final int MIN_FILES_IN_DIRECTORY = 10;
    // Заменил 1000 на 10, чтобы не создавать много файлов в тестах

    public DirectorySearchTask(Path path) {
        this.directoryPath = path;
    }

    @Override
    protected List<String> compute() {
        List<String> answer = new ArrayList<>();
        List<DirectorySearchTask> directories = new ArrayList<>();
        int counter = 0;
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(directoryPath)) {
            for (Path path : paths) {
                if (Files.isRegularFile(path)) {
                    counter++;
                } else if (Files.isDirectory(path)) {
                    DirectorySearchTask newDirectory = new DirectorySearchTask(path);
                    newDirectory.fork();
                    directories.add(newDirectory);
                }
            }
            if (counter > MIN_FILES_IN_DIRECTORY) {
                answer.add(directoryPath.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        for (DirectorySearchTask directory : directories) {
            answer.addAll(directory.join());
        }
        return answer;
    }
}
