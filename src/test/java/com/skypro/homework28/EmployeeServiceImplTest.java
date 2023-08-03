package com.skypro.homework28;

import Application.com.exceptions.EmployeeAlreadyAddedException;
import Application.com.exceptions.EmployeeNotFoundException;
import Application.com.exceptions.InvalidInputException;
import Application.com.interfaces.EmployeeService;
import Application.com.services.EmployeeServiceImpl;
import Application.com.skypro.EmployeeBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImplTest {
    private EmployeeService out = new EmployeeServiceImpl();

@Test
    public void shouldBeEqualWhenWeAddEmployee() {
        EmployeeBook result = out.add ("Jackson", "Stone", 5555, 1);
        EmployeeBook employee = new EmployeeBook("Jackson", "Stone", 5555, 1);
        Assertions.assertEquals(result, employee);
    }
@Test
    public void shouldThrowExceptionWhenWeAddEmployeeThatWeHave() {
    EmployeeBook result = out.add ("Jackson", "Stone", 5555, 1);
    Assertions.assertThrows(EmployeeAlreadyAddedException.class,
            ()->out.add("Jackson", "Stone", 5555, 1)
    );
    }
    @Test
    public void shouldBeEqualWhenWeAddAndRemove() {
        EmployeeBook date = out.add("Katy", "Parry", 5000, 1);
        EmployeeBook result =out.remove("Katy", "Parry", 5000, 1);
        Assertions.assertEquals(result,date);
    }

@Test
    public void shouldThrowExceptionWhenWeTryToRemoveEmployeeThatWeDoNotHave() {
        EmployeeBook result = out.add("Simon", "Parry", 5000, 1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()-> out.remove("Katy", "Parry", 5000, 1)
        );
    }

    @Test
    public void shouldFindEmployeeWhenWeHave() {
    EmployeeBook result = out.add("Katy", "Parry", 5000, 1);
    EmployeeBook resultCopy = out.find("Katy", "Parry", 5000, 1);
    Assertions.assertEquals(result, resultCopy);
    }

    @Test
    public void shouldThrowExceptionWhenWeTryToFindEmployeeThatWeDoNotHave() {
        EmployeeBook result = out.add("Simon", "Parry", 5000, 1);
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->out.find("Katy", "Parry", 5000, 1)
        );
    }

    @Test
    public void shouldBeEqualsTwoListsWhenWeFindAll() {
        List<EmployeeBook> result = new ArrayList<>(List.of(
                out.add("Lex", "Luther", 6666, 1),
                out.add("Peter", "Skripen", 6000, 2),
                out.add("Ruby", "Parker", 5000, 2),
                out.add("Roy", "Gaines", 7000, 1)
                )
        );
        List<EmployeeBook> findAll = new ArrayList<>(out.findAll());
        Assertions.assertIterableEquals(result, findAll);
    }

    @Test
    public void shouldThrowExceptionWhenWeAddNotByAlphabet() {
        Assertions.assertThrows(InvalidInputException.class,
                ()-> out.add("Katy1", "Parry", 5000, 1)
        );
    }
}
