package com.megaport.exercise.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.SORTED_FILE_SUFFIX;

public class FileOperation {
  public List<String> readFile(String filePath) throws IOException {
  List<String> nameList = new ArrayList<>();
  Path path = Paths.get(filePath);
    BufferedReader reader = Files.newBufferedReader(path);

    while (true) {
      String line = reader.readLine();
      if (line == null) {
        break;
      }
      nameList.add(line);
    }
    return nameList;
  }

  public boolean writeFile(String filePath, List<String> data) throws IOException {
    Path path = Paths.get(filePath);
    Path createdPath = Files.write(path, data);

    return createdPath == path;
  }

  public String generateSortedFileName(String rawFileName) {
    return rawFileName.replace(".", SORTED_FILE_SUFFIX + ".");
  }
}
