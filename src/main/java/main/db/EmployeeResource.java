package main.db;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/employee-from-db")
public class EmployeeResource {

    @Inject
    DatabaseService databaseService;

    @GET
    public List<Employee> getAllEmployeesFromDb() {
        return databaseService.getAllEmployeesFromDb();
    }
}
