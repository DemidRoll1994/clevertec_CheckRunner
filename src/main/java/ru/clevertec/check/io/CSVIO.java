package main.java.ru.clevertec.check.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVIO {
    public List<String> getLines(String pathToFile)  {
        try (var lineStream = Files.lines(Paths.get(pathToFile))) {
            return lineStream.collect(Collectors.toList());
        } catch (IOException ignored) {
            return new ArrayList() ;
        }
    }


    public boolean writeFile(String pathToFile, String linesToWrite) {
        try {
            Files.write(Paths.get(pathToFile), linesToWrite.getBytes());
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}


