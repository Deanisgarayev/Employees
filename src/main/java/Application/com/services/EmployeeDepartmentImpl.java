package Application.com.services;

import Application.com.interfaces.EmployeeDepartment;
import Application.com.interfaces.EmployeeService;
import Application.com.skypro.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeDepartmentImpl implements EmployeeDepartment {
    private final EmployeeService employeeService;

    public EmployeeDepartmentImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public int findTotalSumByDepartment(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .mapToInt(employee -> employee.getSalary())
                .sum();




//                .orElseThrow(() -> new IllegalArgumentException("there's not employee in the department"));
    }


    @Override
    public EmployeeBook findEmployeeWithMaxSalary(Integer departmentID) {
       return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .max(Comparator.comparingInt(EmployeeBook::getSalary))
                .orElseThrow(() -> new IllegalArgumentException("there's not employee in the department"));
    }


    @Override
    public EmployeeBook findEmployeeWithMinSalary(Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(),departmentID))
                .min(Comparator.comparingInt(EmployeeBook :: getSalary))
                .orElseThrow(() -> new IllegalArgumentException("there's not employee in the department"));
    }

    @Override
    public Collection<EmployeeBook> findAllEmployeesByDepartment (Integer departmentID) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> Objects.equals(employee.getDepartmentID(), departmentID))
                .collect(Collectors.toList());
    }


    @Override
    public  Map<Integer, List<EmployeeBook>> findAll() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(EmployeeBook::getDepartmentID));
    }
}

