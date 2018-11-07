package com.kasiyanov.dao;

import com.kasiyanov.exception.DaoException;
import com.kasiyanov.model.Buyer;
import com.kasiyanov.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyerDao {
    private static final BuyerDao INSTANCE = new BuyerDao();
    private static final String GET_BY_ID = "SELECT id, street, house, apartment FROM store.buyer WHERE id = ?";

    public Optional<Buyer> getBuyerById(Long id) {
        Buyer buyer = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                buyer = Buyer.builder()
                        .id(resultSet.getLong("id"))
                        .street(resultSet.getString("street"))
                        .house(resultSet.getInt("house"))
                        .apartment(resultSet.getInt("apartment"))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return Optional.ofNullable(buyer);
    }

    public static BuyerDao getInstance() {
        return INSTANCE;
    }
}
