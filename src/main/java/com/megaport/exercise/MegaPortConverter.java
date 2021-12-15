package com.megaport.exercise;

import com.megaport.exercise.application.FileOperation;
import com.megaport.exercise.application.SortingHandler;

import java.io.IOException;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_FILE_LOCATION;

public class MegaPortConverter {
  private FileOperation fileOperation;
  private SortingHandler sortingHandler;

  public MegaPortConverter(FileOperation fileOperation, SortingHandler sortingHandler) {
    this.fileOperation = fileOperation;
    this.sortingHandler = sortingHandler;
  }

  public boolean sortAndGenerateNewFile(String inputFile, String sortCriteria) throws IOException {
    List<String> rawList = fileOperation.readFile(DEFAULT_FILE_LOCATION + inputFile);
    List<String> sortedList = sortingHandler.sort(rawList, sortCriteria);

    if (sortedList.isEmpty())
      return false;

    return fileOperation.writeFile(DEFAULT_FILE_LOCATION + inputFile, sortedList);
  }
}
