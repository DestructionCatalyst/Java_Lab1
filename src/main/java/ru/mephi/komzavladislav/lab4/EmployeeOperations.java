package ru.mephi.komzavladislav.lab4;

import ru.mephi.komzavladislav.lab3.Employee;
import ru.mephi.komzavladislav.lab3.Gender;
import ru.mephi.komzavladislav.lab3.Role;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class EmployeeOperations {

    public static void main(String[] args) {
        EmployeeOperations employeeOperations = new EmployeeOperations();
        List<Employee> employees = Employee.createShortList();

        System.out.println("Years till retirement:");
        employeeOperations.printYearsTillRetirement(employees);

        System.out.println();
        employeeOperations.printFirstEmployeeFromDept(employees, "Main");
        employeeOperations.printFirstEmployeeFromDept(employees, "Non-existing dept");

        System.out.println();
        employeeOperations.doesHighSalaryExist(employees, 230000);
        employeeOperations.doesHighSalaryExist(employees, 300000);

        System.out.println("\nOldest employees: ");
        employeeOperations.printOldestEmployeeFromDept(employees, "Main");
        employeeOperations.printOldestEmployeeFromDept(employees, "Saint-Petersburg department");

        System.out.println("\nEmployees with lowest salary: ");
        employeeOperations.printLowestSalaryEmployeeFromDept(employees, "Main");
        employeeOperations.printLowestSalaryEmployeeFromDept(employees, "Saint-Petersburg department");

        System.out.println();
        employeeOperations.printTotalSalaryWithPremium(employees);

        System.out.println();
        employeeOperations.printAverageEmployeeAgeWithGender(employees, Gender.MALE);
        employeeOperations.printAverageEmployeeAgeWithGender(employees, Gender.FEMALE);
    }


    private void printYearsTillRetirement(List<Employee> employees){
        Function<Employee, Integer> yearsTillRetirement =
                employee -> Math.max(0, employee.getGender().retirementAge - employee.getAge());

        Consumer<Employee> printFullNameAndColon =
                employee -> System.out.print(employee.getGivenName() + ' ' + employee.getSurName() + ": ");

        employees.stream()
                .peek(printFullNameAndColon)
                .map(yearsTillRetirement)
                .forEach(System.out::println);

    }

    private void printFirstEmployeeFromDept(List<Employee> employees, String dept){
        employees.stream()
                .filter(employee -> employee.getRole().equals(Role.EXECUTIVE) && employee.getDept().equals(dept))
                .findFirst()
                .ifPresentOrElse(System.out::println, ()-> System.out.println("No staff in " + dept));

    }

    private void doesHighSalaryExist(List<Employee> employees, int salaryThreshold){
        employees.stream()
                .map(employee -> employee.getRole().salary * (1 + employee.getRole().premiumRate))
                .filter(salary -> salary >= salaryThreshold)
                .findAny()
                .ifPresentOrElse(
                        salary -> System.out.println("There is salary higher than " + salaryThreshold),
                        () -> System.out.println("There is no salary higher than " + salaryThreshold));
    }

    private void printOldestEmployeeFromDept(List<Employee> employees, String dept){
        employees.stream()
                .filter(employee -> employee.getDept().equals(dept))
                .max(Comparator.comparingInt(Employee::getAge))
                .ifPresent(System.out::println);
    }

    private void printLowestSalaryEmployeeFromDept(List<Employee> employees, String dept){
        employees.stream()
                .filter(employee -> employee.getDept().equals(dept))
                .min(Comparator.comparingDouble(
                        employee -> employee.getRole().salary * (1 + employee.getRole().premiumRate)))
                .ifPresent(System.out::println);

    }

    private void printTotalSalaryWithPremium(List<Employee> employees){
        double totalSalary = employees.stream()
                .map(employee -> employee.getRole().salary * (1 + employee.getRole().premiumRate))
                .mapToDouble(Double::doubleValue)
                .sum();

        System.out.println("Total salary: " + String.format("%.2f", totalSalary));
    }

    private void printAverageEmployeeAgeWithGender(List<Employee> employees, Gender gender){
        employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .map(Employee::getAge)
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(avgAge -> System.out.println(
                        "Average age for " + gender.toString().toLowerCase() + "s: " + String.format("%.2f",avgAge)));

    }
}
