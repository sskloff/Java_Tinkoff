package edu.hw6;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    default AbstractFilter and(AbstractFilter other) {
        return path -> this.accept(path) && other.accept(path);
    }

    static AbstractFilter globMatches(String glob) {
        return path -> {
            String regex = "\\.";
            String expectedFileExtension = glob.split(regex)[1];
            String currentFileExtension = new File(path.toString()).getName().split(regex)[1];
            return currentFileExtension.equals(expectedFileExtension);
        };
    }

    static AbstractFilter isDirectory() {
        return Files::isDirectory;
    }

    static AbstractFilter isExecutable() {
        return Files::isExecutable;
    }

    static AbstractFilter readable() {
        return Files::isReadable;
    }

    static AbstractFilter writable() {
        return Files::isWritable;
    }

    static AbstractFilter largerThan(long size) {
        return path -> Files.size(path) > size;
    }

    static AbstractFilter magicNumber(char[] bytes) {
        return path -> {
            byte[] fileBytes = Files.readAllBytes(path);
            if (bytes.length > fileBytes.length) {
                return false;
            }
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] != fileBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    static AbstractFilter regexContains(String regex) {
        return path -> Pattern.compile(regex).matcher(path.toString()).find();
    }
}
