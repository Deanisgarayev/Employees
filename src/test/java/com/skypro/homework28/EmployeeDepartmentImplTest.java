package com.skypro.homework28;

import Application.com.services.EmployeeDepartmentImpl;
import Application.com.services.EmployeeServiceImpl;
import Application.com.skypro.EmployeeBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeDepartmentImplTest {
    @Mock
    EmployeeServiceImpl employeeServiceImpl;
    @InjectMocks
    EmployeeDepartmentImpl out ;

    @BeforeEach
    public void setUp() {
        when(employeeServiceImpl.findAll()).thenReturn(employeeList());
//        when(employeeServiceImpl.findAll()).thenThrow(IllegalArgumentException.class);
    }
    private List<EmployeeBook> employeeList() {
        return List.of(new EmployeeBook("Lex", "Luther", 6666, 1),
                new EmployeeBook("Roy", "Gaines", 7000, 1),
                new EmployeeBook("Ruby", "Parker", 5000, 2),
                new EmployeeBook("Peter", "Skripen", 6000, 2)
        );
    }
        private List<EmployeeBook> employeeList2 () {
            return List.of(new EmployeeBook("Lex", "Luther", 6666, 1),
                    new EmployeeBook("Roy", "Gaines", 7000, 1)

            );
    }

    @Test
    public void should() {
        int employee = out.findTotalSumByDepartment(1);
        assertEquals(13666, employee);
    }

    @Test
    public void ShouldReturnEmployeeWithMaxSalaryFromFirstDepartmentAndThrowExceptionByThirdDepartment() {
        EmployeeBook employee = out.findEmployeeWithMaxSalary( 1);
        assertEquals("Roy", employee.getFirstname());
        assertEquals("Gaines", employee.getSurname());
        assertEquals(7000, employee.getSalary());
        assertEquals(1, employee.getDepartmentID());
        assertThrows(IllegalArgumentException.class,
                ()->out.findEmployeeWithMaxSalary(3));
    }

    @Test
    public void ShouldReturnEmployeeWithMinSalaryFromSecondDepartmentAndThrowExceptionByThirdDepartment() {
        EmployeeBook employee = out.findEmployeeWithMinSalary(2);
        assertEquals("Ruby", employee.getFirstname());
        assertEquals("Parker", employee.getSurname());
        assertEquals(5000, employee.getSalary());
        assertEquals(2, employee.getDepartmentID());
        assertThrows(IllegalArgumentException.class,
                ()->out.findEmployeeWithMinSalary(3));

    }
    @Test
    public void findAllEmployeesByDepartment() {
        assertIterableEquals(employeeList2(), out.findAllEmployeesByDepartment(1));

    }
    @Test
    public void findAll() {
        assertNotNull( out.findAll());


    }

}
