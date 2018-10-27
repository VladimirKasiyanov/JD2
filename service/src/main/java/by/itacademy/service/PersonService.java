package by.itacademy.service;

import by.itacademy.dao.PersonDao;
import by.itacademy.model.Person;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonService {

    private static final PersonService INSTANCE = new PersonService();

    public Optional<Person> getPersonById(Long id) {
        return PersonDao.getInstance().getPersonById(id);
    }

    public static PersonService getInstance() {
        return INSTANCE;
    }

}
