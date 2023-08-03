package Application.com.interfaces;

import Application.com.skypro.EmployeeBook;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeDepartment {
        int findTotalSumByDepartment(Integer departmentID);

        EmployeeBook findEmployeeWithMinSalary (Integer departmentID);

        EmployeeBook findEmployeeWithMaxSalary (Integer departmentID);


        Collection<EmployeeBook>  findAllEmployeesByDepartment (Integer departmentID);

        Map<Integer, List<EmployeeBook>> findAll();

}
