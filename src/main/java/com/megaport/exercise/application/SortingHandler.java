package com.megaport.exercise.application;

import com.megaport.exercise.domain.person.PersonService;
import com.megaport.exercise.domain.person.model.Person;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_SORT_ORDER;
import static com.megaport.exercise.utli.MegaPortConstants.DEFAULT_SPLITTER;

public class SortingHandler {
  private PersonService personService;

  public SortingHandler(PersonService personService) {
    this.personService = personService;
  }

  public List<String> sort(List<String> rawList, String sortCriteria) {
    if (!sortCriteria.equals(DEFAULT_SORT_ORDER))
      return Collections.emptyList();

    List<Person> personList = personService.convertToObject(rawList, DEFAULT_SPLITTER);
    List<Person> sortedPersonList =  personService.orderByFirstNameAndLastName(personList);

    return sortedPersonList.stream()
        .map(person -> person.getLastName() + DEFAULT_SPLITTER + person.getFirstName())
        .collect(Collectors.toList());
  }
}
