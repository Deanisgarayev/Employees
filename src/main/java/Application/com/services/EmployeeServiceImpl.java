package Application.com.services;

import Application.com.exceptions.EmployeeAlreadyAddedException;
import Application.com.exceptions.EmployeeNotFoundException;
import Application.com.exceptions.InvalidInputException;
import Application.com.interfaces.EmployeeService;
import Application.com.model.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, EmployeeBook> employeeBooks;

    public EmployeeServiceImpl() {
        this.employeeBooks = new HashMap<>();
    }

    //     adds employee
    @Override
    public EmployeeBook add(String firstname, String surname, Integer salary, Integer departmentID) {
        validate(firstname, surname);
        EmployeeBook employeeBook = new EmployeeBook(firstname, surname, salary, departmentID);
        if (employeeBooks.containsKey(employeeBook.getFullName())) {
            throw new EmployeeAlreadyAddedException("EmployeeAlreadyExists");
        }
        employeeBooks.put(employeeBook.getFullName(), employeeBook);
        return employeeBook;
    }

    //        deletes employee
    @Override
    public EmployeeBook remove(String firstname, String surname, Integer salary, Integer departmentID) {
        validate(firstname, surname);
        EmployeeBook employeeBook = new EmployeeBook(firstname, surname, salary, departmentID);
        if (employeeBooks.containsKey(employeeBook.getFullName())) {
            return employeeBooks.remove(employeeBook.getFullName());
        }
        throw new EmployeeNotFoundException("EmployeeIsNotFound");
    }

    //        gets employee
    @Override
    public EmployeeBook find(String firstname, String surname, Integer salary, Integer departmentID) {
        validate(firstname, surname);
        EmployeeBook employeeBook = new EmployeeBook(firstname, surname, salary, departmentID);
        if (employeeBooks.containsKey(employeeBook.getFullName())) {
            return employeeBooks.get(employeeBook.getFullName());
        }
        throw new EmployeeNotFoundException("EmployeeIsNotFound");
    }

    //        gets all employees
    @Override
    public Collection<EmployeeBook> findAll() {
        return Collections.unmodifiableCollection(employeeBooks.values());
    }

    //        validates the spelling by alphabet
    private void validate(String firstname, String surname) {
        if (!(isAlpha(firstname) && isAlpha(surname))) {
            throw new InvalidInputException("you can write only by alphabet");
        }
    }

}

