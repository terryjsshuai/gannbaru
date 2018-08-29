package design.pattern.filter.impl;

import design.pattern.filter.Criteria;
import design.pattern.filter.Person;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        return persons.stream()
                .filter(f -> "MALE".equalsIgnoreCase(f.getGender()))
                .collect(Collectors.toList());
    }
}