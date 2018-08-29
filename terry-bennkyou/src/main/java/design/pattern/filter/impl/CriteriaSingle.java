package design.pattern.filter.impl;

import design.pattern.filter.Criteria;
import design.pattern.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaSingle implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {

        return persons.stream()
                .filter(p -> "SINGLE".equalsIgnoreCase(p.getMaritalStatus()))
                .collect(Collectors.toList());
    }
}