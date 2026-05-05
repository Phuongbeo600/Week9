package org.example;

import java.nio.file.Paths; // Import cái này vào

public class FilePathService {
    public String getProjectFolderPath(String root, String subFolder) {
        // REFACTOR: Thay vì viết cứng dấu gạch chéo, ta dùng Paths.get
        return Paths.get(root, subFolder).toString();
    }
}