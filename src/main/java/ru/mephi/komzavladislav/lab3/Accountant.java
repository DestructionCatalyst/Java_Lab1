package ru.mephi.komzavladislav.lab3;

import java.util.List;

public class Accountant {

    public static void main(String[] args) {
        Accountant accountant = new Accountant();
        List<Employee> employees = Employee.createShortList();

        System.out.println("Выплата премии женщинам сотрудникам");
        accountant.payPremiumWomen(employees);
        System.out.println("Выплата зарплаты сотрудникам определенного департамента");
        accountant.paySpbDepartment(employees);
        System.out.println("Выплата премии сотрудникам старше 30, работающим в определенном департаменте");
        accountant.payPremiumExperiencedInMainDepartment(employees);
        System.out.println("Выплата зарплаты менеджерам");
        accountant.payManagers(employees);
        System.out.println("Выплата премии стаффу");
        accountant.payPremiumStaff(employees);
    }

    public void paySalary(Employee employee){
        System.out.println("Выплачена зарплата работни" + employee.getGender().getEnding() + " " + employee);
    }

    public void payPremium(Employee employee){
        System.out.println("Выплачена премия в размере " + employee.getRole().premiumRate +
                " ставки работни" + employee.getGender().getEnding() + " " + employee);
    }

    public void payPremiumWomen(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getGender() == Gender.FEMALE)
                .forEach(this::payPremium);
    }

    public void payMainDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Главный"))
                .forEach(this::paySalary);
    }

    public void paySpbDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Санкт-Петербургский филиал"))
                .forEach(this::paySalary);
    }

    public void payPremiumExperiencedInMainDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Главный"))
                .filter(p -> p.getAge() > 30)
                .forEach(this::payPremium);
    }

    public void payPremiumExperiencedInSpbDepartment(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getDept().equals("Санкт-Петербургский филиал"))
                .filter(p -> p.getAge() > 30)
                .forEach(this::payPremium);
    }

    public void payManagers(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getRole() == Role.MANAGER)
                .forEach(this::paySalary);
    }

    public void payPremiumStaff(List<Employee> employees){
        employees.stream()
                .filter(p -> p.getRole() == Role.STAFF)
                .forEach(this::payPremium);
    }
}
