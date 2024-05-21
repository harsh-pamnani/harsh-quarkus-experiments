package main.db;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.temp.tables.records.EmployeesRecord;

import java.util.List;

import static org.jooq.temp.tables.Employees.EMPLOYEES;

@ApplicationScoped
public class JooqEmployeeRepository {

    @Inject
    AgroalDataSource dataSource;

    @Inject
    DSLContext dslContext;

    public List<Employee> getAllEmployees() {
        List<Employee> result = dslContext.selectFrom(EMPLOYEES)
                                          .fetch()
                                          .stream()
                                          .map(this::deserialize)
                                          .toList();

        return result;
    }

    private Employee deserialize(EmployeesRecord record) {
        return new Employee(record.getId(), record.getName(), record.getAge());
    }
}
