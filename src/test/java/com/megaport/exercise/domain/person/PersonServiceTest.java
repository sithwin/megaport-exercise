package com.megaport.exercise.domain.person;

import com.megaport.exercise.domain.person.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_SPLITTER;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
  private PersonService personService;
  private List<String> nameList = new ArrayList<>();

  @BeforeEach
  void setup() {
    personService = new PersonService();

    nameList = Arrays.asList(
        "BAKER, THEODORE",
        "SMITH, ANDREW",
        "KENT, MADISON",
        "SMITH, FREDRICK");
  }

  @Nested
  class ConvertToObjectTest {
    @Test
    void shouldReturnPersonObject_whenNameListIsValid() {
      // When
      List<Person> result = personService.convertToObject(nameList, DEFAULT_SPLITTER);

      // Then
      Assertions.assertEquals(4, result.size());
    }
  }

  @Nested
  class OrderByFirstNameAndLastNameTest {
    @Test
    void shouldReturnPersonObject_whenNameListIsValid() {
      // Given
      List<Person> personList = new ArrayList<>();

      Person person1 = new Person();
      person1.setFirstName("THEODORE");
      person1.setLastName("BAKER");

      Person person2 = new Person();
      person2.setFirstName("ANDREW");
      person2.setLastName("SMITH");

      Person person3 = new Person();
      person3.setFirstName("MADISON");
      person3.setLastName("KENT");

      Person person4 = new Person();
      person4.setFirstName("FREDRICK");
      person4.setLastName("SMITH");

      personList.add(person1);
      personList.add(person2);
      personList.add(person3);
      personList.add(person4);

      // When
      List<Person> result = personService.orderByFirstNameAndLastName(personList);

      // Then
      Assertions.assertEquals(4, result.size());
      Assertions.assertEquals("BAKER", result.get(0).getLastName());
      Assertions.assertEquals("THEODORE", result.get(0).getFirstName());
      Assertions.assertEquals("KENT", result.get(1).getLastName());
      Assertions.assertEquals("MADISON", result.get(1).getFirstName());
      Assertions.assertEquals("SMITH", result.get(2).getLastName());
      Assertions.assertEquals("ANDREW", result.get(2).getFirstName());
      Assertions.assertEquals("SMITH", result.get(3).getLastName());
      Assertions.assertEquals("FREDRICK", result.get(3).getFirstName());
    }
  }
}