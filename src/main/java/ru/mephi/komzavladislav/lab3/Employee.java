package ru.mephi.komzavladislav.lab3;

import java.util.ArrayList;
import java.util.List;

/*
    Необходимо релизовать простого бота для выдачи зарплаты сотрудникам:
     class Employee с полями: givenName, surName, age, gender(enum MALE, FEMALE), role(enum STAFF, MANAGER, EXECUTIVE),
      dept, eMail, phone, address, city, state (область), code (код области);

    Реализовать паттерн builder (нельзя использовать lombok)

    Переопределить toString()

    Реализовать метод public static List<Employee> createShortList()
    - создает список сотрудников и заполняет его различными сотрдниками не менее 7 (Экземплярами класса Employee).
    Значения полей должны быть разнообразны.

    class Accountant без полей:

    public void paySalary(Employee employee); Выплату зарплаты реализовать через вывод в консоль сообщения о выплате

    public void payPremium(Employee employee); Выплату премии реализовать через вывод в консоль сообщения о выплате.
    Процент премии хранится в enum role (STAFF = 10%, MANAGER = 20%, EXECUTIVE = 30%)


    Задача: На основе написанных классов продемонстрируйте работу со стримами:
        Выплата премии женщинам сотрудникам
        Выплата зарплаты сотрудникам определенного департамента
        Выплата премии сотрудникам старше 30, работающим в определенном департаменте
        Выплата зарплаты менеджерам
        Выплата премии стаффу
 */

public class Employee {
    private String givenName;
    private String surName;
    private int age;
    private Gender gender;
    private Role role;
    private String dept;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int code;

    private Employee() {
    }

    static class Builder{
        private String givenName = "";
        private String surName = "";
        private int age = 18;
        private Gender gender = Gender.MALE;
        private Role role = Role.STAFF;
        private String dept = "";
        private String phone = "";
        private String address = "";
        private String city = "";
        private String state = "";
        private int code = 0;

        public Builder setGivenName(String givenName) {
            this.givenName = givenName;
            return this;
        }

        public Builder setSurName(String surName) {
            this.surName = surName;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setDept(String dept) {
            this.dept = dept;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setState(String state) {
            this.state = state;
            return this;
        }

        public Builder setCode(int code) {
            this.code = code;
            return this;
        }

        public Employee build(){
            Employee employee = new Employee();

            employee.address = address;
            employee.age = age;
            employee.city = city;
            employee.code = code;
            employee.dept = dept;
            employee.gender = gender;
            employee.givenName = givenName;
            employee.phone = phone;
            employee.role = role;
            employee.state = state;
            employee.surName = surName;

            return employee;
        }
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurName() {
        return surName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Role getRole() {
        return role;
    }

    public String getDept() {
        return dept;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return  givenName + ' ' +
                surName +
                ", " + age + " years old" +
                ", " + gender +
                ", position: " + role +
                ", department: " + dept +
                ", phone: " + phone +
                ", address: " + state +
                " (" + code +
                "), " + city +
                ", " + address;
    }

    public static List<Employee> createShortList(){
        List<Employee> shortList = new ArrayList<>();

        shortList.add(new Employee.Builder()
                .setGivenName("Alexander")
                .setSurName("Ivanov")
                .setAge(35)
                .setGender(Gender.MALE)
                .setRole(Role.EXECUTIVE)
                .setDept("Main")
                .setPhone("+78005553535")
                .setAddress("Leninskiy av., h. 20")
                .setCity("Moscow")
                .setState("Moscow")
                .setCode(77)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Vasily")
                .setSurName("Petrov")
                .setAge(29)
                .setGender(Gender.MALE)
                .setRole(Role.MANAGER)
                .setDept("Main")
                .setPhone("+78005553536")
                .setAddress("Vernadskogo av., h. 33")
                .setCity("Moscow")
                .setState("Moscow")
                .setCode(77)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Maria")
                .setSurName("Sidorova")
                .setAge(26)
                .setGender(Gender.FEMALE)
                .setRole(Role.MANAGER)
                .setDept("Saint-Petersburg department")
                .setPhone("+78005553537")
                .setAddress("Nevsky av., h. 20")
                .setCity("Saint-Petersburg")
                .setState("Saint-Petersburg")
                .setCode(78)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Alexey")
                .setSurName("Kuznetsov")
                .setAge(23)
                .setGender(Gender.MALE)
                .setRole(Role.STAFF)
                .setDept("Main")
                .setPhone("+78005553538")
                .setAddress("Kashirskoye hw., h. 40")
                .setCity("Moscow")
                .setState("Moscow")
                .setCode(77)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Irina")
                .setSurName("Sergeeva")
                .setAge(31)
                .setGender(Gender.FEMALE)
                .setRole(Role.STAFF)
                .setDept("Main")
                .setPhone("+78005553539")
                .setAddress("Lenina st., h.5")
                .setCity("Podolsk")
                .setState("Moscow region")
                .setCode(50)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Margarita")
                .setSurName("Alexandrova")
                .setAge(29)
                .setGender(Gender.FEMALE)
                .setRole(Role.STAFF)
                .setDept("Saint-Petersburg department")
                .setPhone("+78005553540")
                .setAddress("Moskovskiy av., h. 100")
                .setCity("Saint-Petersburg")
                .setState("Saint-Petersburg")
                .setCode(78)
                .build());

        shortList.add(new Employee.Builder()
                .setGivenName("Pavel")
                .setSurName("Nikolaev")
                .setAge(40)
                .setGender(Gender.MALE)
                .setRole(Role.STAFF)
                .setDept("Saint-Petersburg department")
                .setPhone("+78005553541")
                .setAddress("Parkovaya st., h. 20")
                .setCity("Pushkin")
                .setState("Leningrad region")
                .setCode(47)
                .build());

        return shortList;
    }
}
