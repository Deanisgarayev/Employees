package Application.com.services;

import Application.com.interfaces.EmployeeDepartment;
import Application.com.interfaces.EmployeeService;
import Application.com.model.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeDepartmentImpl implements EmployeeDepartment {
    private final EmployeeService employeeService;

    public EmployeeDepartmentImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    gets total sum by department
    @Override
    public int findTotalSumByDepartment(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .mapToInt(employee -> employee.getSalary())
                .sum();
    }

    //    gets employee with max salary
    @Override
    public EmployeeBook findEmployeeWithMaxSalary(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .max(Comparator.comparingInt(EmployeeBook::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("there's not employee in the department"));
    }

    //    gets employee with min salary
    @Override
    public EmployeeBook findEmployeeWithMinSalary(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .min(Comparator.comparingInt(EmployeeBook::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("there's not employee in the department"));
    }

    //    gets all employees by department
    @Override
    public Collection<EmployeeBook> findAllEmployeesByDepartment(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .collect(Collectors.toList());
    }

    //    gets employees grouping by department
    @Override
    public Map<Integer, List<EmployeeBook>> findAll() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(EmployeeBook::getDepartmentID));
    }
}

