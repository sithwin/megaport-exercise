package com.megaport.exercise;

import com.megaport.exercise.application.FileOperation;
import com.megaport.exercise.application.SortingHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_SORT_ORDER;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class MegaPortConverterTest {
  private MegaPortConverter megaPortConverter;

  @Mock
  FileOperation fileOperationMock;
  @Mock
  SortingHandler sortingHandlerMock;

  @BeforeEach
  void setup() {
    megaPortConverter = new MegaPortConverter(fileOperationMock, sortingHandlerMock);
  }

  @Test
  void shouldReturnTrue_whenRawListIsValid() throws IOException {
    // Given
    String fileName = "name.txt";
    List<String> rawList = new ArrayList<>();
    rawList.add("name1");

    Mockito.when(fileOperationMock.readFile(anyString()))
        .thenReturn(rawList);

    Mockito.when(sortingHandlerMock.sort(anyList(), anyString()))
        .thenReturn(rawList);

    Mockito.when(fileOperationMock.writeFile(anyString(), anyList()))
        .thenReturn(true);

    // When
    boolean result = megaPortConverter.sortAndGenerateNewFile(fileName, DEFAULT_SORT_ORDER);

    // Then
    Assertions.assertTrue(result);

    // Verify
    Mockito.verify(fileOperationMock, Mockito.times(1))
        .readFile(anyString());
    Mockito.verify(sortingHandlerMock, Mockito.times(1))
        .sort(anyList(), anyString());
    Mockito.verify(fileOperationMock, Mockito.times(1))
        .writeFile(anyString(), anyList());
  }

  @Test
  void shouldReturnFalse_whenRawListIsEmpty() throws IOException {
    // Given
    String fileName = "name.txt";
    List<String> rawList = new ArrayList<>();

    Mockito.when(fileOperationMock.readFile(anyString()))
        .thenReturn(rawList);

    Mockito.when(sortingHandlerMock.sort(anyList(), anyString()))
        .thenReturn(rawList);

    // When
    boolean result = megaPortConverter.sortAndGenerateNewFile(fileName, DEFAULT_SORT_ORDER);

    // Then
    Assertions.assertFalse(result);

    // Verify
    Mockito.verify(fileOperationMock, Mockito.times(1))
        .readFile(anyString());
    Mockito.verify(sortingHandlerMock, Mockito.times(1))
        .sort(anyList(), anyString());
    Mockito.verify(fileOperationMock, Mockito.times(0))
        .writeFile(anyString(), anyList());
  }
}