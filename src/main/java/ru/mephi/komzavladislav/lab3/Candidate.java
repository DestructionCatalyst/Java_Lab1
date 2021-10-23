package ru.mephi.komzavladislav.lab3;

import java.util.ArrayList;
import java.util.List;

public class Candidate {
    private String givenName;
    private String surName;
    private int age;
    private Gender gender;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int code;

    private Candidate(){

    }

    public Employee getHired(Role role, String department){
        return new Employee.Builder()
                .setGivenName(givenName)
                .setSurName(surName)
                .setAge(age)
                .setGender(gender)
                .setPhone(phone)
                .setAddress(address)
                .setCity(city)
                .setCode(code)
                .setState(state)
                .setRole(role)
                .setDept(department)
                .build();
    }


    static class Builder{
        private String givenName = "";
        private String surName = "";
        private int age = 18;
        private Gender gender = Gender.MALE;
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
    
        public Candidate build(){
            Candidate candidate = new Candidate();
    
            candidate.address = address;
            candidate.age = age;
            candidate.city = city;
            candidate.code = code;
            candidate.gender = gender;
            candidate.givenName = givenName;
            candidate.phone = phone;
            candidate.state = state;
            candidate.surName = surName;
    
            return candidate;
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
                ", " + age + " лет" +
                ", " + gender +
                ", телефон: " + phone +
                ", адрес: " + state +
                " (" + code +
                "), " + city +
                ", " + address;
    }

    public static List<Candidate> createShortList() {
        List<Candidate> shortList = new ArrayList<>();

        shortList.add(new Builder()
                .setGivenName("Николай")
                .setSurName("Гончаров")
                .setAge(31)
                .setGender(Gender.MALE)
                .setPhone("+78005553500")
                .setAddress("Кутузовский пр., д. 20")
                .setCity("Москва")
                .setState("Москва")
                .setCode(77)
                .build());

        shortList.add(new Builder()
                .setGivenName("Анастасия")
                .setSurName("Петрова")
                .setAge(29)
                .setGender(Gender.MALE)
                .setPhone("+78005553536")
                .setAddress("Московская ул., д. 20")
                .setCity("Пушкин")
                .setState("Ленинградская область")
                .setCode(77)
                .build());

        return shortList;
    }
}

