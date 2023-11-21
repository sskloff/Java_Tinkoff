package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloneFile {

    private CloneFile() {
    }

    public static void cloneFile(Path path) {
        String fileName = path.getFileName().toString();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        int copiesCount = 1;
        while (true) {
            String cloneName;
            if (copiesCount == 1) {
                cloneName = baseName + " — копия" + extension;
            } else {
                cloneName = baseName + " — копия (" + copiesCount + ")" + extension;
            }
            Path clonePath = path.getParent().resolve(cloneName);
            if (!Files.exists(clonePath)) {
                try {
                    Files.copy(path, clonePath);
                    break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            copiesCount++;
        }
    }
}
