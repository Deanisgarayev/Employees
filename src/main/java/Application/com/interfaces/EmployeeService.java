package Application.com.interfaces;

import Application.com.skypro.EmployeeBook;

import java.util.Collection;

public interface EmployeeService {
        EmployeeBook add(String firstname, String surname, Integer salary, Integer departmentID);

        EmployeeBook remove(String firstname, String surname, Integer salary, Integer departmentID);

        EmployeeBook find(String firstname, String surname, Integer salary, Integer departmentID);

        Collection<EmployeeBook> findAll();

    }


