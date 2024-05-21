package main.db;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

@Path("/employee")
public class EmployeeResource {

    @Inject
    JooqEmployeeRepository jooqEmployeeRepository;

    @Inject
    DatabaseServiceDirectJdbc databaseServiceDirectJdbc;

    @GET
    public List<Employee> getAllEmployeesFromDb() {
        return jooqEmployeeRepository.getAllEmployees();
    }

    @GET
    @Path("/using-directly-jdbc")
    public List<Employee> getAllEmployeesDirectlyUsingJdbc() {
        return databaseServiceDirectJdbc.getAllEmployeesDirectlyUsingJdbc();
    }
}
