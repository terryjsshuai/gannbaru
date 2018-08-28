package design.mode.filter.impl;

import design.mode.filter.Criteria;
import design.mode.filter.Person;

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