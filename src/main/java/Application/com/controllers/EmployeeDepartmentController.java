package Application.com.controllers;

import Application.com.interfaces.EmployeeDepartment;
import Application.com.skypro.EmployeeBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employeeDepartment")

public class EmployeeDepartmentController {
    private final EmployeeDepartment employeeDepartment;
@Autowired
    public EmployeeDepartmentController(EmployeeDepartment employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }
    @GetMapping("/sum")
    public int findTotalSumByDepartment(@RequestParam("departmentID")Integer departmentID) {
        return employeeDepartment.findTotalSumByDepartment(departmentID);
    }

    @GetMapping("/min-salary")
    public EmployeeBook findEmployeeWithMinSalary(@RequestParam("departmentID") Integer departmentID) {
        return employeeDepartment.findEmployeeWithMinSalary( departmentID);
    }
    @GetMapping("/max-salary")
    public EmployeeBook findEmployeeWithMaxSalary(@RequestParam("departmentID") Integer departmentID) {
        return employeeDepartment.findEmployeeWithMaxSalary(departmentID);
    }
    @GetMapping(value = "/all", params = "departmentID")
    public Collection <EmployeeBook> findAllEmployeesByDepartment (@RequestParam("departmentID") Integer departmentID) {
        return employeeDepartment.findAllEmployeesByDepartment (departmentID);
    }
    @GetMapping("/all")
    public Map<Integer, List<EmployeeBook>> findAll() {
        return employeeDepartment.findAll();
    }
}

