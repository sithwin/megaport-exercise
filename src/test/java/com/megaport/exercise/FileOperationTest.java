package com.megaport.exercise;

import com.megaport.exercise.application.FileOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_FILE_LOCATION;

class FileOperationTest {
  private FileOperation fileOperation;

  @BeforeEach
  void setup() {
    fileOperation = new FileOperation();
  }

  @Nested
  class ReadFileTest{
    @Test
    void shouldReturnException_whenFileNotFound() {
      Assertions.assertThrows(IOException.class, () -> fileOperation.readFile("Invalid_File"));
    }

    @Test
    void shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
      // Given
      String emptyFile = "src/test/resources/test-data/empty.txt";

      // When
      List<String> result = fileOperation.readFile(emptyFile);

      // Then
      Assertions.assertNotNull(result);
      Assertions.assertEquals(Collections.emptyList(), result);
    }

    @Test
    void shouldReturnStringList_whenFileIsNotEmpty() throws IOException {
      // Given
      String emptyFile = "src/test/resources/test-data/name_list.txt";

      // When
      List<String> result = fileOperation.readFile(emptyFile);

      // Then
      Assertions.assertNotNull(result);
      Assertions.assertEquals(4, result.size());
    }
  }

  @Nested
  class GenerateSortedFileNameTest {
    @Test
    void shouldGenerateFileNameWithSorted_whenFileNameIsValid() {
      // Given
      String fileName = "name.txt";

      // When
      String result = fileOperation.generateSortedFileName(fileName);

      // Then
      Assertions.assertEquals(result, "name-sorted.txt");
    }
  }

  @Nested
  class WriteFileTest {
    private List<String> nameList = new ArrayList<>();

    @BeforeEach
    void setup() {
      nameList = Arrays.asList(
          "BAKER, THEODORE",
          "SMITH, ANDREW",
          "KENT, MADISON",
          "SMITH, FREDRICK");
    }

    @Test
    void shouldCreatedFile_whenFileNameIsValid() throws IOException {
      // Given
      String fileName = "name-sorted.txt";

      // When
      boolean result = fileOperation.writeFile(DEFAULT_FILE_LOCATION + fileName, nameList);

      // Then
      Assertions.assertTrue(result);
    }

    @Test
    void shouldReturnException_whenInvalidFileName() {
      Assertions.assertThrows(IOException.class, () -> fileOperation.writeFile("", nameList));
    }
  }
}