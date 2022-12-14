package com.duykypaul.painthouse.common;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Log4j2
public class FileUtils {

    private FileUtils() {
        throw new IllegalStateException("FileUtils class");
    }

    public static String readSqlFile(String filename) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(filename)).getFile());
        String content = "";
        try {
            content = Files.readString(Paths.get(file.getPath()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return content;
    }
}
