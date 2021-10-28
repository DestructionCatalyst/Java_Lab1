package ru.mephi.komzavladislav.lab3;

import java.util.List;

public class Accountant {

    public static void main(String[] args) {
        Accountant accountant = new Accountant();
        List<Employee> employees = Employee.createShortList();

        System.out.println("Premium to women");
        accountant.payPremiumWomen(employees);
        System.out.println("Salary to SPB department");
        accountant.paySpbDepartment(employees);
        System.out.println("Premium to main department for workers over 30");
        accountant.payPremiumExperiencedInMainDepartment(employees);
        System.out.println("Salary to managers");
        accountant.payManagers(employees);
        System.out.println("Premium to staff");
        accountant.payPremiumStaff(employees);
    }

    private void paySalary(Employee employee){
        System.out.println("Paid salary to: " + employee);
    }

    private void payPremium(Employee employee){
        System.out.println("Paid premium equal to " + employee.getRole().premiumRate +
                " of salary to: " + employee);
    }

    public void payPremiumWomen(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getGender().equals(Gender.FEMALE))
                .forEach(this::payPremium);
    }

    public void payMainDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Main"))
                .forEach(this::paySalary);
    }

    public void paySpbDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Saint-Petersburg department"))
                .forEach(this::paySalary);
    }

    public void payPremiumExperiencedInMainDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Main"))
                .filter(p -> p.getAge() > 30)
                .forEach(this::payPremium);
    }

    public void payPremiumExperiencedInSpbDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Saint-Petersburg department"))
                .filter(p -> p.getAge() > 30)
                .forEach(this::payPremium);
    }

    public void payManagers(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getRole().equals(Role.MANAGER))
                .forEach(this::paySalary);
    }

    public void payPremiumStaff(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getRole().equals(Role.STAFF))
                .forEach(this::payPremium);
    }
}
