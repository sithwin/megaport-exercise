package com.megaport.exercise.application;

import com.megaport.exercise.domain.person.PersonService;
import com.megaport.exercise.domain.person.model.Person;
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
class SortingHandlerTest {
  private SortingHandler sortingHandler;
  @Mock
  private PersonService personServiceMock;

  @BeforeEach
  void setup() {
    sortingHandler = new SortingHandler(personServiceMock);
  }

  @Test
  void shouldReturnTrue_whenRawListIsValid()  {
    // Given
    List<String> rawList = new ArrayList<>();
    rawList.add("name1");
    List<Person> personList = new ArrayList<>();
    Person person1 = new Person();
    person1.setFirstName("THEODORE");
    person1.setLastName("BAKER");
    personList.add(person1);

    Mockito.when(personServiceMock.convertToObject(anyList(), anyString()))
        .thenReturn(personList);

    Mockito.when(personServiceMock.orderByFirstNameAndLastName(anyList()))
        .thenReturn(personList);

    // When
    List<String> result = sortingHandler.sort(rawList, DEFAULT_SORT_ORDER);

    // Then
    Assertions.assertEquals(1, result.size());

    // Verify
    Mockito.verify(personServiceMock, Mockito.times(1))
        .convertToObject(anyList(), anyString());
    Mockito.verify(personServiceMock, Mockito.times(1))
        .orderByFirstNameAndLastName(anyList());
  }

  @Test
  void shouldReturnEmptyList_whenSortOrderIsInvalid()  {
    // Given
    List<String> rawList = new ArrayList<>();

    // When
    List<String> result = sortingHandler.sort(rawList, "INVALID");

    // Then
    Assertions.assertEquals(0, result.size());
  }
}