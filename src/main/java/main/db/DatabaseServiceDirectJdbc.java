package main.db;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Prerequisite - We must have `employees` table in DB with 3 columns (id, name, age) in order to execute this.
 * If jooq is enabled, this will be taken care of automatically.
 * <p>
 * This class is not using any ORM, I am directly using the JDBC API.
 * It is not ideal, but this is just a demo to show direct usage of JDBC.
 */
@ApplicationScoped
public class DatabaseServiceDirectJdbc {

    AgroalDataSource dataSource;

    @Inject
    public DatabaseServiceDirectJdbc(AgroalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Employee> getAllEmployeesDirectlyUsingJdbc() {
        List<Employee> result = new ArrayList<>();
        String query = "SELECT * FROM employees";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                result.add(new Employee(resultSet.getInt("id"),
                                        resultSet.getString("name"),
                                        resultSet.getInt("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
