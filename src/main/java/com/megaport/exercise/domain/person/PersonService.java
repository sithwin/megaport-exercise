package com.megaport.exercise.domain.person;

import com.megaport.exercise.domain.person.model.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {
  public List<Person> orderByFirstNameAndLastName(List<Person> personList) {
    return personList.stream()
        .sorted(Comparator
            .comparing(Person::getLastName)
            .thenComparing(Person::getFirstName))
        .collect(Collectors.toList());
  }

  public List<Person> convertToObject(List<String> data, String splitter) {
    return data.stream()
        .map( s -> {
          Person person = new Person();
          person.setLastName(s.split(splitter)[0]);
          person.setFirstName(s.split(splitter)[1]);
          return person;
        }).collect(Collectors.toList());
  }
}
