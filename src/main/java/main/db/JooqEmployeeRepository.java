package main.db;

import static org.jooq.temp.tables.Employee.EMPLOYEE;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.temp.tables.records.EmployeeRecord;

@ApplicationScoped
public class JooqEmployeeRepository {

    DSLContext dslContext;

    @Inject
    public JooqEmployeeRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public List<Employee> getAllEmployees() {
        return dslContext.selectFrom(EMPLOYEE).fetch().stream()
                .map(this::deserialize)
                .toList();
    }

    public Employee createEmployee(Employee employee) {
        dslContext.insertInto(EMPLOYEE).set(serialize(employee)).execute();
        return employee;
    }

    private Employee deserialize(EmployeeRecord record) {
        return new Employee(record.getId(), record.getName(), record.getAge());
    }

    private EmployeeRecord serialize(Employee employee) {
        return new EmployeeRecord(employee.id(), employee.name(), employee.age());
    }
}
