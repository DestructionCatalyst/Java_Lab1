package ru.mephi.komzavladislav.lab3;

public enum Role {
    STAFF(50000, 0.1) {
        public String toString(){
            return "Staff";
        }
    },
    MANAGER(100000, 0.2) {
        public String toString() {
            return "Manager";
        }
    },
    EXECUTIVE(200000, 0.3) {
        public String toString() {
            return "Executive";
        }
    },;

    public int salary;
    public double premiumRate;

    Role(int salary, double premiumRate) {
        this.salary = salary;
        this.premiumRate = premiumRate;
    }

    @Override
    public abstract String toString();
}
