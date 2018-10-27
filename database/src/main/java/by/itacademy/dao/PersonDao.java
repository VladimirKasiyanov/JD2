package by.itacademy.dao;

import by.itacademy.exception.DaoException;
import by.itacademy.model.Person;
import by.itacademy.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonDao {
    private static final PersonDao INSTANCE = new PersonDao();
    private static final String GET_BY_ID = "SELECT id, name FROM example.person WHERE id = ?";

    public Optional<Person> getPersonById(Long id) {
        Person person = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = Person.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(person);
    }

    public static PersonDao getInstance() {
        return INSTANCE;
    }
}
