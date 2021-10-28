package ru.mephi.komzavladislav.lab3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class EmployeeOperations {

    public static void main(String[] args) {
        EmployeeOperations employeeOperations = new EmployeeOperations();
        List<Employee> employees = Employee.createShortList();

        employeeOperations.saveEmployeeData(employees);

        List<Employee> moscowEmployees = employees.stream()
                .filter(employee -> employee.getDept().equals("Main"))
                .collect(Collectors.toList());
        List<Employee> spbEmployees = employees.stream()
                .filter(employee -> employee.getDept().equals("Saint-Petersburg department"))
                .collect(Collectors.toList());

        System.out.println("Moscow: ");
        employeeOperations.printAddressList(moscowEmployees);
        System.out.println("Saint-Petersburg: ");
        employeeOperations.printAddressList(spbEmployees);

        employeeOperations.hireCandidate(employees, 0, "Main", Role.STAFF);
        System.out.println(employees.get(employees.size() - 1));

        employeeOperations.getSubordinates(employees);
    }

    // Uses Consumer
    public void saveEmployeeData(List<Employee> employees){
        Consumer<Employee> employeeSaver = employee -> {
            try {
                if(!Files.isDirectory(Path.of("employees"))){
                    Files.createDirectory(Path.of("employees"));
                }

                String fileName = "employees/" + employee.getGivenName() + " "  + employee.getSurName() + ".emp";
                Path employeePath = Path.of(fileName);

                if(!Files.exists(employeePath)){
                    Files.createFile(employeePath);
                }
                Files.write(employeePath, employee.toString().getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        employees.forEach(employeeSaver);
    }

    // Uses Function
    public void printAddressList(List<Employee> employees){
        Function<Employee, String> getEmployeeAddress = employee ->
                        employee.getGivenName() + " " +
                        employee.getSurName() + ": " +
                        employee.getState() + ", " +
                        employee.getCity() + ", " +
                        employee.getAddress();

        employees.stream()
                .map(getEmployeeAddress)
                .forEach(System.out::println);
    }

    // Uses Supplier
    public void hireCandidate(List<Employee> employees, int index, String dept, Role role){
        Supplier<Employee> hireSupplier = () ->
                Candidate.createShortList().get(index).getHired(role, dept);

        employees.add(hireSupplier.get());

    }

    public void getSubordinates(List<Employee> employees){
        // Managers are subordinate to all executives,
        // staff is subordinate to managers of their department
        BiPredicate<Employee, Employee> isSubordinate = (e1, e2) ->
                e1.getRole().equals(Role.MANAGER) && e2.getRole().equals(Role.EXECUTIVE) ||
                e1.getDept().equals(e2.getDept()) && e1.getRole().equals(Role.STAFF) &&
                        e2.getRole().equals(Role.MANAGER);

        employees.forEach(
                e1 -> employees.forEach(
                        e2 -> {
                            if (isSubordinate.test(e1, e2)){
                                System.out.println(e1.getGivenName() + " " + e1.getSurName() +
                                        " is subordinate to " + e2.getGivenName() + " " + e2.getSurName());
                            }
                        }
                )
        );
    }
}
