package design.mode.filter.impl;

import design.mode.filter.Criteria;
import design.mode.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream()
                .filter(p -> "FEMALE".equalsIgnoreCase(p.getGender()))
                .collect(Collectors.toList());
    }
}
